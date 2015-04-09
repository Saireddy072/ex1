package com.sai;
import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class SurveyActive extends ActionElementBase{

	public void doAction(String name, ActionElementData data ) throws AudiumException{
		try{
			data.addToLog("In", "Action");
			Integer surveyID = (Integer)data.getSessionData("surveyID");
			Util util = new Util();
			boolean active = util.checkForActiveWithDate(surveyID,data);
			data.addToLog("Active", String.valueOf(active));
			data.setSessionData("surveyActive", active);
		}catch(Exception e){
			data.addToLog("Exception", e.getMessage());
			e.printStackTrace();
		}
	}
}


