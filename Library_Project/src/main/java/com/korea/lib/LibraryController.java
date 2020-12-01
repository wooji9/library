package com.korea.lib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MemberDAO;
import service.MemberService;
import util.View_Path;
import vo.BoardVO;
import vo.GongjiVO;
import vo.LentVO;
import vo.MemberVO;
import vo.Resolce_SearchVO;

@Controller
public class LibraryController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;
	
	@Autowired
	MemberDAO member_dao;
	
	@Autowired
	MemberService member_service;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//메인화면-----------------------------------------------------------------------
	@RequestMapping(value= {"/", "/main.do"})
	public String main_View() {
		return View_Path.Main_View.VIEW_PATH + "Library_Main.jsp";
	}
	
	//관리자 페이지-----------------------------------------------------------------------
	//메인화면
	@RequestMapping("/admin_main.do")
	public String admin_main_form() {
		return View_Path.Admin_View.VIEW_PATH + "Admin_Main.jsp";
	}
	//책======================================================
	//기존 및 신규책 관리
	@RequestMapping("/admin_book_form.do")
	public String admin_book_form(Model model) {
		//책의 모든 정보를 가지고 온다
		List<Resolce_SearchVO> book_List = member_dao.admin_book_list();
		model.addAttribute("book_List", book_List);
		
		return View_Path.Admin_View.VIEW_PATH + "Admin_Book.jsp";
	}
	//신규책 추가 페이지
	@RequestMapping("/admin_book_window_form.do")
	public String admin_book_window_form() {
		return View_Path.Admin_View.VIEW_PATH + "Admin_Book_Window.jsp";
	}
	
	//대여 반납 관리
	@RequestMapping("/admin_lent_form.do")
	public String admin_lent_form(Model model) {
		//lent테이블의 모든 정보를 가지고 온다
		List<LentVO> lent_List = member_dao.admin_lent_list();
		model.addAttribute("lent_List", lent_List);
				
		return View_Path.Admin_View.VIEW_PATH + "Admin_Lent.jsp";
	}
	//회원======================================================
	//회원관리 폼
	@RequestMapping("/admin_user_form.do")
	public String admin_user_form(Model model) {
		//모든 유저의 정보를 가지고 온다
		List<MemberVO> user_List = member_dao.admin_user_list();
		model.addAttribute("user_List", user_List);
		
		return View_Path.Admin_View.VIEW_PATH + "Admin_User.jsp";
	}
	//회원 정보 수정폼
	@RequestMapping("/admin_user_update_window_form.do")
	public String admin_user_update_window_form(int m_idx) {
		MemberVO user = member_dao.idx_select(m_idx);
		request.setAttribute("vo", user);
		return View_Path.Admin_View.VIEW_PATH + "Admin_User_Update_Window.jsp";
	}
	//회원정보 수정
	@RequestMapping("/admin_user_update.do")
	public String admin_user_update(MemberVO vo) {
		//해당 idx로 검색
		int res = 0;
		//System.out.println("vo : " + vo.getM_idx());		
		//System.out.println("vo : " + vo.getId());		
		//System.out.println("vo : " + vo.getPwd());		
		//System.out.println("vo : " + vo.getName());		
		//System.out.println("vo : " + vo.getTel());		
		//System.out.println("vo : " + vo.getGen());		
		//System.out.println("vo : " + vo.getPostcode());		
		//System.out.println("vo : " + vo.getAddr());		
		//System.out.println("vo : " + vo.getExtraaddr());		
		//System.out.println("vo : " + vo.getEmail());		
		//비밀번호 변경 x
		if( vo.getPwd().equals("") || vo.getPwd() == null ) {
			System.out.println("비밀번호 변경x");
			res = member_dao.user_update_nopwd(vo);
					
			return "redirect:admin_user_update_check.do";
		}
		
		//비밀번호 변경
		System.out.println("기존 pwd : " + vo.getPwd());
		//비밀번호 암호화
		String pwd = bcryptPasswordEncoder.encode(vo.getPwd());
		vo.setPwd(pwd);
		System.out.println("인코딩 pwd : " + pwd);
				
		//비밀번호 변경
		res = member_dao.user_update_yespwd(vo);
		
		return "redirect:admin_user_update_check.do";
	}
	//회원 수정 확인 폼
	@RequestMapping("/admin_user_update_check.do")
	public String admin_user_update() {
		return View_Path.Admin_View.VIEW_PATH + "Admin_User_Update_Check.jsp";
	}
	//회원 삭제(데이터는 남아있다)
	@RequestMapping("/admin_user_del.do")
	public String admin_user_del(int m_idx) {
		int res = member_dao.admin_user_del(m_idx);
		return "redirect:admin_user_form.do";
	}
	//회원 삭제 복구
	@RequestMapping("/admin_user_restore.do")
	public String admin_user_restore(int m_idx) {
		int res = member_dao.admin_user_restore(m_idx);
		return "redirect:admin_user_form.do";
	}
	//열린마당======================================================
	//건의사항 관리폼*************************************************
	@RequestMapping("/admin_board_qa_form.do")
	public String admin_board_qa_form(Model model) {
		//건의사항 정보 가져오기
		List<BoardVO> board_List = member_dao.admin_board_qa_list();
		model.addAttribute("board_List", board_List);
		return View_Path.Admin_View.VIEW_PATH + "Admin_Board_QA.jsp";
	}
	//선택 건의사항 글 보기
	@RequestMapping("/admin_board_view_window_form.do")
	public String admin_board_view_window_form(int idx, Model model) {
		BoardVO board = member_dao.admin_board_selectone(idx);
		model.addAttribute("board", board);
		return View_Path.Admin_View.VIEW_PATH + "Admin_Board_View_Window.jsp"; 
	}
	//건의사항 선택 글 삭제하기
	@RequestMapping("/admin_board_del.do")
	public String admin_board_del(int idx) {
		int res = member_dao.admin_board_del(idx);
		return "redirect:admin_board_qa_form.do";
	}
	//건의사항 삭제된 글 복구하기
	@RequestMapping("/admin_board_restore.do")
	public String admin_board_restore(int idx) {
		int res = member_dao.admin_board_restore(idx);
		return "redirect:admin_board_qa_form.do";
	}
	//공지사항 관리 폼**************************************************
	@RequestMapping("/admin_gongji_form.do")
	public String admin_gongji_form(Model model) {
		//공지사항 정보 가져오기
		List<GongjiVO> gongji_List = member_dao.admin_gongji_list();
		model.addAttribute("gongji_List", gongji_List);
		return View_Path.Admin_View.VIEW_PATH + "Admin_Gongji.jsp";
	}
	//선택 공지사항 글 수정 폼
	@RequestMapping("/admin_gongji_update_window_form.do")
	public String admin_gongji_update_window_form(int idx, Model model) {
		GongjiVO gongji = member_dao.admin_gongji_selectone(idx);
		model.addAttribute("vo", gongji);
		return View_Path.Admin_View.VIEW_PATH + "Admin_Gongji_Update_Window.jsp"; 
	}
	//선택 공지사항 글 수정
	@RequestMapping("/admin_gongji_update.do")
	@ResponseBody
	public String admin_gongji_update(GongjiVO vo) {
		System.out.println(vo.getName());
			
		//디비에 정보 저장
		int res = member_dao.admin_gongji_update(vo);
		String result ="no";
		
		//저장 유무
		if(res == 1) {
			result = "yes";
		}
			
		return result;
	}
	//공지사항 선택 글 삭제
	@RequestMapping("/admin_gongji_del.do")
	public String admin_gongji_del(int idx) {
		int res = member_dao.admin_gongji_del(idx);
		return "redirect:admin_gongji_form.do";
	}
	//공지사항 선택 글 복구
	@RequestMapping("/admin_gongji_restore.do")
	public String admin_gongji_restore(int idx) {
		int res = member_dao.admin_gongji_restore(idx);
		return "redirect:admin_gongji_form.do";
	}
	//공지사항 선택 글 공지글로 변경
	@RequestMapping("/admin_gongji_main.do")
	public String admin_gongji_main(int idx) {
		int res = member_dao.admin_gongji_main(idx);
		return "redirect:admin_gongji_form.do";
	}
	//공지사항 글 작성 폼
	@RequestMapping("/admin_gongji_insert_window_form.do")
	public String admin_gongji_insert_window_form() {
		return View_Path.Admin_View.VIEW_PATH + "Admin_Gongji_Insert_Window.jsp"; 
	}
	//공지사항 글 작성
	@RequestMapping("/admin_gongji_insert.do")
	@ResponseBody
	public String admin_gongji_insert(GongjiVO vo) {
		
		//관리자 이름 변경
		String name = vo.getName() + " 관리자";
		vo.setName(name);
		//System.out.println(vo.getName());
		//System.out.println(vo.getContent());
		//System.out.println(vo.getName());
		//System.out.println(vo.getTitle());
		
		//디비에 정보 저장
		int res = member_dao.admin_gongji_insert(vo);
		String result ="no";
	
		//저장 유무
		if(res == 1) {
			result = "yes";
		}
		
		return result;
	}

	//로그인-------------------------------------------------------------------------
	//로그인 폼
	@RequestMapping("/login_form.do")
	public String login_form(String id, String pwd){
		return View_Path.Log_View.VIEW_PATH + "Library_Login.jsp"; 
	}
	//로그인 확인 폼
	@RequestMapping("/login_check_form.do")
	public String login_check_form(String id, String pwd, Model model) {
		
		//id가 일치하는 유저의 정보 가지고오기
		MemberVO user = member_dao.id_check(id);
				
		String check = "";
		
		//id가 일지하지 않는 경우 or 회원탈퇴 및 벤이 된 아이디인 경우
		if( user == null || user.getRegi() == 1) {
			check = "no_id";
			model.addAttribute("check", check);
			return View_Path.Log_View.VIEW_PATH + "Library_Login_Check.jsp"; 
		}
		
		System.out.println("pwd : " + pwd);
		System.out.println("user : " + user.getPwd());
		System.out.println(bcryptPasswordEncoder.matches(pwd, user.getPwd()));
		System.out.println(user.getM_idx());
		//pwd가 일치하지 않는다면...
		if(user.getM_idx() < 1) {
			//관리자
			if(!user.getPwd().equals(pwd)) {
				check = "no_pwd";
				model.addAttribute("check", check);
				return View_Path.Log_View.VIEW_PATH + "Library_Login_Check.jsp"; 
			}
		}else {
			//일반 고객들
			if( !bcryptPasswordEncoder.matches(pwd, user.getPwd()) ) {
				check = "no_pwd";
				model.addAttribute("check", check);
				return View_Path.Log_View.VIEW_PATH + "Library_Login_Check.jsp"; 
			}
		}
				
		//아이디와 비밀번화 일치, 즉 로그인 성공
		//session에 정보 저장
		session = request.getSession();
		session.setAttribute("user", user);
						
		//세션 유지시간
		session.setMaxInactiveInterval(60 * 60);//1시간
		
		return View_Path.Log_View.VIEW_PATH + "Library_Login_Check.jsp"; 
	}
	//로그아웃 폼
	@RequestMapping("/logout_form.do")
	public String logout_form() {
		return View_Path.Log_View.VIEW_PATH + "Library_Logout.jsp"; 
	}
	//로그아웃
	@RequestMapping("/logout.do")
	public String logout() {
		//세션에 등록되어 있는 데이터 삭제
		session.removeAttribute("user");
		return "main.do";
	}
	
	
	//회원가입------------------------------------------------------------------------
	//회원가입 조인
	@RequestMapping("/register_join.do")
	public String register_join(){
		return View_Path.Regi_View.VIEW_PATH + "Library_Regi_Join.jsp"; 
	}
	//회원가입 폼
	@RequestMapping("/register_form.do")
	public String register_from() {
		return View_Path.Regi_View.VIEW_PATH + "Library_Regi.jsp";
	}
	//회원가입
	@RequestMapping("/register.do")
	public String register(MemberVO vo) {
		
		System.out.println(vo.getTel());
		System.out.println(vo.getExtraaddr());
		
		System.out.println("기존 pwd : " + vo.getPwd());
		//비밀번호 암호화
		String pwd = bcryptPasswordEncoder.encode(vo.getPwd());
		vo.setPwd(pwd);
		System.out.println("인코딩 pwd : " + pwd);
		
		int res = member_dao.regi_insert(vo);

		return "register_check.do";
	}
	//회원가입 확인 폼
	@RequestMapping("/register_check.do")
	public String register_check(){
		return View_Path.Regi_View.VIEW_PATH + "Library_Regi_Check.jsp";
	}
	
	//아이디 비밀번호 찾기---------------------------------------------------------------------
	//아이디 비밀번호 찾기 폼
	@RequestMapping("/idpwd_find_form.do")
	public String idpwd_find_form() {
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Idpwd_Find.jsp";
	}
	//아이디 비밀번호 찾기 창
	@RequestMapping("/idpwd_find_window.do")
	public String idpwd_find_window() {
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Idpwd_Find_Window.jsp";
	}
	//아이디 찾기
	@RequestMapping("/id_search.do")
	@ResponseBody
	public String id_search(MemberVO vo) {
		
		MemberVO user = member_dao.id_search(vo);
		
		String res = "no";
		String result = "";
		
		//id 체크
		if(user != null) {
			res = "yes";
			result = String.format(
					"[{'res':'%s'}, {'m_idx':'%s'}, {'email':'%s'}]",
					res, user.getM_idx(), user.getEmail());
			return result;
		}
		
		result = String.format("[{'res':'%s'}]", res);
        
		return result;
	}
	//id찾기 이메일 인증 폼
	@RequestMapping("/id_search_check_form.do")
	public String id_search_check_form(Model model, String email, String m_idx) {
		model.addAttribute("email", email);
		model.addAttribute("m_idx", m_idx);
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Idsearch_Check.jsp";
	}
	//인증완료후 아이디 찾기
	@RequestMapping("id_search_find.do")
	public String id_search_find(int m_idx, Model model) {

		MemberVO user = member_dao.idx_select(m_idx);
		model.addAttribute("user", user);
		
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Idsearch_Find.jsp";
	}
	//비밀번호 찾기
	@RequestMapping("/pwd_search.do")
	@ResponseBody
	public String pwd_search(MemberVO vo) {
		MemberVO user = member_dao.pwd_search(vo);
		
		String res = "no";
		String result = "";
		
		//id 체크
		if(user != null) {
			res = "yes";
			result = String.format(
					"[{'res':'%s'}, {'m_idx':'%s'}, {'email':'%s'}]",
					res, user.getM_idx(), user.getEmail());
			return result;
		}
		
		result = String.format("[{'res':'%s'}]", res);
        
		return result;
	}
	//pwd 찾기 이메일 인증
	@RequestMapping("/pwd_search_check_form.do")
	public String pwd_search_check_form(Model model, String email, String m_idx) {
		model.addAttribute("email", email);
		model.addAttribute("m_idx", m_idx);
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Pwdsearch_Check.jsp";
	}
	//비밀번호 변경 폼
	@RequestMapping("/pwd_search_change_form.do")
	public String id_search_change_form(int m_idx, Model model) {
		model.addAttribute("m_idx", m_idx);
		
		return View_Path.Idpwd_View.VIEW_PATH + "Library_Pwdsearch_Change.jsp";
	}
	//비밀번호 변경
	@RequestMapping("/pwd_search_change.do")
	@ResponseBody
	public String pwd_search_change(MemberVO vo) {
		
		System.out.println("기존 pwd : " + vo.getPwd());
		//비밀번호 암호화
		String pwd = bcryptPasswordEncoder.encode(vo.getPwd());
		vo.setPwd(pwd);
		System.out.println("인코딩 pwd : " + pwd);
		
		int res = member_dao.pwd_update(vo);
		String result = "no";
		
		if(res == 1) {
			result = "yes";
		}
		
		return result;
	}
	
	//내서재------------------------------------------------------------------------------
	//개인정보 변경
	@RequestMapping("/user_update.do")
	public String user_update( MemberVO vo ) {
		//해당 idx로 검색
		MemberVO user;
		int res = 0;
		
		//비밀번호 변경 x
		if( vo.getPwd().equals("no_change") ) {
			System.out.println("비밀번호 변경x");
			res = member_dao.user_update_nopwd(vo);
			
			user = member_dao.idx_select(vo.getM_idx());
			//session에 정보 저장
			session = request.getSession();
			session.setAttribute("user", user);
			
			return "redirect:main.do";
		}
		
		//비밀번호 변경
		System.out.println("기존 pwd : " + vo.getPwd());
		//비밀번호 암호화
		String pwd = bcryptPasswordEncoder.encode(vo.getPwd());
		vo.setPwd(pwd);
		System.out.println("인코딩 pwd : " + pwd);
		
		//비밀번호 변경
		res = member_dao.user_update_yespwd(vo);
		
		user = member_dao.idx_select(vo.getM_idx());
		user.setPwd(pwd);
		//session에 정보 저장
		session = request.getSession();
		session.setAttribute("user", user);
		
		return "redirect:main.do";
	}
	//개인정보 변경 폼
	@RequestMapping("/user_update_form.do")
	public String user_update_form() {
		return View_Path.User_View.VIEW_PATH + "Library_User_Update.jsp";
	}
	//대출이력 조회 폼
	@RequestMapping("/user_lent_search_form.do")
	public String user_lent_search_form(String m_id, Model model) {
		List<LentVO> lent_List = member_dao.user_lent_search(m_id);
		model.addAttribute("lent_List", lent_List);
		return View_Path.User_View.VIEW_PATH + "Library_User_Lent_Search.jsp";
	}
	//도서예약 신청 내역 폼
	@RequestMapping("/user_book_reserve_form.do")
	public String user_book_reserve_form() {
		return View_Path.User_View.VIEW_PATH + "Library_User_Book_Reserve.jsp";
	}
	//회원 탈퇴 폼
	@RequestMapping("/user_del_form.do")
	public String user_del_form() {
		return View_Path.User_View.VIEW_PATH + "Library_User_Del.jsp";
	}
	//회원 탈퇴
	@RequestMapping("/user_del.do")
	public String user_del(MemberVO vo) {
		//idx로 유저의 정보가져오기
		MemberVO user = member_dao.idx_select(vo.getM_idx());
		
		String result = "";
		//비밀번호 체크
		if( !bcryptPasswordEncoder.matches(vo.getPwd(), user.getPwd()) ) {
			//비밀번호 일치하지 않음
			result = "no_pwd";
			request.setAttribute("result", result);
			return View_Path.User_View.VIEW_PATH + "Library_User_Del_Check.jsp";
		}
		
		
		//비밀번호 일치
		result = "success";
		request.setAttribute("result", result);
		
		//회원탈퇴
		int res = member_dao.admin_user_del(vo.getM_idx());
		
		if( res == 1 ) {
			//로그아웃(세션에 등록된 데이터 삭제)
			session.removeAttribute("user");
		}
		
		return View_Path.User_View.VIEW_PATH + "Library_User_Del_Check.jsp";
	}
	
	//이메일 보내기--------------------------------------------------------------------------
	@RequestMapping("/sendmail.do")
	@ResponseBody
	public String cer_Email( String email ) {
		
		/* email = "chounsa5555@naver.com"; */
		System.out.println(email);
		
		//임의의 authKey 생성 & 이메일 발송
        String authKey = member_service.sendAuthMail(email);
		System.out.println(authKey);
        
		return authKey;
	}

	//아이디 중복체크-----------------------------------------------------------------------
	@RequestMapping("/id_check.do")
	@ResponseBody
	public String id_check(String id) {
		//DB에서 id변수에 해당되는 값을 가진 사람이 있는지 검색
		MemberVO vo = member_dao.id_check(id);
				
		String res = "no";
			
		if( vo == null ) {
			//회원가입이 가능한 경우
			res = "yes";
		}
				
		String resultStr = String.format(
						"[{'result':'%s'}, {'id':'%s'}]", res, id);
		
		return resultStr;
	}
	
	//로그인 실패-----------------------------------------------------------------------
	@RequestMapping("/login_faile.do")
	public String login_faile() {
		return View_Path.Log_View.VIEW_PATH + "Library_Login_Faile.jsp";
	}
	
}
