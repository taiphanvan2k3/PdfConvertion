package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.Utils;

public class ConverterDAO {
	public void saveHistory(String username, String fileNameUpload, String fileNameOutput, String filePathOutput) {
		try {
			Connection connection = Utils.getConnection();
			String sql = "insert into uploads(username, fileNameUpload, fileNameOutput, filePathOutput) "
					+ "values(?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, fileNameUpload);
			pst.setString(3, fileNameOutput);
			pst.setString(4, filePathOutput);
			pst.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
