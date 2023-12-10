<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<div class="modal login-modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h1 class="title">LOGIN</h1>
    <form action="LoginServlet" method="POST" name="formLogin">
      <input type="hidden" name="action" value="check-login" />
      <div class="content">
        <div class="row form-group">
          <%
          String username = (String) request.getSession().getAttribute("username");
          %>
          <input type="text" name="username" placeholder="Username"
            value="<%=username == null ? "" : username%>"
          />
        </div>
        <div class="row form-group">
          <input type="password" name="password"
            placeholder="Password"
          />
        </div>
        <div class="row btn-group">
          <button type="submit">Login</button>
        </div>
      </div>
    </form>
  </div>
</div>