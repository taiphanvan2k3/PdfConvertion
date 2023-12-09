package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.BO.ConverterBO;
import model.BO.ConverterThread;
import utils.Utils;

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
		String fileName = "";
		for (Part part : parts) {
			String folderUpload = request.getServletContext().getRealPath("/upload");

			Date now = new Date();
			fileName = now.getTime() + "_" + Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (!Files.exists(Path.of(folderUpload))) {
				Files.createDirectory(Path.of(folderUpload));
			}

			filePathInServer = folderUpload + "/" + fileName;
			part.write(filePathInServer);
		}

		System.out.println("Fileupload: " + filePathInServer);

		ConverterThread thread = new ConverterThread(filePathInServer);
		thread.start();
		try {
			thread.join();

			// Xoá file đã upload lên lúc nãy
			Utils.deleteFile(filePathInServer);

			String fileOutput = filePathInServer.replace(".pdf", ".docx");

			// Lưu lịch sử lại DB
			this.saveHistory("admin", fileName, fileOutput);
			Utils.downloadFile(request, response, fileName.replace(".pdf", ".docx"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void saveHistory(String username, String fileNameUpload, String filePathOutput) {
		Thread saveHistoryThread = new Thread(() -> {
			(new ConverterBO()).saveHistory(username, fileNameUpload, filePathOutput);
		});
		saveHistoryThread.start();
	}
}
