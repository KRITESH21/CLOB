import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

import org.apache.commons.io.IOUtils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class CLOBretrieval {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		MysqlConnectionPoolDataSource datasource=new MysqlConnectionPoolDataSource();
		datasource.setURL("jdbc:mysql://localhost:3306/nathan_sir");
		datasource.setUser("root");
		datasource.setPassword("Kritesh21@");

		Connection con=null;
		PreparedStatement prstmt=null;
		ResultSet resultset=null;
		
		
		
		con=datasource.getConnection();
		try {
			if(con!=null) {
				String selectQuery="select name,history from Cities where name=?";
				prstmt=con.prepareStatement(selectQuery);
			}
			if(prstmt!=null) {
				prstmt.setString(1, "Bengaluru");
				resultset=prstmt.executeQuery();			}
			if(resultset!=null) {
				if(resultset.next()) {
				
				Reader reader=resultset.getCharacterStream(2);
				File f=new File("Bengaluru-history");
				FileWriter w=new FileWriter(f);
				IOUtils.copy(reader,w);
				w.flush();
			}else {
				System.out.println("no records are available");
			}
			}
		}catch(SQLException se) {
			se.printStackTrace();			
		}catch(IOException io) {
			io.printStackTrace();
		}
	}

}
