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
			<div>
				<div class="login-box d-flex justify-content-center align-items-start bg-white  border rounded">
					<div class="w-100 p-5">
						<h2 class="text-center">Marondalgram</h2>
						<br>
						<div class="text-center">
							<b class="text-secondary">친구들의 사진과 동영상을 보려면<br> 가입하세요.</b>
						</div>
						
						<div class="d-flex  mt-3">
							<input type="text" id="loginIdInput" class="form-control" placeholder="아이디">
							<button type="button" class="btn btn-info btn-sm ml-2" id="isDuplicateBtn">중복확인</button>
						</div>
						
						<div class="small text-success d-none" id="availableText">사용가능한 아이디 입니다</div>
						<div class="small text-danger d-none" id="duplicateText">중복된 아이디 입니다</div>
					
						<input type="password" id="passwordInput" class="form-control mt-3" placeholder="패스워드">
						<input type="password" id="passwordConfirmInput" class="form-control mt-3" placeholder="패스워드 확인">
						
						<input type="text" id="nameInput" class="form-control mt-3" placeholder="이름">
						<input type="text" id="emailInput" class="form-control mt-3" placeholder="이메일">
						
						<button type="button" id="signUpBtn" class="btn btn-info btn-block mt-3">회원가입</button>
				
					</div>
					
				</div>
				<div class="mt-4 p-3 d-flex justify-content-center align-items-start bg-white  border rounded">
					계정이 있으신가요? <a href="/user/signin/view">로그인</a>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	</div>
	<script>
		$(document).ready(function() {
			
			var isDuplicateCheck = false;
			var isDuplicateId = true;
			
			$("#loginIdInput").on("input", function() {
				// 사용자가 아이디 인풋을 건드는 순가나
				// 중복체크 관련 모든 사항을 초기화 
				isDuplicateCheck = false;
				isDuplicateId = true;
				
				$("#duplicateText").addClass("d-none");
				$("#availableText").addClass("d-none");
			});
			
			
			$("#isDuplicateBtn").on("click", function() {
					
				let loginId = $("#loginIdInput").val();
				
				if(loginId == "") {
					alert("아이디를 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"get"
					, url:"/user/duplicate_id"
					, data:{"loginId":loginId}
					, success:function(data) {
						
						isDuplicateCheck = true;
						//중복시	{"id_duplicate":true}
						//중복이 아닌경우	{"id_duplicate":false}
						
						if(data.id_duplicate) {
							$("#duplicateText").removeClass("d-none");
							$("#availableText").addClass("d-none");
							isDuplicateId = true;
						} else {
							$("#duplicateText").addClass("d-none");
							$("#availableText").removeClass("d-none");
							isDuplicateId = false;
						}
						
					}
					, error:function() {
						alert("중복체크 에러");
					}
				});
			});
			
			
			$("#signUpBtn").on("click", function() {
				let loginId = $("#loginIdInput").val();
				let password = $("#passwordInput").val();
				let passwordConfirm = $("#passwordConfirmInput").val();
				let name = $("#nameInput").val()
				let email = $("#emailInput").val()
				
				if(loginId == "") {
					alert("아이디를 입력하세요");
					return ;
				}
				
				// 중복체크 여부 확인 (중복체크가 진행되지 않으면 return)
				if(isDuplicateCheck == false) {
					alert("중복체크를 진행해주세요");
					return ;
				}
				
				// 아이디 중복확인 (중복된 상태면 return)
				if(isDuplicateId) {
					alert("아이디가 중복되었습니다");
					return ;
				}
				
				if(password == "") {
					alert("비밀번호를 입력하세요");
					return ;
				}
				
				if(password != passwordConfirm) {
					alert("비밀번호를 확인하세요");
					return ;
				}
				
				if(name == "") {
					alert("이름을 입력하세요");
					return ;
				}
				
				if(email == "") {
					alert("이메일을 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"post"
					, url:"/user/signup"
					, data:{"loginId":loginId, "password":password, "name":name, "email":email}
					, success:function(data) {
						
						if(data.result == "success") {
							location.href = "/user/signin/view"
						} else {
							alert("회원가입 실패");
						}
					}
					, error:function() {
						alert("회원가입 에러");
					}
					
				});
				
			});
			
			
		});
	
	</script>
	
	

</body>
</html>