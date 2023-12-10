package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdf_convertion", "root", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void redirectToPage(HttpServletRequest request, HttpServletResponse response, String destination) {
		try {
			response.sendRedirect(request.getContextPath() + destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Date convertStringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = new Date();
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void deleteFile(String filePath) {
		try {
			Path path = Paths.get(filePath);
			Files.deleteIfExists(path);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			String fileNameOutput = fileName.substring(fileName.indexOf("_") + 1);
			System.out.println("Output:" + fileNameOutput);
			System.out.println("Downloading: " + fileName);

			String filePath = request.getServletContext().getRealPath("/upload") + "/" + fileName;
			String mimeType = request.getServletContext().getMimeType(fileName);

			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			// Thiết lập thông số của response để trình duyệt hiểu là file download
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOutput + "\"");

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
