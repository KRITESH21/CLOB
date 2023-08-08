import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class CLOBInsertion {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		MysqlConnectionPoolDataSource datasource=new MysqlConnectionPoolDataSource();
		datasource.setURL("jdbc:mysql://localhost:3306/nathan_sir");
		datasource.setUser("root");
		datasource.setPassword("Kritesh21@");
		
		Connection con=null;
		PreparedStatement prstmt=null;
				
		try {
			con=datasource.getConnection();
			if(con!=null) {
				String insertQuery="insert into Cities(`name`,`history`)values(?,?)";
				prstmt=con.prepareStatement(insertQuery);
				if(prstmt!=null) {
					prstmt.setString(1, "Bengaluru");
					File file=new File("Bengaluru_history.txt");
					FileReader fr=new FileReader(file);
					prstmt.setCharacterStream(2, fr);
					System.out.println("inserting text file from " +file.getAbsolutePath());
					int noOfrows=prstmt.executeUpdate();
					System.out.println("no of rows inserted "+noOfrows);
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}

}
