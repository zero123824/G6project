package com.member.model;
import java.util.List;
import java.util.Set;

public interface MemberDAO_interface {
	Integer add(MemberVO newmember);
	void update(MemberVO selectedmem);
	void delete(Integer member_id);
	MemberVO findByPK(Integer member_id);
	MemberVO findByAccount(String member_account);
	List<MemberVO> getAll();
	List<MemberVO> loginCheck();
	Set<MemberVO> getAll(String keyword);
	void updateStatus(MemberVO selectedmem);
	
	/**JDBC TRANSACTION ACID**/
//	void addByJDBCTransaction(MemberVO newmember,List<String> favorlist);
}
