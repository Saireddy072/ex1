package com.sai;


import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;
public class UpdateSurveyResult extends ActionElementBase {
	
	public void  doAction(String name, ActionElementData data)throws AudiumException{
		data.addToLog("before util", "before util");
		Util util = new Util();
		data.addToLog("after util", "after util");
		Integer surveyID = (Integer)data.getSessionData("SurveyID");
		String surveyID1 = (surveyID).toString();
		data.addToLog("SurveyID is",  surveyID1);
		Integer Accepted = 1;
		int qid = 1;
		String response= data.getElementData("2_Option_Menu_01", "value");
		
		data.addToLog("Response", response);
		util.UpdateResponse(surveyID,Accepted,qid,response);
			
	}
}
