package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BEAN.Upload;
import model.BO.ConverterBO;
import utils.Utils;

@WebServlet("/ListConvertServlet")
public class ListConvertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListConvertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Upload> uploads = (new ConverterBO())
				.getListFileConvert((String) request.getSession().getAttribute("username"));
		request.getSession().setAttribute("uploads", uploads);
		Utils.redirectToPage(request, response, "/viewListConvert.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
