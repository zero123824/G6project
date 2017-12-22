package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberDAO();
	}
	
	public MemberVO add(String member_account,String member_psw,String member_lastname,String member_firstname
			,String member_address,String mobilenum,String member_emailaddress,java.sql.Date member_birthday,String member_idcode
			,String creaditcard,Integer subsenews,Integer member_sex,Integer member_lock_status,byte[] member_pic,String member_nickname){
		MemberVO memberVO = new MemberVO();
		System.out.println(member_pic);
		memberVO.setMember_account(member_account)
		.setMember_psw(member_psw)
		.setMember_lastname(member_lastname)
		.setMember_firstname(member_firstname)
		.setMember_address(member_address)
		.setMobilenum(mobilenum)
		.setMember_emailaddress(member_emailaddress)
		.setMember_birthday(member_birthday)
		.setMember_idcode(member_idcode)
		.setCreaditcard(creaditcard)
		.setSubsenews(subsenews)
		.setMember_sex(member_sex)
		.setMember_lock_status(member_lock_status)
		.setMember_pic(member_pic)
		.setMember_nickname(member_nickname);
		dao.add(memberVO);
		return memberVO;	
	}
	
	public MemberVO update(Integer member_id,String member_account,String member_psw,String member_lastname,String member_firstname
			,String member_address,String mobilenum,String member_emailaddress,java.sql.Date member_birthday,String member_idcode
			,String creaditcard,Integer subsenews,Integer member_sex,Integer member_lock_status,byte[] member_pic,String member_nickname){
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id)
		.setMember_account(member_account)
		.setMember_psw(member_psw)
		.setMember_lastname(member_lastname)
		.setMember_firstname(member_firstname)
		.setMember_address(member_address)
		.setMobilenum(mobilenum)
		.setMember_emailaddress(member_emailaddress)
		.setMember_birthday(member_birthday)
		.setMember_idcode(member_idcode)
		.setCreaditcard(creaditcard)
		.setSubsenews(subsenews)
		.setMember_sex(member_sex)
		.setMember_lock_status(member_lock_status)
		.setMember_pic(member_pic)
		.setMember_nickname(member_nickname);
		dao.update(memberVO);
		return memberVO;		
	}
	
	public void suspend(MemberVO memberVO,Integer member_lock_status){
		memberVO.setMember_lock_status(member_lock_status);
		dao.update(memberVO);
	}
	
	public void delete(Integer member_id){
		dao.delete(member_id); 
	}
	
	public MemberVO findByPK(Integer member_id){
		return dao.findByPK(member_id);	
	}
	
	public MemberVO findByAccount(String member_account){
		return dao.findByAccount(member_account);	
	}

	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}

}
