package com.sai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class GetSurveyID extends ActionElementBase {
	public void doAction(String name,ActionElementData data) throws AudiumException{
		try{
			data.addToLog("in try", "try");
			String ani =data.getAni();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://10.1.6.196:1433;databaseName=SurveyDb;user=speech;password=soft;");
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("select top 1 surveyID from Surveyacceptance where ani="+ani+"");
		
		while(rset.next()){
			data.setSessionData("SurveyID", rset.getInt("SurveyID"));
			data.addToLog("SurveyID" , Integer.toString(rset.getInt("SurveyID")));
		}

		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		
	}

}

