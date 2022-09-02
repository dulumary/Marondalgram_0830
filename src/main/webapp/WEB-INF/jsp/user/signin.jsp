<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Marondalgram</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<section class="content d-flex justify-content-center my-5">
			<div class="mr-5">
				<img src="https://cdn.pixabay.com/photo/2016/09/17/07/03/instagram-1675670_960_720.png" width="450">
			</div>
			<div>
				<div class="login-box d-flex justify-content-center align-items-start bg-white  border rounded">		
					<div class="w-100 p-5">			
						<h2 class="text-center">Marondalgram</h2>
						<br>
						<form id="loginForm">
							<input type="text" id="loginIdInput" class="form-control mt-3" placeholder="아이디">
							<input type="password" id="passwordInput" class="form-control mt-3" placeholder="패스워드">
							<button id="loginBtn" type="submit" class="btn btn-primary btn-block mt-3">로그인</button>
							<hr/>
						</form>
						
						<div class="text-center text-secondary">비밀번호를 잊으셨나요?</div>
					</div>
					
				</div>
				<div class="mt-4 p-3 d-flex justify-content-center align-items-start bg-white  border rounded">
					계정이 없으신가요? <a href="/user/signup/view">가입하기</a>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		
	</div>
	
	<script>
		$(document).ready(function() {
			$("#loginForm").on("submit", function(e) {
				e.preventDefault();
				
				let loginId = $("#loginIdInput").val();
				let password = $("#passwordInput").val();
				
				if(loginId == "") {
					alert("아이디를 입력하세요");
					return ;
				}
				
				if(password == "") {
					alert("비밀번호를 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"post"
					, url:"/user/signin"
					, data:{"loginId":loginId, "password":password}
					, success:function(data) {
						
						if(data.result == "success") {
							location.href = "/post/timeline/view";
						} else {
							alert("아이디/비밀번호 확인해주세요");
						}
						
					}
					, error:function() {
						alert("로그인 에러");
					}
				});
				
				
			});
			
		});
	
	</script>

</body>
</html>