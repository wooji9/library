<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Template Name: Bistup
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Copyright: OS-Templates.com
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html lang="">
<!-- To declare your language - read more here: https://www.w3.org/International/questions/qa-html-language-declarations -->
<head>
<title>두루미 도서관</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="${pageContext.request.contextPath}/resources/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script type="text/javascript">
	function enterkey() {
		if( window.event.keyCode == 13 ){
			//엔터키 눌렀을때 실행할 내용
			login_Go();
		}
	}

	function login_Go() {
		var f = document.getElementById("login_frm");
		var id = f.id.value.trim();
		var pwd = f.pwd.value.trim();
		
		if( id == "" ){
			alert("아이디를 입력해주세요.");
			f.id.focus();
			return;
		}
		
		if( pwd == "" ){
			alert("비밀번호를 입력해주세요.");
			f.pwd.focus();
			return;
		}
		
		f.method = "post";
		f.action = "login_check_form.do";
		f.submit();
		
	}
		
</script>
</head>
<body id="top">
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<jsp:include page="../Library_Menu_Top.jsp"/>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper bgded overlay" style="background-image:url('${pageContext.request.contextPath}/resources/images/demo/backgrounds/01.png');">
  <div id="breadcrumb" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <h6 class="heading">두루미 도서관</h6>
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="main.do">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="sidebar one_quarter first"> 
      <!-- ################################################################################################ -->
      <h6>회원 공간</h6>
      <nav class="sdb_holder">
        <ul>
          <li><a href="login_form.do">로그인</a></li>
          <li><a href="register_join.do">회원가입</a></li>
          <li><a href="idpwd_find_form.do">아이디/비밀번호 찾기</a></li>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="content three_quarter"> 
      <!-- ################################################################################################ -->
      <h1>아이디/비밀번호 찾기</h1>
      <div>
	      <p>아이디/비밀번호 찾기</p>
	      <p>도서관 정회원(회원카드 발급한 자)중 이메일 인증은 정상적으로 되나 아이디/비밀번호를 찾을 수 없는 경우<br> 신분증을 지참하여 도서관을 방문해주세요.
							<span>실명확인을 하신 후</span> 정상적으로 아이디/비밀번호 찾기를 하실 수 있습니다.</p>
	      <div>
		      <p class="tit">이메일로 찾기</p>
			  <a href="#"
			  onclick="window.open('idpwd_find_window.do','existId','left=0, top=0, width=520,height=400,toolboars=no,resizble=no,scrollbars=yes')" 
			  target="_blank" class="btn-d" title="이메일 인증(새창)">이메일 인증</a>
			  <p class="txt">휴대폰인증은 네이버(주)에서 제공되며, 타인의 명의를 도용하실 경우 관련법령에 따라 처벌 받으실 수 있습니다.</p>
	      </div>
      </div>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<jsp:include page="../Library_Menu_Footer.jsp"/>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
<!-- JAVASCRIPTS -->
<script src="${pageContext.request.contextPath}/resources/layout/scripts/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layout/scripts/jquery.backtotop.js"></script>
<script src="${pageContext.request.contextPath}/resources/layout/scripts/jquery.mobilemenu.js"></script>
</body>
</html>