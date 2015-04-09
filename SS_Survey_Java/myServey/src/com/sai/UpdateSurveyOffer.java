package com.sai;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

public class UpdateSurveyOffer extends ActionElementBase{
	@Override
	public void doAction(String name, ActionElementData data) throws AudiumException {
		String dnis = data.getDnis();
		int surveyId = 0;
		int id = 0;
		String query = "select id, SurveyID from SurveyDNIS where DNIS="+data.getDnis();
		GetActionValue getActionValue = new GetActionValue();
		data.setSessionData("query", query);
		getActionValue.doAction(name, data);
		ResultSet resultSet = (ResultSet)data.getSessionData("result");
		try {
			while(resultSet.next()){
				surveyId = Integer.parseInt(resultSet.getString("SurveyID")); 
				id = Integer.parseInt(resultSet.getString("ID")); 
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setSessionData("surveyId", surveyId);
		data.setSessionData("id", id);
	}
}
