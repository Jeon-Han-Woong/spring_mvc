<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exUpload.jsp</title>
</head>
<body>

	<form action="/spring/exUploadPost" method="post" enctype="multiPART/form-data">
	
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<div>
			<input type='file' name='files'>
		</div>
		<hr>
		
		<button type="submit">제출</button>
	</form>
	
</body>
</html>