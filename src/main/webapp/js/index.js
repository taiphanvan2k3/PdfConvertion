function removeSessionUsername() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "./LoginServlet?action=invalidate-session", true);
	xhr.send();
}

function getAllUsernames() {
	const xhr = new XMLHttpRequest();
	xhr.open(
		"POST",
		"LoginServlet",
		true);

	// Định nghĩa sự kiện xử lý phản hồi từ controller
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var responseData = xhr.responseText;
			console.log(responseData);
		}
	};

	const data = { 'action': 'get-users' };
	xhr.send(data);
}

document.addEventListener("DOMContentLoaded", function() {
	const loginModal = document.querySelector('.login-modal');
	const signupModal = document.querySelector('.signup-modal');

	const loginElement = document.querySelector('.text-login');
	if (loginElement !== null) {
		loginElement.addEventListener('click', function() {
			loginModal.style.display = 'flex';
		});
	}

	const btnSignUp = document.querySelector('.btn-signup');
	if (btnSignUp) {
		btnSignUp.addEventListener('click', function() {
			signupModal.style.display = 'flex';
		});
	};

	document.querySelectorAll('.close').forEach((closeElement) => {
		closeElement.addEventListener('click', function() {
			loginModal.style.display = 'none';
			signupModal.style.display = 'none';

			document.querySelectorAll('.login-modal input').forEach((element) => {
				element.value = '';
			});

			document.querySelectorAll('.signup-modal input').forEach((element) => {
				element.style.borderColor = 'black';
				element.value = '';
			});
			
			removeSessionUsername();
		})
	});

	// Lấy thẻ a và lắng nghe sự kiện click
	var uploadLink = document.getElementById("uploadLink");
	uploadLink.addEventListener("click", function(e) {
		e.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

		// Tạo input để chọn file
		const fileInput = document.createElement("input");
		fileInput.type = "file";
		fileInput.accept = ".pdf";

		// Lắng nghe sự kiện change của input file
		fileInput.addEventListener("change", function() {
			// Lấy file đã chọn
			var selectedFile = this.files[0];

			// Gửi file lên server sử dụng XMLHttpRequest
			var xhr = new XMLHttpRequest();
			var formData = new FormData();
			formData.append("file", selectedFile);

			xhr.open(
				"POST",
				"ConverterServlet",
				true);
			xhr.responseType = "arraybuffer";

			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						var blob = new Blob([xhr.response], {
							type: "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
						});
						var link = document.createElement("a");
						link.href = window.URL.createObjectURL(blob);
						link.download = selectedFile.name.replace('pdf', 'docx');
						link.click();
					} else {
						console.error("Error: " + xhr.status);
					}
				}
			};

			xhr.send(formData);
		});

		// Kích hoạt sự kiện click cho input file
		fileInput.click();
	});
});