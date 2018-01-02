package com.member.model;
import java.util.List;

public interface MemberDAO_interface {
	Integer add(MemberVO newmember);
	void update(MemberVO selectedmem);
	void delete(Integer member_id);
	MemberVO findByPK(Integer member_id);
	MemberVO findByAccount(String member_account);
	List<MemberVO> getAll();
	List<MemberVO> loginCheck();
	
	/**JDBC TRANSACTION ACID**/
//	void addByJDBCTransaction(MemberVO newmember,List<String> favorlist);
}
