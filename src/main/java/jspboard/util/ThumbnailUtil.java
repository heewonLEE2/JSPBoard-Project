package jspboard.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * ThumbnailUtil
 *
 * - 원본 비율 유지로 썸네일 생성
 * - 포맷 자동 판단 (jpg/jpeg, png, gif, bmp, webp)
 * - PNG/WEBP/GIF의 투명도 유지, JPEG은 배경색으로 합성
 * - 품질(0.0~1.0) 및 WebP 무손실 옵션 지원
 * - 기본적으로 업스케일(확대)은 하지 않음 (필요 시 allowUpscale=true 사용)
 *
 *   메이븐레파지토리 : webp-imageio-0.1.6.jar 다운로드
 *   또는
 *   사용 전 pom.xml에 다음 의존성을 추가:
 *   org.sejda.imageio : webp-imageio : 0.1.6
 *
 *   서버(애플리케이션) 환경에서는 플러그인 로딩을 위해
 *   ImageIO.scanForPlugins();를 한 번 호출해 주세요 (본 클래스의 static 블록에서 호출함).
 */
public final class ThumbnailUtil {

    // 지원하는 알파(투명) 채널 포맷
    private static final String[] ALPHA_FORMATS = {"png", "gif", "webp"};

    static {
        // ImageIO 플러그인(특히 webp-imageio)을 런타임에 스캔하여 등록
        // 일부 서버 컨테이너에서는 자동 로딩이 누락되는 경우가 있으므로 명시 호출
        ImageIO.scanForPlugins();
    }

    private ThumbnailUtil() {}

    /* ===========================
     * 공개 API (간단 사용)
     * =========================== */

    /**
     * 가장 단순한 사용: 비율 유지, 업스케일 금지, JPEG일 때 흰색 배경, 품질 0.85
     */
    public static void createThumbnail(String inputPath, String outputPath,
                                       int maxWidth, int maxHeight) throws IOException {
        createThumbnail(inputPath, outputPath, maxWidth, maxHeight,
                0.85f, false, Color.WHITE, false);
    }

    /**
     * 품질(0.0~1.0) 지정 / 업스케일 허용 여부 선택 / JPEG 배경색 지정
     */
    public static void createThumbnail(String inputPath, String outputPath,
                                       int maxWidth, int maxHeight,
                                       float quality, boolean allowUpscale,
                                       Color jpegBackground) throws IOException {
        createThumbnail(inputPath, outputPath, maxWidth, maxHeight,
                quality, false, jpegBackground, allowUpscale);
    }

    /**
     * 품질 + WebP 무손실 여부 + 업스케일 허용 + JPEG 배경색까지 모두 제어
     *  - JPEG: 무손실 개념 없음 → quality만 적용, 배경색으로 합성
     *  - PNG/GIF: 보통 무손실로 저장(quality 무시될 수 있음)
     *  - WEBP: quality 또는 무손실(Lossless) 중 선택 가능
     */
    public static void createThumbnail(String inputPath, String outputPath,
                                       int maxWidth, int maxHeight,
                                       float quality, boolean webpLossless,
                                       Color jpegBackground, boolean allowUpscale) throws IOException {
        // 1) 입력/출력 파일 객체
        File in = new File(inputPath);
        File out = new File(outputPath);

        // 2) 원본 읽기
        BufferedImage original = ImageIO.read(in);
        if (original == null) {
            throw new IOException("이미지를 읽을 수 없습니다: " + inputPath);
        }

        // 3) 출력 포맷 추론 (확장자)
        String format = getFormatFromFilename(outputPath);

        // 4) 목표 크기 계산 (비율 유지)
        Dimension target = computeFitSize(original.getWidth(), original.getHeight(),
                                          maxWidth, maxHeight, allowUpscale);

        // 5) 출력 이미지 타입 결정
        boolean outputSupportsAlpha = supportsAlpha(format);
        boolean inputHasAlpha = original.getColorModel().hasAlpha();

        // JPEG은 알파 불가 → RGB 타입 + 배경색 합성
        int outType = (outputSupportsAlpha || inputHasAlpha) && !format.equals("jpg") && !format.equals("jpeg")
                ? BufferedImage.TYPE_INT_ARGB
                : BufferedImage.TYPE_INT_RGB;

        // 6) 고품질 리사이즈
        BufferedImage scaled = resizeHighQuality(original, target.width, target.height, outType,
                (!outputSupportsAlpha && inputHasAlpha) ? jpegBackground : null);

        // 7) 저장 (포맷별 파라미터 설정 포함)
        writeWithParams(scaled, out, format, quality, webpLossless);
    }

    /* ===========================
     * 내부 유틸들
     * =========================== */

    /** 파일명에서 확장자를 포맷으로 추출 */
    private static String getFormatFromFilename(String name) {
        int dot = name.lastIndexOf('.');
        if (dot < 0 || dot == name.length() - 1) return "jpg";
        String ext = name.substring(dot + 1).toLowerCase();
        switch (ext) {
            case "jpg":
            case "jpeg":
                return "jpg";
            case "png":
                return "png";
            case "gif":
                return "gif";
            case "bmp":
                return "bmp";
            case "webp":
                return "webp"; // webp-imageio 플러그인이 처리
            default:
                return "jpg";
        }
    }

    /** 포맷이 알파(투명) 채널을 지원하는지 여부 */
    private static boolean supportsAlpha(String format) {
        for (String f : ALPHA_FORMATS) {
            if (f.equals(format)) return true;
        }
        return false;
    }

