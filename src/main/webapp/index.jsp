<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PDF Conversion</title>
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/convertion/convertion.css" />
<link rel="stylesheet" type="text/css" href="css/login/login.css">
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="content">
    <div class="content-header">
      <h1 class="content-title">PDF to WORD Conversion</h1>
      <h2 class="content-subtitle">Converting pdf documents to
        word is very convenient</h2>
    </div>
    <div class="content-uploader">
      <a href="#!" class="btn-upload" id="uploadLink"> Choose PDF
        file</a>
    </div>
    <%
    if ((loginStatus != null && loginStatus) || (signUpStatus != null && signUpStatus)) {
    %>
    <div class="content-uploader">
        <a href="./ListConvertServlet" class="btn-upload">View list converted</a>
    </div>
    <%
    }%>
  </div>
  <%@include file="login-modal.jsp"%>
  <%@include file="signup-modal.jsp"%>
  <script src="./js/index.js"></script>
  <script src="./js/signup.js"></script>
  <script>
  	const loginStatus = <%=loginStatus%>;
	
  	if(loginStatus != null && !loginStatus) {
  		document.querySelector('.login-modal').style.display = 'flex';
  	}
  </script>
</body>
</html>
