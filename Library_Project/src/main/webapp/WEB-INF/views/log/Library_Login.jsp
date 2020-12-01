<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ !empty sessionScope.user }">
	<script>
		alert("로그아웃 후 이용해주세요.");
		location.href="main.do";
	</script>
</c:if>
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
      <div class="body_head_login"><h1>로그인</h1></div>
      <div class="body_content_login">
	      <div class="tit_wrap_login">
		      <h1>두루미 도서관 </h1>
		      <p><span class="subtit_login">CRAME LIBRARY</span></p>
		      <p>두루미도서관의 회원이 되시면 더욱 많은 혜택과 서비스를 이용하실 수 있습니다.</p>
	      </div>
	      <form id="login_frm">
		      <div class="scrollable">
		      	<div class="cont_login">
			        <table>
			          <tbody>
			            <tr>
			              <th align="center"><i class="fas fa-user rgtspace-5"></i></th>
			              <td><input type="text" id="id" name="id"
			              			 class="text" title="아이디" placeholder="아이디"></td>
			            </tr>
			            <tr>
			              <th align="center"><i class="fa-key fa-fw fas"></i></th>
			              <td><input type="password" id="pwd" name="pwd"
			              			 class="text" title="비밀번호" placeholder="비밀번호" autocomplete="off"
									 onkeyup="enterkey();"></td>
			            </tr>
			          </tbody>
			        </table>
		      	</div>
		        <input type="button" onclick="login_Go();" class="btn-d btn-red btn-login" title="로그인 페이지로 이동" value="로그인">
		        <div>
			        <p>아직 두루미 도서관의 회원이 아니신가요? <a href="register_join.do">회원가입</a>.</p>
			        <p>아이디/비밀번호를 잊으셨나요? <a href="idpwd_find_form.do">아이디/비밀번호 찾기</a>.</p>
		        </div>
		      </div>
	      </form>
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