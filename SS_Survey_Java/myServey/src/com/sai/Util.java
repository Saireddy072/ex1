package com.sai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.audium.server.session.ActionElementData;

public class Util {
	public Connection con = null;
	public Statement statement = null;
	/**
	 * the below is constructor will be called whenever an object will be created for this class.
	 * and it will initialize the connection, statement and driver.
	 */
	public Util() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.con = DriverManager.getConnection("jdbc:sqlserver://10.1.6.196:1433;databaseName=SurveyDb;user=speech;password=soft;");
			this.statement = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to check the customer for active and without startdate and endDate.
	 * @param surveyID and @return boolean value
	 */
	public boolean checkForActiveWithDate(Integer surveyID,ActionElementData data){
		ResultSet rs = null;
		try{
			data.addToLog("In", "Action 1");
			String query = "select Active,StartDate,EndDate from Survey where ID='"+surveyID+"';";
			boolean active = false;
			Date currentDate = new Date();
			Date startDate = new Date();
			Date endDate = new Date();
			rs = statement.executeQuery(query);
			if(rs.next()){
				data.addToLog("Active", String.valueOf(rs.getBoolean(1)));
				active = rs.getBoolean(1);
				data.addToLog("startDate", String.valueOf(rs.getDate(2)));
				startDate = rs.getDate(2);
				data.addToLog("endDate", String.valueOf(rs.getDate(3)));
				endDate = rs.getDate(3);
			}
			if(active){
				//Comparing the current date with startdate and end date. 
				if(currentDate.compareTo(startDate)==1 && currentDate.compareTo(endDate)==-1){
					return true;
				}
			}
		}catch(Exception exp){
			data.addToLog("Exception in util", exp.getMessage());
			exp.printStackTrace();
		}
		return false;
	}
	/**
	 * The below method is to update the table info about the customer who is interested in survey.
	 * @param surveyID, @param ani, @param dnis, @param lang, @param accepted, @param created, @return status
	 */
	//public int updateSurveyRecord(Integer surveyID,String ani, String dnis, String lang, boolean accepted, Date created){
	public int updateSurveyRecord(ActionElementData data){
		int status = 0;
		try{
			//data.addToLog("In", "Update Action");
			//Updating the values in db that is ANI, DNIS, Language, Accepted, Created based on surveyID.
			//String query = "UPDATE SurveyAcceptance SET ANI="+ani+",DNIS="+dnis+",Accepted='"+accepted+"',Created="+created+" where ID="+surveyID+";";


			String query = "insert into SurveyAcceptance(SurveyID,ani,dnis,accepted,created) values ("+
			data.getSessionData("surveyID")+","+data.getSessionData("ani")+",'"+data.getSessionData("dnis")+"','"+data.getSessionData("accepted")+"',"+data.getSessionData("created")+")";

			data.addToLog("Record", "updateSurveyRecord");
			statement.executeUpdate(query);
		}catch(Exception exp){
			data.addToLog("Records not updated", "updateSurveyRecord");
			return 5;

		}
		return status;//if the value of status is more than 1 then row is updated successfully.
	}
	public static void main(String[] args) {
		Util util = new Util();
		try{
			System.out.println(util.getQid("1"));
			
			//util.updateSurveyRecord(22222222, "0111111234", "7654", "en", true, null);
			System.out.println("records updated");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public ResultSet getQid(String surveyID){
		ResultSet rs = null;
		
		try{
			
			String queqry = "select type,TTS from dbo.Question where ID="+surveyID;
			rs = statement.executeQuery(queqry);
			//while(rs.next()){
			//System.out.println("TTS and type " +rs.getString(1));
			
			
			//}
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		return rs;

	}
	
	public int  UpdateResponse(int SurveyID,int SurveyAcceptance,int QuestionID,String Response){
		int status =  0;
		try{
			String query = "insert into SurveyResponse values("+SurveyID+","+SurveyAcceptance+","+QuestionID+","+Response+")";
			statement.executeUpdate(query);
		}catch(Exception e){
			
			System.out.println("Error in inserting data");
		return 5;
		}
	return status;
	}
	
}



