package com.member.model;

import java.io.Serializable;

public class MemberVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer member_id;
	private String member_account;
	private String member_psw;
	private String member_lastname;
	private String member_firstname;
	private String member_address;
	private String mobilenum;
	private String member_email;
	private java.sql.Date member_birthday;
	private String member_idcode;
	private String creaditcard;
	private Integer subsenews;
	private Integer member_sex;
	private Integer member_lock_status;
	private byte[] member_pic;
	private String member_nickname;

	public Integer getMember_id() {
		return member_id;
	}

	public MemberVO setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}

	public String getMember_account() {
		return member_account;
	}

	public MemberVO setMember_account(String member_account) {
		this.member_account = member_account;
		return this;
	}

	public String getMember_psw() {
		return member_psw;
	}

	public MemberVO setMember_psw(String member_psw) {
		this.member_psw = member_psw;
		return this;
	}

	public String getMember_lastname() {
		return member_lastname;
	}

	public MemberVO setMember_lastname(String member_lastname) {
		this.member_lastname = member_lastname;
		return this;
	}

	public String getMember_firstname() {
		return member_firstname;
	}

	public MemberVO setMember_firstname(String member_firstname) {
		this.member_firstname = member_firstname;
		return this;
	}

	public String getMember_address() {
		return member_address;
	}

	public MemberVO setMember_address(String member_address) {
		this.member_address = member_address;
		return this;
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public MemberVO setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
		return this;
	}

	public String getCreaditcard() {
		return creaditcard;
	}

	public MemberVO setCreaditcard(String creaditcard) {
		this.creaditcard = creaditcard;
		return this;
	}

	public Integer getSubsenews() {
		return subsenews;
	}

	public MemberVO setSubsenews(Integer subsenews) {
		this.subsenews = subsenews;
		return this;
	}

	public byte[] getMember_pic() {
		return member_pic;
	}

	public MemberVO setMember_pic(byte[] member_pic) {
		this.member_pic = member_pic;
		return this;
	}

	public String getMember_email() {
		return member_email;
	}

	public MemberVO setMember_email(String member_email) {
		this.member_email = member_email;
		return this;
	}

	public java.sql.Date getMember_birthday() {
		return member_birthday;
	}

	public MemberVO setMember_birthday(java.sql.Date member_birthday) {
		this.member_birthday = member_birthday;
		return this;
	}

	public String getMember_idcode() {
		return member_idcode;
	}

	public MemberVO setMember_idcode(String member_idcode) {
		this.member_idcode = member_idcode;
		return this;
	}

	public Integer getMember_sex() {
		return member_sex;
	}

	public MemberVO setMember_sex(Integer member_sex) {
		this.member_sex = member_sex;
		return this;
	}

	public Integer getMember_lock_status() {
		return member_lock_status;
	}

	public MemberVO setMember_lock_status(Integer member_lock_status) {
		this.member_lock_status = member_lock_status;
		return this;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public MemberVO setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_account == null) ? 0 : member_account.hashCode());
		result = prime * result + ((member_email == null) ? 0 : member_email.hashCode());
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
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
		MemberVO other = (MemberVO) obj;
		if (member_account == null) {
			if (other.member_account != null)
				return false;
		} else if (!member_account.equals(other.member_account))
			return false;
		if (member_email == null) {
			if (other.member_email != null)
				return false;
		} else if (!member_email.equals(other.member_email))
			return false;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		return true;
	}
}
