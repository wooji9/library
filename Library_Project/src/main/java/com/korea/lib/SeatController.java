package com.korea.lib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.SeatDAO;
import util.View_Path;
import vo.MemberVO;
import vo.SeatVO;

@Controller
public class SeatController {

	SeatDAO seat_dao;
	//Public Static String VIEW_PATH = "" 
	public void setSeat_dao(SeatDAO seat_dao) {
		this.seat_dao = seat_dao;
	}
	
	@Autowired
	HttpServletRequest request;
	
	//예약check해 다음화면전환 //로그인확인도 추가해야함
	   @RequestMapping("/book.do")
	   public String main_to_book(Integer m_idx, Model model) {
	      
	      if(m_idx == null) {
	        return "redirect:login_faile.do";
	    	  /////////////////////////////////////////
	    		//세션에서 받아온 member idx
	  	    
	      }
	      
	      Date date_now = new Date(System.currentTimeMillis()); 
	      SimpleDateFormat h = new SimpleDateFormat("HH"); 
	      int hour = Integer.parseInt(h.format(date_now));
	      //int hour = 16;
	      
	      SeatVO seat = null;
	      Boolean hasSeat=false;
	      if(9<=hour&&hour<12) {
	         seat = seat_dao.checkT1(m_idx);
	         if(seat.getT1_mem_idx()>0)
	            hasSeat=true;
	      }else if(12<=hour&&hour<15) {
	         seat = seat_dao.checkT2(m_idx);
	         if(seat.getT2_mem_idx()>0)
	            hasSeat=true;
	      }else if(15<=hour&&hour<18) {
	         seat = seat_dao.checkT3(m_idx);
	         if(seat.getT3_mem_idx()>0) 
	            hasSeat=true;
	      }
	      
	      model.addAttribute("seat", seat);
	      
	      HttpSession session = request.getSession();
	      session.setAttribute("hour"   , hour);
	      session.setAttribute("hasSeat", hasSeat);
	      
	      System.out.println("현재 사용중인가 : "+hasSeat);
	      System.out.println("=======================");
	      
	      if(hasSeat) {
	         //현재 예약 있음
	          return View_Path.Visit_View.VIEW_PATH + "booked.jsp";
	      }else {
	         //현재 예약 없음
	          return View_Path.Visit_View.VIEW_PATH + "select_room.jsp";
	      }
	      //if(empty openSession)
	   }
	
	@RequestMapping("/curr_booked.do")
	public String curr_booked() {
		return View_Path.Visit_View.VIEW_PATH+"select_room.jsp";
	}
		
	//좌석선택 화면으로
	@RequestMapping("/to_select_mult.do")
	public String to_select_mult(Model model) {
		List<SeatVO> list = seat_dao.selectMult();
		model.addAttribute("list",list);
		return View_Path.Visit_View.VIEW_PATH+"select_mult.jsp";
	}
	
	@RequestMapping("/to_select_read.do")
	public String to_select_read(Model model) {
		List<SeatVO> list = seat_dao.selectRead();
		model.addAttribute("list",list);
		return View_Path.Visit_View.VIEW_PATH+"select_read.jsp";
	}
	
	@RequestMapping("/to_select_time.do")
	public String to_select_time(int seat_idx, Model model) {
		SeatVO seat = seat_dao.selectOne(seat_idx);
		model.addAttribute("seat",	seat);
		return View_Path.Visit_View.VIEW_PATH+"select_time.jsp";
	}
	
	@RequestMapping("/time_book.do")
	public String time_book(int seat_idx, int m_idx) {
		
		String[] times = request.getParameterValues("time");
		
		SeatVO tmp = new SeatVO();
		//HttpSession session = request.getSession();
		
		tmp.setSeat_idx(seat_idx);
		
		for(int i=0; i<times.length;i++) {
		 if(times[i].equals("1")) {
			 if(seat_dao.count1(m_idx)>0) {
				 return View_Path.Visit_View.VIEW_PATH+"booked_alert.jsp";
			 }
			 tmp.setT1_mem_idx(m_idx);	
			 //같은시간에 예약있으면 에러발생
		 }
		 if(times[i].equals("2")) {
			 if(seat_dao.count2(m_idx)>0) {
				 return View_Path.Visit_View.VIEW_PATH+"booked_alert.jsp";
			 }
			 tmp.setT2_mem_idx(m_idx);		 
			 }
		 if(times[i].equals("3")) {
			 if(seat_dao.count3(m_idx)>0) {
				 return View_Path.Visit_View.VIEW_PATH+"booked_alert.jsp";
			 }
			 tmp.setT3_mem_idx(m_idx);		 
			 }
		}
		
		//같은시간에 예약있으면 에러
	
		
		System.out.println("seat_idx:"+seat_idx);
		System.out.println("idx:"+m_idx);
		System.out.println(tmp.getT1_mem_idx()+"t1");
		System.out.println(tmp.getT2_mem_idx()+"t2");
		System.out.println(tmp.getT3_mem_idx()+"t3");
		System.out.println("1");
		int res= seat_dao.book(tmp);	//예약 완료
		System.out.println("4");
		return "redirect:book_main.do";
	}
	
	//하루지나면 예약 풀리는거 구현해야함
	
	

}















