package jspboard.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reply implements Serializable {

	private static final long serialVersionUID = 9348493589459L;

	private int rid;
	private String rcontent;
	private Timestamp rregdate;
	private String rdelyn;
	private String mid;
	private int aid;

	public Reply() {
	}

	public Reply(int rid, String rcontent, Timestamp rregdate, String rdelyn, String mid, int aid) {
		this.rid = rid;
		this.rcontent = rcontent;
		this.rregdate = rregdate;
		this.rdelyn = rdelyn;
		this.mid = mid;
		this.aid = aid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public Timestamp getRregdate() {
		return rregdate;
	}

	public void setRregdate(Timestamp rregdate) {
		this.rregdate = rregdate;
	}

	public String getRdelyn() {
		return rdelyn;
	}

	public void setRdelyn(String rdelyn) {
		this.rdelyn = rdelyn;
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

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", rcontent=" + rcontent + ", rregdate=" + rregdate + ", rdelyn=" + rdelyn
				+ ", mid=" + mid + ", aid=" + aid + "]";
	}

}
