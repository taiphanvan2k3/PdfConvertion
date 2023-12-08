package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ConverterServlet")
@MultipartConfig
public class ConverterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConverterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy tất cả các phần (parts) được gửi từ yêu cầu
		Collection<Part> parts = request.getParts();
		String filePathInServer = "";
		for (Part part : parts) {
			String folderUpload = request.getServletContext().getRealPath("/upload");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (!Files.exists(Path.of(folderUpload))) {
				Files.createDirectory(Path.of(folderUpload));
			}

			filePathInServer = folderUpload + "/" + fileName;
			part.write(folderUpload + "/" + fileName);
		}

		System.out.println("Fileupload:" + filePathInServer);

		ConverterThread thread = new ConverterThread(filePathInServer);
		thread.start();
		try {
			thread.join();

			// Xoá pdf upload, mấy cái file tạm
			this.downloadFile(request, response, filePathInServer.replaceAll(".pdf", ".docx"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
		try {
			String fileName = new File(filePath).getName();

			String mimeType = getServletContext().getMimeType(fileName);

			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			// Thiết lập thông số của response để trình duyệt hiểu là file download
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			// Đọc dữ liệu từ file và ghi vào OutputStream của response
			try (FileInputStream fileInputStream = new FileInputStream(filePath);
					OutputStream outputStream = response.getOutputStream()) {
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				long total = 0;
				while ((bytesRead = fileInputStream.read(buffer)) != -1) {
					total += bytesRead;
					outputStream.write(buffer, 0, bytesRead);
				}
				System.out.println("Tổng bytes:" + total);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
