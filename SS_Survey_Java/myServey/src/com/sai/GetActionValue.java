
package com.sai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class GetActionValue extends ActionElementBase{

	@Override
	public void doAction(String name, ActionElementData data) throws AudiumException {
		Connection conn = null;
		ResultSet rs = null;
		Statement statement = null;
		String query = (String)data.getSessionData("query");
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://10.1.6.196:1433;databaseName=SurveyDb;user=speech;password=soft;");
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			data.setSessionData("result", rs);
		} catch(Exception e){
			System.out.println("Data Access Error");
		}
		finally{
			if(null != statement){
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
