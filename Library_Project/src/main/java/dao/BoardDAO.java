
package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {

	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//공지사항------------------------------------------------------------
	//페이지별 게시물 조회
	public List<BoardVO> selectList(HashMap<String, Integer> map){
		List<BoardVO> list = sqlSession.selectList("board.board_list_condition", map);
		return list;
	}
	//전체 게시물수 구하기
	public int getRowTotal() {
		int count = sqlSession.selectOne("board.board_count");
		return count;
	}
	
	//공지사항 상세보기------------------------------------------------------------
	//게시물 한 건 조회
	public BoardVO selectOne( int idx ) {
		BoardVO vo = sqlSession.selectOne("board.board_one", idx);
		return vo;
	}
	
	//게시글 삭제(업데이트)------------------------------------------------------------
	//삭제(된 것 처럼)업데이트
	public int del_update(int idx) {
		System.out.println("2idx:" + idx);
		int res = sqlSession.update("board.del_update", idx);
		return res;
	}
	
	//새글쓰기------------------------------------------------------------
	//새글 추가
	public int insert( BoardVO vo ) {
		int res = sqlSession.insert("board.board_insert", vo);
		return res;
	}

	//공통 사항------------------------------------------------------------
	//조회수 증가
	public int update_readhit( int idx ) {
		int res = sqlSession.update("board.board_update_readhit", idx);
		return res;
	}

	//전체 게시물 조회
	public List<BoardVO> selectList(){
		List<BoardVO> list = sqlSession.selectList("board.board_list");
		return list;
	}
	
	//step수정
	public int update_step( BoardVO baseVO ) {
		int res = sqlSession.update("board.board_update_step", baseVO);
		return res;
	}
	
}
