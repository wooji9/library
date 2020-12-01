package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SeatVO;

public class SeatDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<SeatVO> selectMult() {
		List<SeatVO> list = null;
		
		list=sqlSession.selectList("s.selectMult");
		return list;
	}
	
	public List<SeatVO> selectRead() {
		List<SeatVO> list = null;
		
		list=sqlSession.selectList("s.selectRead");
		return list;
	}
	public	SeatVO checkT1(int idx) {
		SeatVO seat=null;
		
		seat=sqlSession.selectOne("s.checkT2",idx);
		
		if(seat==null){
			System.out.println("seat은 null -> 생성자로");
			System.out.println("=======================");
			SeatVO seat_null = new SeatVO();
			return seat_null;
		}
		
		System.out.println("현재9시-12시");
		System.out.println("seat.getSeat_idx :"+seat.getSeat_idx());
		System.out.println("seat.getT1_mem_idx :"+seat.getT1_mem_idx());
		System.out.println("=======================");
		return seat;
	}
	
	public	SeatVO checkT2(int idx) {
		SeatVO seat=null;
		
		seat=sqlSession.selectOne("s.checkT2",idx);
		
		if(seat==null){
			System.out.println("seat은 null -> 생성자로");
			System.out.println("=======================");
			SeatVO seat_null = new SeatVO();
			return seat_null;
		}
		
		System.out.println("현재 12시-15시");
		System.out.println("seat.getSeat_idx :"+seat.getSeat_idx());
		System.out.println("seat.getT2_mem_idx :"+seat.getT2_mem_idx());
		System.out.println("=======================");
		return seat;
	}
	
//	private SeatVO SeatVO(int i, int j, int k, int l) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public	SeatVO checkT3(int idx) {
		SeatVO seat=null;
		
		seat=sqlSession.selectOne("s.checkT3",idx);
		
		if(seat==null){
			System.out.println("seat은 null -> 생성자로");
			System.out.println("=======================");
			SeatVO seat_null = new SeatVO();
			return seat_null;
		}
		
		System.out.println("seat에 값 잘 저장됨");
		
		System.out.println("현재 15시-18시");
		System.out.println("seat.getSeat_idx :"+seat.getSeat_idx());
		System.out.println("seat.getT3_mem_idx :"+seat.getT3_mem_idx());
		System.out.println("=======================");
		return seat;
	}
	
	public	SeatVO selectOne(int seat_idx) {
		SeatVO seat=null;
		
		seat=sqlSession.selectOne("s.selectOne",seat_idx);
		
		return seat;
	}
	
	public int book(SeatVO seat) {
		System.out.println("2");
		int r = sqlSession.update("s.book",seat);
		System.out.println("3");
		return r;
	}
	public int count1(int m_idx) {
		int count=sqlSession.selectOne("s.count1",m_idx);
		return count;
	}
	public int count2(int m_idx) {
		int count=sqlSession.selectOne("s.count2",m_idx);
		return count;
	}
	public int count3(int m_idx) {
		int count=sqlSession.selectOne("s.count3",m_idx);
		return count;
	}
}















