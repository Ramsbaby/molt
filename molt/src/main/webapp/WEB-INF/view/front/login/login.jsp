<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/_layout/include/tagLib.jsp" %>

	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form id="loginForm" action="<c:url value='/j_spring_security_check' />" method="post">
							<input type="text" placeholder="아이디" id="userId" name="userId" value=""/>
							<input type="password" placeholder="비밀번호" id="userPwd" name="userPwd" value=""/>
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="button" id="btnLogin" class="btn btn-default">Login</button>
							<button type="button" id="btnJoin" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
			</div>
		</div>
	</section><!--/form-->