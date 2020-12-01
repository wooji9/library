<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:if test="${ vo.del_info eq 1 }">
	<script>
		alert("존재하지 않는 게시글입니다.");
		location.href = "board.do?page=${param.page}";
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>
<script>
	function del(){
		if(${not empty sessionScope.user} && ${user.id eq vo.mem_id}){
			if(confirm("삭제하시겠습니까?")){
				var url = "board_delete.do";
				var param = "idx=${vo.idx}";
				sendRequest(url, param, resultFn, "POST");
			}
		}
	}
	function resultFn(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data  =xhr.responseText;
			if(data == 'yes'){
				alert("글을 삭제하였습니다");
				location.href = "board.do?page=${param.page}";
			}
		}
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
      <h6>열린마당</h6>
      <nav class="sdb_holder">
        <ul>
          <li><a href="list.do">공지사항</a></li>
          <li><a href="#">희망도서신청</a></li>
          <li><a href="board.do">건의사항</a></li>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="content three_quarter"> 
      <!-- ################################################################################################ -->
      <h1>건의사항</h1>
	  <form>
	      <div class="scrollable">
	        <table>
	          <tbody>
	            <tr>
	              	<th>제목</th>
				  	<td>${vo.subject }</td>
	            </tr>
	            <tr>
					<th>작성자</th>
					<td>${vo.name }</td>
				</tr>
				<tr>
					<th>작성 날짜</th>			
					<td>${vo.regdate }</td>	
				</tr>
				<tr>
					<th>내용</th>
					<td><pre>${ vo.content }</pre></td>
				</tr>
	          </tbody>
	        </table>
	        <div>
	        	<c:if test="${ !empty sessionScope.user }">
					<!-- 글 삭제 -->
					<img src="${pageContext.request.contextPath}/resources/images/img/btn_delete.gif" onclick="del();"
					style="cursor:pointer" align="right">
					<!-- 글 수정 -->
					<img src="${pageContext.request.contextPath}/resources/images/img/btn_modify.gif" onclick="modify();" 
					style="cursor:pointer" align="right">
	        	</c:if>
				<!-- 목록으로 돌아기기  -->
				<img src="${pageContext.request.contextPath}/resources/images/img/btn_list.gif" 
				onclick="location.href='board.do?page=${param.page}'"
				style="cursor:pointer" align="right">
	        </div>
	      </div>
      </form>
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