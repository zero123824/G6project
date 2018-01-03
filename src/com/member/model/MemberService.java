package com.member.model;

import java.util.ArrayList;
import java.util.List;

import com.genre.model.GenreDAO;
import com.genre.model.GenreDAO_interface;
import com.member_favor.model.MemberFavorDAO;
import com.member_favor.model.MemberFavorDAO_interface;
import com.member_favor.model.MemberFavorVO;

public class MemberService {
	private MemberDAO_interface dao;
	private MemberFavorDAO_interface favordao;
	private GenreDAO_interface genredao;
	public MemberService(){
		dao = new MemberDAO();
		favordao = new MemberFavorDAO();
		genredao = new GenreDAO();
	}
	
	public MemberVO add(String member_account,String member_psw,String member_lastname,String member_firstname
			,String member_address,String mobilenum,String member_email,java.sql.Date member_birthday,String member_idcode
			,String creaditcard,Integer subsenews,Integer member_sex,Integer member_lock_status,byte[] member_pic,String member_nickname,
			List<String> favorlist){
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_account(member_account)
		.setMember_psw(member_psw)
		.setMember_lastname(member_lastname)
		.setMember_firstname(member_firstname)
		.setMember_address(member_address)
		.setMobilenum(mobilenum)
		.setMember_email(member_email)
		.setMember_birthday(member_birthday)
		.setMember_idcode(member_idcode)
		.setCreaditcard(creaditcard)
		.setSubsenews(subsenews)
		.setMember_sex(member_sex)
		.setMember_lock_status(member_lock_status)
		.setMember_pic(member_pic)
		.setMember_nickname(member_nickname);
		Integer member_id = dao.add(memberVO);
		insertfavor(favorlist, member_id);
		return findByAccount(member_account);	
	}
	
	public MemberVO update(Integer member_id,String member_account,String member_psw,String member_lastname,String member_firstname
			,String member_address,String mobilenum,String member_email,java.sql.Date member_birthday,String member_idcode
			,String creaditcard,Integer subsenews,Integer member_sex,Integer member_lock_status,byte[] member_pic,String member_nickname){
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id)
		.setMember_account(member_account)
		.setMember_psw(member_psw)
		.setMember_lastname(member_lastname)
		.setMember_firstname(member_firstname)
		.setMember_address(member_address)
		.setMobilenum(mobilenum)
		.setMember_email(member_email)
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
	
	public List<String> getfavorTypeName(Integer member_id) {
		List<String> list = new ArrayList<String>();
		for(MemberFavorVO mfv : favordao.getOneMemFavor(member_id)) {
			list.add(genredao.findByPrimaryKey(mfv.getGenre_id()).getGenre_name());
		}		
		return list;
	}
	
	public void insertfavor(List<String> favorlist,Integer member_id) {
//		dao.addByJDBCTransaction(member_id, favorlist);
		for(String favor : favorlist) {
			MemberFavorVO newmemberfavor = new MemberFavorVO();
			newmemberfavor.setGenre_id(Integer.valueOf(favor));
			newmemberfavor.setMember_id(member_id);
			favordao.add(newmemberfavor);
		}
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
