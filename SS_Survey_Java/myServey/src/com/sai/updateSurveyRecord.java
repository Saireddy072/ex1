package com.sai;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class updateSurveyRecord extends ActionElementBase{
	@Override
	public void doAction(String name, ActionElementData data) throws AudiumException {
		Util util = new Util();
		String lang = "en-us";
		Integer surveyID = (Integer)data.getSessionData("surveyID");
		String ani = data.getAni();
		data.addToLog("ani", ani);
		String dnis = data.getDnis();
		data.addToLog("dnis", dnis);
		
		
		boolean accepted = false;
		
		Date myDate = new Date();
		DateFormat ft = 
		      new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss a");

		data.addToLog("date", myDate.toString());
		data.setSessionData("surveyID", surveyID);
		data.setSessionData("ani", ani);
		data.setSessionData("dnis", dnis);
		
		data.setSessionData("myDate", ft.format(myDate));
		if(data.getElementData("offer_survey", "value").equalsIgnoreCase("yes")){
			accepted = true;
		}
		//Date created = new Date();
		//int i=util.updateSurveyRecord(surveyID, ani, dnis, lang, accepted, myDate );
		data.setSessionData("accepted", accepted);
		int i = util.updateSurveyRecord(data);
		if (i==5)
		{
			
			data.addToLog("exception", "Exception in update");
		}
		
		
	}
}
