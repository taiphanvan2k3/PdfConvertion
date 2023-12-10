<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<div class="modal signup-modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h1 class="title">SIGN UP</h1>
    <form action="LoginServlet" method="POST" name="formSignup">
      <input type="hidden" name="action" value="sign-up" />
      <div class="content">
        <div class="row form-group">
          <input type="text" name="username"
            placeholder="Username"
          />
        </div>
        <div class="row form-group">
          <input type="password" name="password"
            placeholder="Password"
          />
        </div>
        <div class="row form-group">
          <input type="password" name="confirmPassword"
            placeholder="Confirm your password"
          />
        </div>
        <div class="row btn-group">
          <button type="button" onclick="validatePassword()">Ok</button>
          <button type="reset" name="btnCancel">Cancel</button>
        </div>
      </div>
    </form>
  </div>
</div>