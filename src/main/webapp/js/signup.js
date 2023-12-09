const password = document.formSignup.password;
const confirmPassword = document.formSignup.confirmPassword;

function validateInput(inputElement) {
	let isValid = true;
	if (inputElement.value === '') {
		inputElement.style.borderColor = 'red';
		isValid = false;
	} else {
		inputElement.style.borderColor = 'black';
	}
	return isValid;
}

function validatePassword() {
	if (password && confirmPassword) {
		let isValid = true;
		const username = document.formSignup.username;

		isValid &= validateInput(username);
		isValid &= validateInput(password);
		isValid &= validateInput(confirmPassword);

		if (!isValid) {
			alert('Vui lòng nhập đầy đủ thông tin');
		}
		else if (password.value !== confirmPassword.value) {
			confirmPassword.style.borderColor = 'red';
			alert('Mật khẩu xác nhận không đúng');
		} else {
			document.formSignup.submit();
		}
	}
}