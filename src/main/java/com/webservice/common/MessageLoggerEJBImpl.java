
package com.webservice.common;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MessageLoggerEJBImpl{

	public void saveMessageLog(){

		String sql     = "";
		try{
			DataSource pool;
			Connection conn = null;
			Statement  stmt = null;	 

			/** Create a JNDI Initial context to be able to lookup the DataSource */
			InitialContext ctx = new InitialContext();
			/** Lookup the DataSource, which will be backed by a pool */

			/**   that the application server provides. */
			pool = (DataSource)ctx.lookup("java:jboss/datasources/webDS");
			if (pool == null){
				throw new Exception("Unknown DataSource 'jdbc/webDS'");
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();	

			sql = "select * from Employee";

			System.out.println("email :"+sql.toString());
			stmt.executeQuery(sql);

		}catch(Exception e){
			e.printStackTrace();
		}      
	}
}
