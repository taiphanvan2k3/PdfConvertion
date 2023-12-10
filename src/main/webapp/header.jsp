<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%
Boolean loginStatus = (Boolean) request.getSession().getAttribute("login-status");
Boolean signUpStatus = (Boolean) request.getSession().getAttribute("signup-status");
%>
<div class="header">
  <div class="navbar">
    <div class="menu">
      <img class="logo-header" src="./img/Logo.png"
        alt="PDF Convertion"
      />
    </div>
    <div class="actions">
      <%
      if ((loginStatus != null && loginStatus) || (signUpStatus != null && signUpStatus)) {
      %>
      <label style="margin-right: 10px;">
        <span>Hello</span>
        <b><%=request.getSession().getAttribute("username")%></b>
      </label>

      <a class="btn btn-logout" href="./LoginServlet?action=logout">Logout</a>
      <%
      } else {
      %>
      <label class="text-login">Login</label>
      <button class="btn btn-signup">Sign up</button>
      <%
      }
      %>
    </div>
  </div>
</div>