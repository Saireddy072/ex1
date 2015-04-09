package com.sai;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class SurveyID extends ActionElementBase{

	public void doAction(String name, ActionElementData data ) throws AudiumException{

		try{

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			String str = data.getDnis();
			Connection con = DriverManager.getConnection("jdbc:sqlserver://10.1.6.196:1433;databaseName=SurveyDb;user=speech;password=soft;");
			data.addToLog("db_status", "connected");
			data.addToLog("db_status", "connected");
			Statement statement = con.createStatement();
			data.addToLog("statement", "connected");
			data.addToLog("db_status", "connected");
			ResultSet rs = statement.executeQuery("select SurveyID from SurveyDNIS where DNIS='"+str+"';");

			while(rs.next())
			{
				data.setSessionData("surveyID", rs.getInt("SurveyID"));
				data.addToLog("surveyID", "After setting session connected");
				data.addToLog("SurveyID", Integer.toString(rs.getInt("SurveyID")));

			}

		}catch(Exception e){



		}
	}

}
