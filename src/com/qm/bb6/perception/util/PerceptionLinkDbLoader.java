/*
 * @(#)PerceptionSchedule.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

import java.sql.*;
import java.util.*;


import blackboard.base.*;
import blackboard.db.*;
import blackboard.data.content.Content;
import blackboard.persist.*;
import blackboard.platform.BbServiceManager;

import com.qm.bb6.perception.servlet.CreateAssessmentServlet;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionLinkDbLoader  {
	

	
	private PerceptionLinkDbLoader(){
		// do nothing
	}
	
	/**
	 * Loads a list of content Ids relating to Perception links within a Bb course
	 * @param course_id   the Bb course
	 * @return   a list a Ids
	 * @throws java.sql.SQLException
	 * @throws blackboard.persist.PersistenceException
	 * @throws blackboard.db.ConnectionNotAvailableException
	 */
	public static List loadContentIdsByCourseId( Id course_id ) throws ConnectionNotAvailableException, SQLException, PersistenceException{
		ResultSet rSet = null;
		Connection conn = null;
		List idList = new ArrayList(); 
		try{
			conn = getDbConnection();
			StringBuffer SQL = new StringBuffer();
			SQL.append("select pk1 from COURSE_CONTENTS where CNTHNDLR_HANDLE = ? and crsmain_pk1 = ? ");
			PreparedStatement stmt = conn.prepareStatement(SQL.toString());
			stmt.setString(1, CreateAssessmentServlet.PERCEPTION_CONTENT_HANDLER);
			stmt.setInt(2, ((PkId) course_id).getPk1() );
			rSet = stmt.executeQuery();
			if( rSet != null ){
				while( rSet.next() ){
					idList.add( Id.generateId( Content.DATA_TYPE, rSet.getInt("pk1")) );
				}
				rSet.close();
			} 
		}finally{
			try{
				if( rSet != null )
					rSet.close();
			}catch(SQLException e){
				// ignore
			}
				
			if( conn != null )
				releaseDbConnection( conn );
		}
		return idList;
	}
	
	
	private static Connection getDbConnection() throws ConnectionNotAvailableException{
		try{
			return ConnectionManager.getDefaultConnection();
		}catch(blackboard.db.ConnectionNotAvailableException dbError){
			throw dbError;
		}catch(Exception e){
			throw new blackboard.db.ConnectionNotAvailableException("Connection not available, perhaps you need to set the context first", e);
		}
	}
	
	private static void releaseDbConnection(Connection conn) {
		ConnectionManager.releaseDefaultConnection(conn);
	}
	

}
