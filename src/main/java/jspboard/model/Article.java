package jspboard.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article implements Serializable {

	private static final long serialVersionUID = 23434453459343294L;

	private int aid;
	private String atitle;
	private String acontent;
	private Timestamp aregdate;
	private int acount;
	private int afcount;
	private String adelyn;
	private String mid;
	private int bid;
	private String bname;

	public Article() {
	}

	public Article(int aid, String atitle, String acontent, Timestamp aregdate, int acount, int afcount, String adelyn,
			String mid, int bid, String bname) {
		this.aid = aid;
		this.atitle = atitle;
		this.acontent = acontent;
		this.aregdate = aregdate;
		this.acount = acount;
		this.afcount = afcount;
		this.adelyn = adelyn;
		this.mid = mid;
		this.bid = bid;
		this.bname = bname;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public Timestamp getAregdate() {
		return aregdate;
	}

	public void setAregdate(Timestamp aregdate) {
		this.aregdate = aregdate;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public int getAfcount() {
		return afcount;
	}

	public void setAfcount(int afcount) {
		this.afcount = afcount;
	}

	public String getAdelyn() {
		return adelyn;
	}

	public void setAdelyn(String adelyn) {
		this.adelyn = adelyn;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "Article [aid=" + aid + ", atitle=" + atitle + ", acontent=" + acontent + ", aregdate=" + aregdate
				+ ", acount=" + acount + ", afcount=" + afcount + ", adelyn=" + adelyn + ", mid=" + mid + ", bid=" + bid
				+ ", bname=" + bname + "]";
	}

}
