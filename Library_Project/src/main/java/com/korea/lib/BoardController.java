
package com.korea.lib;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.BoardDAO;
import util.Paging;
import util.View_Path;
import vo.BoardVO;

@Controller
public class BoardController {

	BoardDAO board_dao;
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	//공지사항 
	@RequestMapping("/board.do")
	public String list( Model model, Integer page ) {

		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}

		int start = (nowPage - 1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List<BoardVO> list = null;

		list = board_dao.selectList( map );

		int row_total = board_dao.getRowTotal();

		String pageMenu = Paging.getPaging("board.do", nowPage, 
				row_total, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);

		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);

		request.getSession().removeAttribute("board_view");

		return View_Path.board_View.VIEW_PATH + "board_list.jsp";

	}
	
	//공지사항 상세보기
	@RequestMapping("/board_view.do") 
	public String view(Model model, int idx ) {
		BoardVO vo = board_dao.selectOne( idx );
		
		//조회수 중복 방지
		System.out.println(session.getAttribute("board_view"));
		if(session.getAttribute("board_view") == null) {
			session.setAttribute("board_view", "readhit");
			//조회수 증가
			int res = board_dao.update_readhit(idx);
		}
		
		model.addAttribute("vo", vo);
		return View_Path.board_View.VIEW_PATH + "board_view.jsp";
	}
	
	//게시글 삭제(어드민쪽에서는 데이터값을 가지고있다)
	@RequestMapping("/board_delete.do")
	@ResponseBody public String delete( int idx ) {
		String result = "no";
		//삭제가 된 것 처럼 업데이트를 수행 
		int res = board_dao.del_update(idx);
	
		if( res == 1 ) {
			result = "yes"; 
		}
	
		return result;
	}
	
	//새글 쓰는 폼
	@RequestMapping("/board_insert_form.do") 
	public String insert_form() { 
		return	View_Path.board_View.VIEW_PATH + "board_write.jsp"; 
	}
	//새글쓰기
	@RequestMapping("/board_insert.do") 
	public String insert( Model model, BoardVO vo) {
		//ip얻기
		String ip = request.getRemoteAddr();
		//ip vo에 저장
		vo.setIp(ip);
		
		board_dao.insert(vo); 
		return "redirect:board.do";
	}
	  
}
