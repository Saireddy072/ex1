package com.sai;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;
public class UpdateSurveyResult5 extends ActionElementBase {
	
	public void  doAction(String name, ActionElementData data)throws AudiumException{
	
		Util util = new Util();
		Integer surveyID = (Integer)data.getSessionData("SurveyID");
		Integer Accepted = 1;
		int qid = 1;
		String response= data.getElementData("5_Option_Menu_01", "value");
		data.addToLog("response value is", response);
		util.UpdateResponse(surveyID,Accepted,qid,response);
			
	}
}
