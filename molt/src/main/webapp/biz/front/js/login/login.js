$(document).ready(function(){
	$('#btnLogin').click(function(e){
		e.preventDefault();
			
			var userId = $('#userId').val();
			if(userId == '' || userId == null){
				alert('아이디를 입력해주세요.');
				return;
			} else {
				$('#userPwd').val(MD5($('#userPwd').val()));
				$('#loginForm').submit();
			}
	});
});
