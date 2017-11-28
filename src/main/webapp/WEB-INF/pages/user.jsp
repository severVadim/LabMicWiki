<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
<link rel='shortcut icon' type='image/ico' href=<c:url value="/resources/image/favicon.ico" /> />
<html>
<title>Login</title>
<body>
  <div class="container">
	<div class="login-container">
            <div id="output"></div>
            <div class="avatar"></div>
            <div class="form-box">
			<c:url value="/postUsername" var="postUsername"/>
			<form:form method="post" action="${postUsername}" modelAttribute="user">
                    <form:input path="nickName"  type="text"     placeholder="Username"/>
                    <form:input path="password"  type="password" placeholder="Password"/>                  
                    <button class="button" type="submit">Login</button> 
             </form:form>
            </div>
        </div>       
	</div>
	<div align="center"><b>${error}</b></div>
</body>
</html>