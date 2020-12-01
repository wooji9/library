<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <c:if test="${ empty sessionScope.user }">
	<script>
		alert("로그인 후 이용해주세요.");
		location.href="login_form.do";
	</script>
</c:if> --%>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script>
	function time(i){
		location.href="to_select_time.do?seat_idx="+i;
	}
</script>
</head>
<body id="top">
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row0">
  <div id="topbar" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="fl_left">
      <ul class="nospace">
       <li><a href="main.do"><i class="fas fa-home fa-lg"></i></a></li>
       	<li><a href="hi.do">About</a></li>
        <li><a href="#">Contact</a></li>
        <c:if test="${ empty sessionScope.user }">
        <li><a href="login_form.do">Login</a></li>
        <li><a href="register_join.do">Register</a></li>
        </c:if>
		<c:if test="${ !empty sessionScope.user }">
        <li><a href="logout_form.do">Logout</a></li>
        <c:if test="${ user.m_idx eq 0 }">
        <li><a href="admin_main.do">Admin</a></li>
        </c:if>
        </c:if>
      </ul>
    </div>
    <c:if test="${ empty sessionScope.user }">
	    <div class="fl_right">
	      <ul class="nospace">
	        <li><i class="fas fa-phone rgtspace-5"></i> +82 (010) 2660 6661</li>
	        <li><i class="fas fa-envelope rgtspace-5"></i> dkdl54321@naver.com</li>
	      </ul>
	    </div>
    </c:if>
    <c:if test="${ !empty sessionScope.user }">
	    <div class="fl_right">
	      <ul class="nospace">
	        <li><i class="fas fa-user rgtspace-5"></i> ${user.name}님</li>
	        <li><i class="fas fa-envelope rgtspace-5"></i> ${user.email}</li>
	      </ul>
	    </div>
    </c:if>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row1">
  <header id="header" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="one_quarter first">
      <h1><a href="main.do">두루미 도서관</a></h1>
      <p>행복과 기쁨을 함께하는 우리</p>
    </div>
    <div class="one_quarter"><strong><i class="fas fa-phone rgtspace-5"></i> Call Us:</strong> +82 (010) 2660 6661</div>
    <div class="one_quarter"><strong><i class="far fa-clock rgtspace-5"></i> Mon. - Sat.:</strong> 08.00am - 18.00pm</div>
    <div class="one_quarter">
      <form action="#" method="post">
        <label>
          <select>
            <option value="" selected="selected" disabled="disabled">Language</option>
            <option value="korean">Korean</option>
            <option value="korean">한국어</option>
          </select>
        </label>
      </form>
    </div>
    <!-- ################################################################################################ -->
  </header>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li><a class="drop" href="hi.do">도서관 안내</a>
        <ul>
          <li><a href="hi.do">인사말</a></li>
          <li><a href="come.do">오시는 길</a></li>
          <li><a href="familytree.do">조직도</a></li>
        </ul>
      </li>
      <li><a class="drop" href="search.do">자료검색</a>
        <ul>
          <li><a href="search.do">자료검색</a></li>
          <li><a href="#">신규 책</a></li>
          <li><a href="#">이달의 추천 도서</a></li>
        </ul>
      </li>
      <li><a class="drop" href="list.do">열린마당</a>
        <ul>
          <li><a href="list.do">공지사항</a></li>
          <li><a href="#">희망도서신청</a></li>
          <li><a href="#">건의사항</a></li>
        </ul>
      </li>
      <li><a class="drop" href="book.do?m_idx=${user.m_idx}">열람실</a>
        <ul>
          <li><a href="book.do?m_idx=${user.m_idx}">열람실 예약</a></li>
        </ul>
      </li>
      <li><a class="drop" href="user_lent_search_form.do?m_id=${user.id}">내서재</a>
        <ul>
          <li><a href="user_lent_search_form.do?m_id=${user.id}">대출이력 조회</a></li>
          <li><a href="user_book_reserve_form.do">도서예약 신청내역</a></li>
          <li><a href="user_update_form.do">개인정보변경</a></li>
        </ul>
      </li>
    </ul>
    <!-- ################################################################################################ -->
  </nav>
