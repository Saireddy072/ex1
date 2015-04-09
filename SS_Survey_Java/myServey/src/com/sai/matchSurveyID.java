package com.sai;
import java.sql.ResultSet;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class matchSurveyID extends ActionElementBase {
	public  void doAction(String name, ActionElementData data)throws AudiumException{
		try{
			
			data.addToLog("before util", "util");
			//Util util = new Util();
			data.addToLog("after util", "util");
			Integer surveyID = (Integer)data.getSessionData("SurveyID");
			String surveyID1= surveyID.toString();
			data.addToLog("SID", surveyID1);
			Util util = new Util();
			ResultSet rs = util.getQid(surveyID1);
			
			String type = "";
			String TTS = "";
			if(rs.next()){
				type = rs.getString(1);
				TTS = rs.getString(2);
				
			}
			data.setSessionData("type", type); 
			data.addToLog("type", type);
			data.setSessionData("TTS", TTS);
			data.addToLog("TTS", TTS);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
}
