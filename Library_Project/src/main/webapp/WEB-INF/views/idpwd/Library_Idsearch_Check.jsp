<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두루미 도서관</title>

<script src="${pageContext.request.contextPath}/resources/js/httpRequest.js"></script>

<script type="text/javascript">
	//window창 닫기
	function self_close() {
		window.close();
	}
	
	//이메일 인증
	var authKey;
	function email_check() {
		
		var email = document.getElementById('email').value;
		
		//Ajax를 통해 id를 서버로 전송
		var url = "sendmail.do";
		
		//encodeURIComponent : 파라미터로 전송하고자 하는 값에
		//@, #등 특수문자가 포함되어 있어도 정상전달하기 위한 인코딩 메서드
		var param = "email=" + encodeURIComponent(email);
		
		sendRequest(url, param, email_resultFn, "POST");
		
	}
	function email_resultFn() {
		
		if( xhr.readyState == 4 && xhr.status == 200 ){
			//서버로부터 도착한 데이터
			authKey = xhr.responseText;
			
			alert("인증번호가 메일로 발송되었습니다.");
			
		}
		
	}
	
	//이메일 인증번호 인증
	function key_check() {
		
		var m_idx = document.getElementById('m_idx').value;
		var key = document.getElementById('key').value;
		
		if( key != authKey ){
			alert("인증번호 6자리를 잘못 입력하셨습니다.");
			document.getElementById('key').value = "";
			document.getElementById('key').focus();
			return;
		}
		
		alert("인증이 완료되었습니다.");
		location.href = "id_search_find.do?m_idx=" + m_idx;
		
	}
</script>

</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td bgcolor="#999999" style="padding:5px 10px;" class="white12bold">아이디/비밀번호 찾기</td>
			</tr>
		</tbody>
	</table>
	
	<table width="500" class="grey12">
		<tbody><tr>
			<td style="padding:20px 0 0 0">
				<table width="460" align="center">
					<tbody>
						<tr>
							<td style="padding:15px; border-top:2px #cccccc solid; border-right:2px #cccccc solid; border-bottom:2px #cccccc solid; border-left:2px #cccccc solid;">
								<form name="id_f" id="id_f">
									<input type="hidden" name="m_idx" id="m_idx" value="${m_idx}">
									<table width="420">
									  	<tbody>
										  	<tr>
										  		<td class="stitle">이메일 인증</td>
										  	</tr>
									  	</tbody>
								  	</table>
								  	
								  	<table width="420" cellspacing="1" class="regtable">
									  	<tbody>
										  	<tr>
											  	<td height="25" bgcolor="#f4f4f4">e-Mail</td>
											  	<td>
											  		<input type="text" name="email" id="email" value="${ email }" tabindex="2" readonly>
											  	</td>
											  	<td align="center">
											  	<div class="bts"><a href="javascript:email_check();" tabindex="4"><span style="width:80px">인증번호 전송</span></a></div></td>
										  	</tr>
										  	<tr>
											  	<td width="100" height="25" bgcolor="#f4f4f4">인증 번호</td>
											  	<td width="130">
											  		<input type="text" name="key" id="key" tabindex="1" placeholder="인증번호 6자리를 입력해주세요.">
											  	</td>
											  	<td align="center"><div class="bts"><a href="javascript:key_check();" tabindex="4"><span style="width:80px">인증</span></a></div></td>
										  	</tr>
										</tbody>
									</table>
								</form>
								
							</td>
						</tr>
					</tbody>
				</table>
				
				<table align="right" >
					<tbody>
						<tr>
							<td height="40" style="padding:0 13px 0 0">
								<div class="bts"><a href="javascript:self_close();"><span style="width:50px">닫기</span></a></div>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
			</tr>
		</tbody>
	</table>
</body>
</html>