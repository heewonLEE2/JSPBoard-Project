package jspboard.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Afile implements Serializable {

	private static final long serialVersionUID = 3453454393589459L;

	private int afid;
	private String afsfname;
	private String afcfname;
	private String afcontenttype;
	private Timestamp afregdate;
	private String afdelyn;
	private String mid;
	private int aid;
	private String thumbnailpath;

	public Afile() {
	}

	public Afile(int afid, String afsfname, String afcfname, String afcontenttype, Timestamp afregdate, String afdelyn,
			String mid, int aid, String thumbnailpath) {
		this.afid = afid;
		this.afsfname = afsfname;
		this.afcfname = afcfname;
		this.afcontenttype = afcontenttype;
		this.afregdate = afregdate;
		this.afdelyn = afdelyn;
		this.mid = mid;
		this.aid = aid;
		this.thumbnailpath = thumbnailpath;
	}

	public int getAfid() {
		return afid;
	}

	public void setAfid(int afid) {
		this.afid = afid;
	}

	public String getAfsfname() {
		return afsfname;
	}

	public void setAfsfname(String afsfname) {
		this.afsfname = afsfname;
	}

	public String getAfcfname() {
		return afcfname;
	}

	public void setAfcfname(String afcfname) {
		this.afcfname = afcfname;
	}

	public String getAfcontenttype() {
		return afcontenttype;
	}

	public void setAfcontenttype(String afcontenttype) {
		this.afcontenttype = afcontenttype;
	}

	public Timestamp getAfregdate() {
		return afregdate;
	}

	public void setAfregdate(Timestamp afregdate) {
		this.afregdate = afregdate;
	}

	public String getAfdelyn() {
		return afdelyn;
	}

	public void setAfdelyn(String afdelyn) {
		this.afdelyn = afdelyn;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getThumbnailpath() {
		return thumbnailpath;
	}

	public void setThumbnailpath(String thumbnailpath) {
		this.thumbnailpath = thumbnailpath;
	}

	@Override
	public String toString() {
		return "Afile [afid=" + afid + ", afsfname=" + afsfname + ", afcfname=" + afcfname + ", afcontenttype="
				+ afcontenttype + ", afregdate=" + afregdate + ", afdelyn=" + afdelyn + ", mid=" + mid + ", aid=" + aid
				+ ", thumbnailpath=" + thumbnailpath + "]";
	}

}
