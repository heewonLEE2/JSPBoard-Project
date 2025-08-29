# 📋 JSPBoard V0.1

![JSP](https://img.shields.io/badge/JSP-4CAF50?style=for-the-badge&logo=java&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

> **전통적인 Java 웹 기술 스택을 활용한 완전한 게시판 시스템**  
> Model-View-Controller 패턴과 계층화 아키텍처를 구현한 교육용 프로젝트

## 🏗️ 프로젝트 개요

JSPBoard는 **순수 Java 웹 기술**로 구현된 다기능 게시판 시스템입니다. Spring Framework 없이 **Servlet**, **JSP**, **JDBC**를 활용하여 완전한 웹 애플리케이션을 구현했습니다.

### 🎯 핵심 특징
- **🔧 순수 Java 웹 기술** - Spring Framework 미사용
- **📐 MVC 패턴 구현** - 명확한 계층 분리
- **🗂️ Command 패턴** - 확장 가능한 요청 처리
- **📁 파일 업로드** - 이미지 썸네일 생성 지원
- **💬 실시간 댓글** - Ajax 기반 동적 댓글 시스템
- **🔐 세션 기반 인증** - 로그인/로그아웃 처리

## 🛠️ 기술 스택

### Backend
- **Language**: Java 8+
- **Web Server**: Apache Tomcat 9.0+
- **Database**: Oracle Database
- **Connection Pool**: HikariCP
- **Build Tool**: Maven

### Frontend
- **Template**: JSP (JavaServer Pages)
- **Styling**: Bootstrap 5
- **JavaScript**: jQuery + Axios

### Architecture Patterns
- **MVC Pattern** - Model, View, Controller 분리
- **Command Pattern** - 요청별 커맨드 객체
- **DAO Pattern** - 데이터 접근 계층
- **Service Pattern** - 비즈니스 로직 계층
- **Singleton Pattern** - 유틸리티 클래스

## 📋 주요 기능

### 🏠 대시보드
- 최근 게시물 (10건) 실시간 조회
- 최근 가입 회원 목록
- 최근 업로드 사진 썸네일 갤러리
- 등록된 게시판 목록

### 👥 회원 관리
- **회원가입/로그인** - 세션 기반 인증
- **회원 목록 조회** - 관리자 기능
- **회원 상태 관리** - 활성/비활성 처리

### 📝 게시판 시스템
- **다중 게시판** - 카테고리별 게시판 생성
- **게시글 CRUD** - 작성, 읽기, 수정, 삭제
- **검색 기능** - 제목/내용 통합 검색
- **페이징 처리** - 효율적인 대용량 데이터 처리

### 📎 파일 관리
- **다중 파일 업로드** - Multipart 지원
- **썸네일 자동 생성** - 이미지 최적화
- **파일 다운로드** - 안전한 파일 전송
- **파일 타입 검증** - 보안 강화

### 💬 댓글 시스템
- **실시간 댓글** - Ajax 기반 비동기 처리
- **댓글 CRUD** - 동적 추가/삭제
- **권한 제어** - 작성자만 삭제 가능

## 📁 프로젝트 구조

```
src/main/java/
├── jspboard/
│   ├── command/          # Command Pattern 구현체
│   │   ├── IndexCommand.java
│   │   ├── LoginCommand.java
│   │   ├── RegistArticleCommand.java
│   │   └── ...
│   ├── controller/       # MVC Controller
│   │   └── BoardController.java
│   ├── dao/             # Data Access Object
│   │   ├── interface/
│   │   └── impl/
│   ├── service/         # Business Logic Layer
│   │   ├── interface/
│   │   └── impl/
│   ├── model/           # Value Object (VO)
│   │   ├── Member.java
│   │   ├── Article.java
│   │   ├── Board.java
│   │   └── ...
│   ├── util/            # Utility Classes
│   │   ├── ConnectionUtil.java
│   │   ├── ThumbnailUtil.java
│   │   └── FileDownloadUtil.java
│   ├── filter/          # Servlet Filters
│   │   └── EncodingFilter.java
│   └── constant/        # Constants
│       └── BoardConstant.java

src/main/webapp/
├── jsp/                 # JSP Views
│   ├── include/         # 공통 레이아웃
│   ├── member/          # 회원 관련 JSP
│   ├── article/         # 게시글 관련 JSP
│   ├── board/           # 게시판 관리 JSP
│   └── test/            # 테스트 페이지
├── js/                  # JavaScript 파일
│   └── reply.js         # 댓글 기능
└── WEB-INF/
    └── web.xml          # 웹 애플리케이션 설정
```

## 🚀 실행 방법

### 1. 사전 준비
```bash
# 필수 요구사항
- Java 8 이상
- Apache Tomcat 9.0 이상
- Oracle Database 11g 이상
- Maven 3.6 이상
```

### 2. 데이터베이스 설정
```sql
-- 테이블 생성 스크립트 실행
-- 1. MEMBER 테이블
CREATE TABLE member (
    mid VARCHAR2(50) PRIMARY KEY,
    mpass VARCHAR2(200) NOT NULL,
    mname VARCHAR2(200) NOT NULL,
    mregdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    mdelyn CHAR(1) DEFAULT 'N'
);

-- 2. BOARD 테이블  
CREATE TABLE board (
    bid NUMBER PRIMARY KEY,
    bname VARCHAR2(200) NOT NULL,
    bregdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    bdelyn CHAR(1) DEFAULT 'N'
);

-- 3. ARTICLE 테이블
CREATE TABLE article (
    aid NUMBER PRIMARY KEY,
    atitle VARCHAR2(2000) NOT NULL,
    acontent CLOB,
    aregdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    acount NUMBER DEFAULT 0,
    afcount NUMBER DEFAULT 0,
    adelyn CHAR(1) DEFAULT 'N',
    mid VARCHAR2(50) REFERENCES member(mid),
    bid NUMBER REFERENCES board(bid)
);

-- 4. REPLY 테이블
CREATE TABLE reply (
    rid NUMBER PRIMARY KEY,
    rcontent VARCHAR2(2000) NOT NULL,
    rregdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    rdelyn CHAR(1) DEFAULT 'N',
    mid VARCHAR2(50) REFERENCES member(mid),
    aid NUMBER REFERENCES article(aid)
);

-- 5. AFILE 테이블 (첨부파일)
CREATE TABLE afile (
    afid NUMBER PRIMARY KEY,
    afsfname VARCHAR2(500) NOT NULL,
    afcfname VARCHAR2(500) NOT NULL,
    afcontenttype VARCHAR2(100),
    afregdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    afdelyn CHAR(1) DEFAULT 'N',
    mid VARCHAR2(50) REFERENCES member(mid),
    aid NUMBER REFERENCES article(aid),
    thumbnailpath VARCHAR2(500)
);

-- 시퀀스 생성
CREATE SEQUENCE seq_board START WITH 1;
CREATE SEQUENCE seq_article START WITH 1;
CREATE SEQUENCE seq_reply START WITH 1;
CREATE SEQUENCE seq_afile START WITH 1;
```

### 3. 애플리케이션 설정
```xml
<!-- context.xml에 데이터소스 설정 -->
<Resource name="jdbc/oracle"
          auth="Container"
          type="javax.sql.DataSource"
          driverClassName="oracle.jdbc.OracleDriver"
          url="jdbc:oracle:thin:@localhost:1521:XE"
          username="your_username"
          password="your_password"
          maxTotal="20"
          initialSize="5"/>
```

### 4. 빌드 및 배포
```bash
# 프로젝트 클론
git clone https://github.com/heewonLEE2/jspboard.git
cd jspboard

# Maven 빌드
mvn clean compile package

# Tomcat에 배포
cp target/JSPBoard.war $TOMCAT_HOME/webapps/

# Tomcat 시작
$TOMCAT_HOME/bin/startup.sh

# 브라우저에서 접속
http://localhost:8080/JSPBoard/
```

## 🏛️ 아키텍처 설계

### MVC 패턴 구현
```java
// Controller: 모든 요청을 처리하는 Front Controller
@WebServlet("*.do")
public class BoardController extends HttpServlet {
    // Command Pattern으로 요청별 처리 위임
}

// Command: 각 요청별 비즈니스 로직 처리
public class IndexCommand implements BoardCommand {
    public String process(HttpServletRequest req, HttpServletResponse resp);
}

// Service: 비즈니스 로직 계층
public class ArticleServiceImpl implements ArticleService {
    // DAO 계층과 협업하여 비즈니스 로직 처리
}

// DAO: 데이터 접근 계층
public class ArticleDaoImpl implements ArticleDao {
    // 순수 JDBC를 사용한 데이터베이스 작업
}
```

### 핵심 디자인 패턴

#### 1. **Command Pattern**
- 요청별 독립적인 Command 객체 생성
- URL 패턴에 따른 동적 Command 로딩
- 확장성과 유지보수성 향상

#### 2. **DAO Pattern**  
- 데이터 접근 로직 완전 분리
- 인터페이스 기반 구현으로 교체 가능
- Connection Pool 활용한 효율적 DB 연결

#### 3. **Service Layer**
- 비즈니스 로직의 중앙화
- 트랜잭션 경계 명확화
- Command와 DAO 사이의 중간 계층

## 📈 성능 최적화

### 데이터베이스 최적화
- **Connection Pooling**: HikariCP 사용
- **페이징 처리**: ROWNUM을 활용한 효율적 조회
- **인덱스 활용**: 검색 성능 향상

### 파일 처리 최적화  
- **썸네일 생성**: 이미지 자동 리사이징
- **스트림 처리**: 대용량 파일 안전 처리
- **MIME 타입 검증**: 보안 강화

### 프론트엔드 최적화
- **Ajax 통신**: 부분 페이지 갱신
- **Bootstrap CDN**: 빠른 스타일 로딩
- **jQuery**: 효율적 DOM 조작

## 🔐 보안 기능

### 인증 및 권한
- **세션 기반 인증**: 안전한 로그인 처리
- **CSRF 방지**: 토큰 기반 요청 검증
- **권한별 접근 제어**: 작성자 검증

### 데이터 보안
- **XSS 방지**: 사용자 입력 데이터 이스케이핑
- **SQL Injection 방지**: PreparedStatement 사용
- **파일 업로드 보안**: 확장자 및 크기 제한

## 🧪 테스트 페이지

프로젝트에는 각 기능별 테스트 페이지가 포함되어 있습니다:

- **회원 관리 테스트**: `/jsp/test/memberTest.jsp`
- **게시판 테스트**: `/jsp/test/boardTest.jsp`  
- **게시글 테스트**: `/jsp/test/articleTest.jsp`
- **댓글 테스트**: `/jsp/test/replyTest.jsp`
- **파일 테스트**: `/jsp/test/afileTest.jsp`
- **썸네일 생성 테스트**: `/jsp/test/ThumbnailTest.jsp`

## 📖 학습 포인트

이 프로젝트를 통해 학습할 수 있는 주요 개념들:

### Java 웹 개발
- **Servlet API** - HttpServlet, Filter, Listener
- **JSP** - EL, JSTL, 커스텀 태그
- **JDBC** - Connection Pool, PreparedStatement, 트랜잭션

### 디자인 패턴
- **MVC Pattern** - 관심사 분리
- **Command Pattern** - 요청 처리 객체화
- **DAO Pattern** - 데이터 접근 추상화
- **Singleton Pattern** - 자원 관리

### 웹 표준 기술
- **HTML5** - 시맨틱 마크업
- **CSS3** - Bootstrap 프레임워크
- **JavaScript** - Ajax, DOM 조작, 이벤트 처리

## 🔄 향후 개선 계획

- [ ] **REST API** 도입으로 프론트엔드 분리
- [ ] **Spring Boot** 마이그레이션
- [ ] **JPA/Hibernate** 적용
- [ ] **Redis** 캐싱 시스템 도입
- [ ] **Docker** 컨테이너화
- [ ] **JUnit** 단위 테스트 추가
