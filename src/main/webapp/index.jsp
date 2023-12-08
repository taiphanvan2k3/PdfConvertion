<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PDF Convertion</title>
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/convertion/convertion.css" />
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="content">
    <div class="content-header">
      <h1 class="content-title">PDF to WORD Convertion</h1>
      <h2 class="content-subtitle">Converting pdf documents to
        word is very convenient</h2>
    </div>
    <div class="content-uploader">
      <a href="#!" class="btn-upload" id="uploadLink">Choose PDF
        file</a>
    </div>
  </div>
  <script>
			document.addEventListener("DOMContentLoaded", function() {
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
						console.log(selectedFile.name);
						
						// Gửi file lên server sử dụng XMLHttpRequest
						var xhr = new XMLHttpRequest();
						var formData = new FormData();
						formData.append("file", selectedFile);

						xhr.open("POST", "ConverterServlet", true);
						xhr.responseType = "arraybuffer";
						
						xhr.onreadystatechange = function() {
							if (xhr.readyState === 4) {
								if (xhr.status === 200) {
									var blob = new Blob([xhr.response], { type: "application/vnd.openxmlformats-officedocument.wordprocessingml.document" });
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
		</script>
</body>
</html>