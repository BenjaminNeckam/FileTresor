package at.database;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class FileData extends SqlHelper{
	
	//TODO later use File instead of String
	public void insertFile(String filePath, String fileName) throws SQLException, IOException{
		File pdfFile = new File(filePath); //C://Users//Benni//Documents//Lebenslauf_Neckam_Benjamin.pdf
		byte[] pdfData = new byte[(int) pdfFile.length()];
		DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
		dis.readFully(pdfData);
		dis.close();

		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO files (" +
						"fileId, " +
						"filename, " +
						"file " +
						") Values(?,?,?)");
		//TODO fileId should be the hash of the file
		ps.setInt(1,1);
		ps.setString(2,fileName);
		ps.setBytes(3, pdfData);
		ps.executeUpdate();
		ps.close();
		
	}
	
	public void deleteFile(int fileId) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM files where fileId=" + fileId);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void main(String[] args){
		FileData fd = new FileData();
		fd.initDatabaseConnection();
		try {
			//fd.insertFile("C://Users//Benni//Documents//Lebenslauf_Neckam_Benjamin.pdf");
			fd.deleteFile(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fd.shutDownConnection();
	}
}
