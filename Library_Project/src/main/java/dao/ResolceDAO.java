package dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import vo.LentVO;
import vo.MemberVO;
import vo.Resolce_SearchVO;

public class ResolceDAO {
	
	@Autowired
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//최초 자료검색페이지 접근시 자료호출
	public List<Resolce_SearchVO> select(){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select");
		return list;
	}
	//컬럼별 내용검색 
	public List<Resolce_SearchVO> select_bName(String search){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select_bName", search);
		return list;
	}
	public List<Resolce_SearchVO> select_category(String search){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select_category", search);
		return list;
	}
	public List<Resolce_SearchVO> select_company(String search){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select_company", search);
		return list;
	}
	public List<Resolce_SearchVO> select_isbl(String search){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select_isbl", search);
		return list;
	}
	public List<Resolce_SearchVO> select_bYear(String search){
		List<Resolce_SearchVO> list = sqlSession.selectList("search.select_bYear", search);
		return list;
	}
	
	//책정보 상세보기 페이지
	public Resolce_SearchVO selectOne(String isbl){
		Resolce_SearchVO vo= sqlSession.selectOne("search.select_one", isbl);
		return vo;
	}
	
	//예약페이지 회원정보 가져오기
	public MemberVO selectID(String m_id) {
		MemberVO vo = sqlSession.selectOne("search.select_m_id", m_id);
		return vo;
	}
	
	//예약된 정보를 db에 넣어주기
	public int insertLent(LentVO vo) { 
		int res = sqlSession.insert("search.insertLent", vo);
		return res;
	}
	//이미 예약이 되어있는지 확인
	public List<LentVO> select_lentList(String b_isbl) {
		List<LentVO> list = sqlSession.selectList("search.LentSelect", b_isbl);
		return list;
	}
	//새책 넣어주기
	public int insertBook(Resolce_SearchVO vo) {
		int res = sqlSession.insert("search.new_book_insert", vo);
		return res;
	}
	
	public List<Resolce_SearchVO> selectTime(){
		
		List<Resolce_SearchVO> list = sqlSession.selectList("search.selectTime");
		
		return list;
	}
	//예약된책 대여해주기
	public int lentupdate(int idx) {
		int res = sqlSession.update("search.lent_update", idx);
		return res;
	}
	//대여된책 반납해주기
	public int rtupdate(int idx) {
		int res = sqlSession.update("search.rt_update", idx);
		return res;
	}
	//예약된책 취소해주기
	public int cancelupdate(int idx) {
		int res = sqlSession.update("search.cancelupdate", idx);
		return res;
	}

	
	
}
