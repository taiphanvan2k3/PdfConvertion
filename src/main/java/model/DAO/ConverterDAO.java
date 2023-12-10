package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.BEAN.Upload;
import utils.Utils;

public class ConverterDAO {
	public ArrayList<Upload> getListFileConvert(String username) {
		ArrayList<Upload> uploads = new ArrayList<>();
		try {
			Connection connection = Utils.getConnection();
			if (connection != null) {
				String query = "select * from uploads where username = '" + username + "'";
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Upload upload = new Upload(rs.getString("fileNameUpload"), rs.getString("fileNameOutput"),
							rs.getString("fileNameOutputInServer"), rs.getString("date"));
					uploads.add(upload);
				}
			}
		} catch (Exception e) {
			System.err.println("getListFileConvert: " + e.getMessage());
		}
		return uploads;
	}

	public void saveHistory(String username, String fileNameUpload, String fileNameOutput,
			String fileNameOutputInServer) {
		try {
			Connection connection = Utils.getConnection();
			String sql = "insert into uploads(username, fileNameUpload, fileNameOutput, fileNameOutputInServer) "
					+ "values(?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, fileNameUpload);
			pst.setString(3, fileNameOutput);
			pst.setString(4, fileNameOutputInServer);
			pst.executeUpdate();
		} catch (Exception e) {
			System.err.println("saveHistory: " + e.getMessage());
		}
	}
}
