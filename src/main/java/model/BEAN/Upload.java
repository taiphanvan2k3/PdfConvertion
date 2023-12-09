package model.BEAN;

public class Upload {
	private String fileUpload;
	private String fileConverted;
	private String date;
	public Upload(String fileUpload, String fileConverted, String date) {
		super();
		this.fileUpload = fileUpload;
		this.fileConverted = fileConverted;
		this.date = date;
	}
	public String getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getFileConverted() {
		return fileConverted;
	}
	public void setFileConverted(String fileConverted) {
		this.fileConverted = fileConverted;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
