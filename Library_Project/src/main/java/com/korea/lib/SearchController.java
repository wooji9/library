package com.korea.lib;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dao.ResolceDAO;
import util.View_Path;
import vo.LentVO;
import vo.MemberVO;
import vo.Resolce_SearchVO;

@Controller
public class SearchController {
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	HttpSession session;
	
	
	ResolceDAO resolceDAO;
	public void setResolceDAO(ResolceDAO resolceDAO) {
		this.resolceDAO = resolceDAO;
	}
	//전체 책정보 검색
	@RequestMapping("/search.do")
	public String search(Model model) {
		List<Resolce_SearchVO> list = resolceDAO.select();
		model.addAttribute("list",list);
		return View_Path.Search_View.VIEW_PATH + "resolce_search.jsp";
	}
	
	//원하는 책 정보를 검색
	@RequestMapping("/search_view.do")
	public String search_view(Model model, String curlum, String search) {
		List<Resolce_SearchVO> list = null;
		if(curlum.equals("book_name")) {
			list = resolceDAO.select_bName(search);
		}else if(curlum.equals("category")) {
			list = resolceDAO.select_category(search);
		}else if(curlum.equals("company")) {
			list = resolceDAO.select_company(search);
		}else if(curlum.equals("isbl")) {
			list = resolceDAO.select_isbl(search);
		}else if(curlum.equals("book_year")) {
			list = resolceDAO.select_bYear(search);
		}
		
		model.addAttribute("list",list);
		return View_Path.Search_View.VIEW_PATH + "resolce_search.jsp";
	}
	
	//상세보기 페이지로이동
	@RequestMapping("/lent_page.do")
	public String lent(String isbl, Model model) {
		Resolce_SearchVO vo = resolceDAO.selectOne(isbl);
		model.addAttribute("vo", vo);
		return View_Path.Search_View.VIEW_PATH + "lent_page.jsp";
	}
	
	//예약 페이지로 이동
	@RequestMapping("/reserve_lent.do")
	public String reserve(Model model, String isbl, String m_id) {
		MemberVO vo = resolceDAO.selectID(m_id); 
		Resolce_SearchVO b_vo = resolceDAO.selectOne(isbl);
		model.addAttribute("vo", vo);
		model.addAttribute("b_vo", b_vo);
		return View_Path.Search_View.VIEW_PATH + "reserve_lent.jsp";
	}
	
	//예약 DB저장
	@RequestMapping("/book_lent.do")
	public String book_lent(LentVO lentvo, Model model) {
		
			//DB에 추가완료
			int res = resolceDAO.insertLent(lentvo);
			if(res == 1) {
				model.addAttribute("res",res);
				return View_Path.Search_View.VIEW_PATH + "reserve.jsp";
			}
		return "redirect:search.do";
	}
	
	//신규책 DB에 추가하기
	@RequestMapping("/new_book.do")
	public String new_book(Resolce_SearchVO vo) throws IllegalStateException, IOException {
		System.out.println("저자" + vo.getAuthor());
		String result ="";
		int res = resolceDAO.insertBook(vo);
			if(res == 1) {
				//이미지 업로드
				String webPath = "/resources/images/book_img/";
				String savePath = application.getRealPath(webPath);
				System.out.println(savePath);
				
				//업로드 될 파일의 정보
				MultipartFile photo = vo.getPhoto();
				String filename = "no_file";
				//파일이 정상적으로 업로드 되었다면..
				if( !photo.isEmpty() ) {
					
					//업로드 된파일에 실제 파일명
					filename = vo.getIsbl();
					
					//저장할 파일경로 생성하기
					File saveFile = new File(savePath, filename);
					if( !saveFile.exists() ) {
						saveFile.mkdirs();
					}else {
						//동일한 파일명이 존재할경우 현재 업로드 시간을 파일명 뒤에 붙여서
						//중복을 방지해준다.
			//			long time = System.currentTimeMillis();
			//			filename = String.format("%d_%s", time, filename);
			//			saveFile = new File( savePath, filename);
						saveFile.mkdirs();
					}
					
					//업로드 된 파일은 MultipartResolver클래스가 지정해놓은 임시 저장소에 자동으로 들어간다
					//임시저장소에 있는 파일은 일정기간이 지나면 자동으로 삭제되기 떄문에 개발자가지정해준 경로로 파일을 복사해둬야 한다.
					photo.transferTo(saveFile);
			}
		}//if
		
		return View_Path.Search_View.VIEW_PATH + "new_book_check.jsp";
	}
	//최근 1주일간 추가된 새책 보기
	@RequestMapping("/newbook_week.do")
	public String newbook_week(Model model) {
		List<Resolce_SearchVO> list = resolceDAO.selectTime();
		model.addAttribute("list", list);
		
		return View_Path.Search_View.VIEW_PATH + "newbook_week.jsp";
	}
	
	//예약된책 대여로 업데이트 해주기
	@RequestMapping("/lent_update.do")
	public String lent_update(int idx) {
		int res = resolceDAO.lentupdate(idx);
		return "redirect:admin_lent_form.do";
	}
	//대여된책 반납으로 업데이트
	@RequestMapping("/rt_update.do")
	public String rt_update(int idx) {
		int res = resolceDAO.rtupdate(idx);
		return "redirect:admin_lent_form.do";
	} 
	//예약 취소해주기
	@RequestMapping("/cancel_update.do")
	public String cancel_update(int idx) {
		int res = resolceDAO.cancelupdate(idx);
		return "redirect:admin_lent_form.do";
	}
	
	
}
























