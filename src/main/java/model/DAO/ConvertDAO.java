package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.BEAN.Upload;
import utils.Utils;


public class ConvertDAO {
	public ArrayList<Upload> getListFileConvert(String username) {
		ArrayList<Upload> uploads = new ArrayList<>();
		try {
			Connection connection = Utils.getConnection();
			if (connection != null) {
				String query = "select * from uploads where username = '" + username +"'";
				try {
					Statement st = connection.createStatement();
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						Upload upload = new Upload(rs.getString("fileNameUpload"), rs.getString("fileNameOutput")
								, rs.getString("date"));
						uploads.add(upload);
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		return uploads;
	}
}
