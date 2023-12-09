package model.BO;

import model.DAO.ConverterDAO;

public class ConverterBO {
	public void saveHistory(String username, String fileNameUpload, String filePathOutput) {
		(new ConverterDAO()).saveHistory(username, fileNameUpload, fileNameUpload.replace(".pdf", ".docx"),
				filePathOutput);
	}
}
