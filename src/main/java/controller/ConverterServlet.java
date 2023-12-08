package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
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
		// Đường dẫn đến file đã xử lý
		String filePath = "path/to/processed/file.pdf";

		// Thiết lập các thông số trả về
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=file.pdf");

		try (InputStream in = new FileInputStream(filePath); OutputStream out = response.getOutputStream()) {
			// Đọc dữ liệu từ file và ghi vào phản hồi
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy tất cả các phần (parts) được gửi từ yêu cầu
		Collection<Part> parts = request.getParts();

		for (Part part : parts) {
			// Lấy tên của phần (trường file)
			String fileName = getFileName(part);

			InputStream fileContent = part.getInputStream();

			String savePath = "";

			// Tạo đường dẫn đầy đủ để lưu file
			String filePath = savePath + File.separator + fileName;

			// Lưu file vào đường dẫn đã xác định
			try (OutputStream out = new FileOutputStream(new File(filePath));
					InputStream fileInputStream = fileContent) {
				int read;
				final byte[] bytes = new byte[1024];
				while ((read = fileInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				System.out.println("File saved successfully: " + filePath);
			} catch (IOException e) {
				System.out.println("Error saving file: " + e.getMessage());
			}

			// In thông báo về tên file đã nhận được
			System.out.println("Received file: " + fileName);
		}
	}

	// Hàm utility để lấy tên file từ một phần
	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "unknown";
	}
}
