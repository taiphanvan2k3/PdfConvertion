package model.BO;

import java.util.ArrayList;

import model.BEAN.Upload;
import model.DAO.ConverterDAO;

public class ConverterBO {
	public ArrayList<Upload> getListFileConvert(String username) {
		return (new ConverterDAO()).getListFileConvert(username);
	}

	public void saveHistory(String username, String fileNameUpload, String fileNameInServer) {
		(new ConverterDAO()).saveHistory(username, fileNameUpload, fileNameUpload.replace(".pdf", ".docx"),
				fileNameInServer.replace(".pdf", ".docx"));
	}
}
