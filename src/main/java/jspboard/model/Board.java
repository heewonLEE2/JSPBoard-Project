package jspboard.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Board implements Serializable {

	private static final long serialVersionUID = 3453453459343294L;

	private int bid;
	private String bname;
	private Timestamp bregdate;
	private String bdelyn;

	public Board() {
	}

	public Board(int bid, String bname, Timestamp bregdate, String bdelyn) {
		this.bid = bid;
		this.bname = bname;
		this.bregdate = bregdate;
		this.bdelyn = bdelyn;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Timestamp getBregdate() {
		return bregdate;
	}

	public void setBregdate(Timestamp bregdate) {
		this.bregdate = bregdate;
	}

	public String getBdelyn() {
		return bdelyn;
	}

	public void setBdelyn(String bdelyn) {
		this.bdelyn = bdelyn;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", bname=" + bname + ", bregdate=" + bregdate + ", bdelyn=" + bdelyn + "]";
	}

}
