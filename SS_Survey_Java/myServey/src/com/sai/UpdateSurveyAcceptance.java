/**
 * 
 */
package com.sai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

/**
 * @author admin
 *
 */
public class UpdateSurveyAcceptance extends ActionElementBase{
	@Override
	public void doAction(String name, ActionElementData data) throws AudiumException {
		boolean isActive = (Boolean) data.getSessionData("active");
		byte b = (Byte)data.getSessionData("active");
		int id = (Integer) data.getSessionData("id");
		Connection conn = null;
		Statement stmt = null;
		String query = "update SurveyAcceptance set Accepted='"+b+"' where id='"+id+"'";
		
		if(isActive){
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://10.1.6.196:1433;databaseName=SurveyDb;user=speech;password=soft;");
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
			} catch(Exception e){
				System.out.println("Unable to update the record in Survey acceptance");
			}
			finally{
				if(null != stmt){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}
}
