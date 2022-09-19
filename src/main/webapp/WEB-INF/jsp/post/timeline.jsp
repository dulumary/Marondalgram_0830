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
  	
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	
  	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<!--  timeline -->
			<div class="col-5">
				<!-- 입력 상자 -->
				<div class="border rounded">
					<textarea class="form-control border-0" rows="4" id="contentInput"></textarea>
				
					<div class="d-flex justify-content-between mt-2">
						<a href="#" id="imageIcon" class="ml-3"> <i class="bi bi-image image-icon"></i> </a>
						<input type="file" id="fileInput" class="d-none">
						<button type="button" class="btn btn-primary" id="uploadBtn">업로드</button>
					
					</div>
				</div>
				<!-- /입력 상자 -->
				
				<!--  피드들~ -->
				<div class="mt-3">
					<c:forEach var="post" items="${postList }">
					<!--  카드 -->
					<div class="border rounded mt-2">
						<div class="d-flex justify-content-between  p-2">
							<div>${post.userId }</div>
							<i class="bi bi-three-dots"></i>
						
						</div>
						
						<div>
							<img class="w-100" src="${post.imagePath }">
							
						</div>
					
						<div class="p-2">
							<span class=""><i class="bi bi-heart"></i></span>  좋아요 10개
						</div>
						
						<div class="p-2" >
							<b>${post.userId }</b> ${post.content }
						</div>
						
						<!--  댓글들! -->
						<div class="p-2">
							<div class="small">댓글</div>
							<hr>
							<div class="small"><b>유재석</b> 우와 진짜 귀엽네요!</div>
							<div class="small"><b>바다</b> 데려다가 키워요!</div>
							
							<!--  댓글 입력 -->
							<div class="d-flex mt-1">
								<input type="text" class="form-control">
								<button type="button" class="btn btn-primary">게시</button>
							</div>
							<!--  /댓글 입력 -->
						
						</div>
						<!--  /댓글들! -->
					</div>
					<!--  /카드 -->
					
					</c:forEach>
				
				</div>
				<!--  /피드들~ -->
				
			</div>
			<!--  /timeline -->
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	</div>
	
	<script>
		$(document).ready(function() {
			
			$("#uploadBtn").on("click", function() {
				
				let content = $("#contentInput").val();
				
				if(content == "") {
					alert("내용을 입력하세요!!");
					return ;
				}
				
				// 파일에 대한 유효성 검사 
				if($("#fileInput")[0].files.length == 0) {
					alert("이미지를 선택해주세요.");
					return ;
				}
				
				let formData = new FormData();
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				$.ajax({
					type:"post"
					, url:"/post/create"
					, data:formData
					, enctype:"mutipart/form-data"
					, processData:false
					, contentType:false
					, success:function(data) {
						
						if(data.result == "success") {
							location.reload();
						} else {
							alert("업로드 실패");
						}
						
					}
					, error:function() {
						alert("업로드 에러");
					}
				});
				
				
				
				
			});
			
			
			
			$("#imageIcon").on("click", function() {
				//  파일인풋을 클릭한 효과 
				$("#fileInput").click();
			});
			
			
			
			
		});
	
	</script>
	
	
	
	
	
	

</body>
</html>