package com.database;

import java.sql.Timestamp;

/**
 * Chatlog entity. @author MyEclipse Persistence Tools
 */

public class Chatlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid1;
	private Integer userid2;
	private String content;
	private Timestamp pubtime;
	private String memo1;
	private String memo2;

	// Constructors

	/** default constructor */
	public Chatlog() {
	}

	/** minimal constructor */
	public Chatlog(Integer userid1, Integer userid2, String content,
			Timestamp pubtime) {
		this.userid1 = userid1;
		this.userid2 = userid2;
		this.content = content;
		this.pubtime = pubtime;
	}

	/** full constructor */
	public Chatlog(Integer userid1, Integer userid2, String content,
			Timestamp pubtime, String memo1, String memo2) {
		this.userid1 = userid1;
		this.userid2 = userid2;
		this.content = content;
		this.pubtime = pubtime;
		this.memo1 = memo1;
		this.memo2 = memo2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid1() {
		return this.userid1;
	}

	public void setUserid1(Integer userid1) {
		this.userid1 = userid1;
	}

	public Integer getUserid2() {
		return this.userid2;
	}

	public void setUserid2(Integer userid2) {
		this.userid2 = userid2;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getPubtime() {
		return this.pubtime;
	}

	public void setPubtime(Timestamp pubtime) {
		this.pubtime = pubtime;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return this.memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	@Override
	public String toString() {
		return "Chatlog [id=" + id + ", userid1=" + userid1 + ", userid2="
				+ userid2 + ", content=" + content + ", pubtime=" + pubtime
				+ ", memo1=" + memo1 + ", memo2=" + memo2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memo1 == null) ? 0 : memo1.hashCode());
		result = prime * result + ((memo2 == null) ? 0 : memo2.hashCode());
		result = prime * result + ((pubtime == null) ? 0 : pubtime.hashCode());
		result = prime * result + ((userid1 == null) ? 0 : userid1.hashCode());
		result = prime * result + ((userid2 == null) ? 0 : userid2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chatlog other = (Chatlog) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memo1 == null) {
			if (other.memo1 != null)
				return false;
		} else if (!memo1.equals(other.memo1))
			return false;
		if (memo2 == null) {
			if (other.memo2 != null)
				return false;
		} else if (!memo2.equals(other.memo2))
			return false;
		if (pubtime == null) {
			if (other.pubtime != null)
				return false;
		} else if (!pubtime.equals(other.pubtime))
			return false;
		if (userid1 == null) {
			if (other.userid1 != null)
				return false;
		} else if (!userid1.equals(other.userid1))
			return false;
		if (userid2 == null) {
			if (other.userid2 != null)
				return false;
		} else if (!userid2.equals(other.userid2))
			return false;
		return true;
	}

}