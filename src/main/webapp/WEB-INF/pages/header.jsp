<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<a href="${pageContext.request.contextPath}/"><img border="0" alt="Home" src="../../resources/image/logo.png"/></a>
<div class="btn-group-wrap">
<div class="btn-group">
<button class="button" name="new" id="new">New</button>
<button class="button" name="edit" id="edit">Edit</button>
<button class="button" name="history" id="history">History</button>
<button class="button" name="preview-back" id="preview-back">Preview</button>
</div>
<div class="btn-login">
<b>Hello ${user.getNickName()}</b>
<button class="button" onclick="Login()">Login</button>
</div>
</div>
<SCRIPT>
function Login (){
	window.location.href='/LogIn';	
}
</SCRIPT>