</div>
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
      <h6>열람실</h6>
      <nav class="sdb_holder">
        <ul>
          <li><a href="book.do?m_idx=${user.m_idx}">열람실 예약</a></li>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="content three_quarter"> 
      <!-- ################################################################################################ -->
      <h1>멀티미디어실 좌석표</h1>
      <%-- idx가 s인 seat의 현재시간 사용중인지 아닌지 
			  모든 네모는 <a/>하이퍼링크 달려있음
			 if문으로 일정 네모마다 띄어쓰기 및 줄바꿈해주기 --%>
		<br>
		<hr>
		<br>
		<p align="center">			 
			<c:choose>
		    	<c:when test="${9<=hour and hour<12}">
		    	<c:set var="t" value="1"/>
		   	  		<c:forEach var="s" items="${ list}">
		    	  	
		    	  		<c:if test="${s.t1_mem_idx < 0 }">
		    	  		<button type="button" class="btn btn-outline-dark btn-lg" onclick="time(${s.seat_idx});"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.t1_mem_idx >= 0 }">
		    	  		<button type="button" class="btn btn-secondary btn-lg" onclick="time(${s.seat_idx});"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.seat_idx%2 == 0 }">&nbsp;&nbsp;&nbsp;&nbsp; </c:if>
					 	<c:if test="${(s.seat_idx)%6 == 0}"><br></c:if> <!-- 6 12 18 24 -->
						
						<c:if test="${t %12 == 0}"><br></c:if>
						<c:set var="t" value="${t+1 }" />
					</c:forEach>
		   	 </c:when>
		
		    	<c:when test="${12<=hour and hour<15}">
		    	<c:set var="t" value="1"/>
		   	  		<c:forEach var="s" items="${ list}">
		    	  	
		    	  		<c:if test="${s.t2_mem_idx < 0 }">
		    	  		<button type="button" class="btn btn-outline-dark btn-lg" onclick="time('${s.seat_idx}');"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.t2_mem_idx >= 0 }">
		    	  		<button type="button" class="btn btn-secondary btn-lg" onclick="time('${s.seat_idx}');"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.seat_idx%2 == 0 }">&nbsp;&nbsp;&nbsp;&nbsp; </c:if>
					 	<c:if test="${(s.seat_idx)%6 == 0}"><br></c:if> <!-- 6 12 18 24 -->
						
						<c:if test="${t %12 == 0}"><br></c:if>
						<c:set var="t" value="${t+1 }" />
					</c:forEach>
		   	 </c:when>
		    
		   	 <c:when test="${15<=hour and hour<18}">
		 		<c:set var="t" value="1"/>
		   	  		<c:forEach var="s" items="${ list}">
		    	  	
		    	  		<c:if test="${s.t3_mem_idx < 0 }">
		    	  		<button type="button" class="btn btn-outline-dark btn-lg" onclick="time(${s.seat_idx});"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.t3_mem_idx >= 0 }">
		    	  		<button type="button" class="btn btn-secondary btn-lg" onclick="time(${s.seat_idx});"><fmt:formatNumber value="${s.seat_idx}" minIntegerDigits="2"/></button>
		    	  		</c:if>
		    	  		
		    	  		<c:if test="${s.seat_idx%2 == 0 }">&nbsp;&nbsp;&nbsp;&nbsp; </c:if>
					 	<c:if test="${(s.seat_idx)%6 == 0}"><br></c:if> <!-- 6 12 18 24 -->
						
						<c:if test="${t %12 == 0}"><br></c:if>
						<c:set var="t" value="${t+1 }" />
					</c:forEach>
		   	 </c:when>
			</c:choose>
		</p>
		  <!-- Optional JavaScript -->
		    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
		    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
		    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>

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
<div class="wrapper row4">
  <footer id="footer" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="one_quarter first">
      <h6 class="heading">Nulla ullamcorper</h6>
      <ul class="nospace btmspace-30 linklist contact">
        <li><i class="fas fa-map-marker-alt"></i>
          <address>
          Street Name &amp; Number, Town, Postcode/Zip
          </address>
        </li>
       <li><i class="fas fa-phone"></i> +82 (010) 2660 6661</li>
        <li><i class="far fa-envelope"></i> dkdl54321@naver.com</li>
      </ul>
      <ul class="faico clear">
        <li><a class="faicon-facebook" href="#"><i class="fab fa-facebook"></i></a></li>
        <li><a class="faicon-twitter" href="#"><i class="fab fa-twitter"></i></a></li>
        <li><a class="faicon-dribble" href="#"><i class="fab fa-dribbble"></i></a></li>
        <li><a class="faicon-linkedin" href="#"><i class="fab fa-linkedin"></i></a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="heading">Dignissim nibh ut</h6>
      <ul class="nospace linklist">
        <li><a href="#">Natoque penatibus et magnis</a></li>
        <li><a href="#">Dis parturient montes</a></li>
        <li><a href="#">Nascetur ridiculus mus</a></li>
        <li><a href="#">Vestibulum tincidunt nisi</a></li>
        <li><a href="#">Sed eleifend scelerisque</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="heading">Vestibulum cras placerat</h6>
      <ul class="nospace linklist">
        <li><a href="#">Maecenas vestibulum molestie</a></li>
        <li><a href="#">Arcu cras sed tincidunt</a></li>
        <li><a href="#">Enim maecenas sed mi dictum</a></li>
        <li><a href="#">Dolor laoreet fringilla</a></li>
        <li><a href="#">Augue curabitur lobortis</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="heading">Vulputate cum sociis</h6>
      <p class="nospace btmspace-15">Diam nulla id condimentum magna scelerisque ac suspendisse viverra est purus.</p>
      <form method="post" action="#">
        <fieldset>
          <legend>Newsletter:</legend>
          <input class="btmspace-15" type="text" value="" placeholder="Name">
          <input class="btmspace-15" type="text" value="" placeholder="Email">
          <button type="submit" value="submit">Submit</button>
        </fieldset>
      </form>
    </div>
    <!-- ################################################################################################ -->
  </footer>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row5">
  <div id="copyright" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left">Copyright &copy; 2018 - All Rights Reserved - <a href="#">Domain Name</a></p>
    <p class="fl_right">Template by <a target="_blank" href="https://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
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