package model.BO;

import java.util.ArrayList;

import model.BEAN.Upload;
import model.DAO.ConvertDAO;

public class ConvertBO {
	public ArrayList<Upload> getListFileConvert(String username) {
		return (new ConvertDAO()).getListFileConvert(username);
	}
}
