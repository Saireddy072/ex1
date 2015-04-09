/**
 * 
 */
package com.sai;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.audium.server.AudiumException;
import com.audium.server.session.ActionElementData;
import com.audium.server.voiceElement.ActionElementBase;

/**
 * @author admin
 *
 */
public class GetActiveSurvey extends ActionElementBase{
	@Override
	public void doAction(String name, ActionElementData data) throws AudiumException {
		int id = (Integer)data.getSessionData("id");
		int active = 0;
		String query = "select Active from Survey where ID='"+id+"'";
		GetActionValue getActionValue = new GetActionValue();
		data.setSessionData("query", query);
		getActionValue.doAction(name, data);
		ResultSet resultSet = (ResultSet)data.getSessionData("result");
		try {
			while(resultSet.next()){
				active = Integer.parseInt(resultSet.getString("Active")); 
				id = Integer.parseInt(resultSet.getString("ID")); 
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setSessionData("active", active);
		data.setSessionData("id", id);
	}
}