    /**
     * 비율 유지하며 (maxWidth, maxHeight)에 '맞춰 넣기'
     * allowUpscale=false면 원본보다 크게 키우지 않음
     */
    private static Dimension computeFitSize(int ow, int oh, int maxW, int maxH, boolean allowUpscale) {
        double wr = maxW / (double) ow;
        double hr = maxH / (double) oh;
        double ratio = Math.min(wr, hr);

        // 업스케일 비허용이면, 축소가 필요 없는 경우 원본 크기 유지
        if (!allowUpscale && ratio > 1.0) {
            return new Dimension(ow, oh);
        }
        int w = Math.max(1, (int) Math.round(ow * ratio));
        int h = Math.max(1, (int) Math.round(oh * ratio));
        return new Dimension(w, h);
    }

    /**
     * 고품질 리사이징
     * - ANTIALIASING, BILINEAR, RENDER_QUALITY 힌트 사용
     * - JPEG(알파 미지원)로 저장해야 하는데 입력이 알파를 가진 경우,
     *   배경색(background)이 null이 아니면 먼저 배경으로 합성
     */
    private static BufferedImage resizeHighQuality(BufferedImage src, int w, int h, int targetType, Color background) {
        BufferedImage dst = new BufferedImage(w, h, targetType);
        Graphics2D g = dst.createGraphics();

        // 고품질 렌더링 힌트
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // JPEG 등 알파 불가 포맷으로 그릴 때 배경색 채우기
        if (targetType == BufferedImage.TYPE_INT_RGB && background != null) {
            g.setComposite(AlphaComposite.SrcOver);
            g.setColor(background);
            g.fillRect(0, 0, w, h);
        }

        // 이미지 그리기
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        return dst;
    }

    /**
     * ImageWriter를 찾아서 포맷별 파라미터를 세팅한 뒤 저장
     * - JPEG: quality(압축률) 적용
     * - WEBP(Sejda): Lossless / Lossy(quality) 설정
     * - PNG/GIF/BMP: 구현에 따라 quality 무시될 수 있음(무손실/팔레트)
     */
    private static void writeWithParams(BufferedImage img, File outFile, String format,
                                        float quality, boolean webpLossless) throws IOException {
        // 사용 가능한 Writer 탐색
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
        if (!writers.hasNext()) {
            throw new IOException("해당 포맷을 지원하는 ImageWriter가 없습니다: " + format);
        }
        ImageWriter writer = writers.next();

        // 출력 스트림 준비
        try (FileOutputStream fos = new FileOutputStream(outFile);
             ImageOutputStream ios = ImageIO.createImageOutputStream(fos)) {

            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            // 공통: 압축 가능한 경우 명시적 설정
            if (param.canWriteCompressed()) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

                // 포맷별 커스터마이징
                if (isWebP(format)) {
                    // Sejda webp-imageio:
                    // - 무손실: setCompressionType("Lossless")
                    // - 손실: setCompressionType("Lossy") + setCompressionQuality(0.0~1.0)
                    String[] types = param.getCompressionTypes();
                    if (types != null && types.length > 0) {
                        // 기본은 손실
                        String desired = webpLossless ? "Lossless" : "Lossy";
                        // 가용 타입 목록에서 원하는 타입이 있으면 사용
                        String chosen = types[0];
                        for (String t : types) {
                            if (t.equalsIgnoreCase(desired)) {
                                chosen = t;
                                break;
                            }
                        }
                        param.setCompressionType(chosen);
                    }
                    if (!webpLossless) {
                        // 손실일 때만 품질 반영
                        param.setCompressionQuality(clampQuality(quality));
                    }
                } else if (isJPEG(format)) {
                    // JPEG: 품질 0.0(고압축) ~ 1.0(고화질)
                    param.setCompressionQuality(clampQuality(quality));
                }
                // PNG/GIF/BMP는 드라이버에 따라 무시될 수 있음
            }

            // 실제 쓰기
            writer.write(null, new IIOImage(img, null, null), param);
            writer.dispose();
        }
    }

    private static boolean isJPEG(String fmt) {
        return "jpg".equalsIgnoreCase(fmt) || "jpeg".equalsIgnoreCase(fmt);
    }

    private static boolean isWebP(String fmt) {
        return "webp".equalsIgnoreCase(fmt);
    }

    private static float clampQuality(float q) {
        if (Float.isNaN(q)) return 0.85f;
        return Math.max(0.0f, Math.min(1.0f, q));
    }

    /* ===========================
     * 간단 실행 예시 (원하면 주석 해제)
     * =========================== */
    /*
    public static void main(String[] args) throws IOException {
        // 1) 기본 사용 (비율 유지, 업스케일 없음, JPEG면 흰 배경, 품질 0.85)
        ThumbnailUtil.createThumbnail("input.png", "thumb.jpg", 200, 150);

        // 2) 품질/업스케일/JPEG 배경 지정
        ThumbnailUtil.createThumbnail("input.png", "thumb2.jpg", 400, 400,
                0.9f, false, Color.WHITE);

        // 3) WebP 손실 압축 (품질 0.8)
        ThumbnailUtil.createThumbnail("input.png", "thumb_lossy.webp", 300, 300,
                0.8f, false, Color.WHITE, false);

        // 4) WebP 무손실
        ThumbnailUtil.createThumbnail("input.png", "thumb_lossless.webp", 300, 300,
                1.0f, true, Color.WHITE, false);
    }
    */
}

