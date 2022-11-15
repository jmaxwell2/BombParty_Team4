package lab5out_solution;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
	
	private Connection conn;
	  //Add any other data fields you like – at least a Connection object is mandatory
	  public Database()
	  {
		    //Open database properties using the FileInputStream
			try {
				FileInputStream fis = new FileInputStream("lab5out_solution/db.properties");
				
				//Open properties object
				Properties prop = new Properties();
				
				//Load the properties
				try {
					prop.load(fis);
					
					String url = prop.getProperty("url");
					String user = prop.getProperty("user");
					String pass = prop.getProperty("password");
					try {
						conn = DriverManager.getConnection(url, user, pass);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	  }
	  
	  public ArrayList<String> query(String query)
	  {
		  ArrayList<String> list = new ArrayList<String>();
		  
		  try {
			  //Create a statement form the Conn object
			  Statement statement = conn.createStatement();
			  
			  //Create a Result set
			  ResultSet rs = statement.executeQuery(query);
			  
			  String row = new String();
			  
			  ResultSetMetaData rmd = rs.getMetaData();
			  int no_columns = rmd.getColumnCount();
			  //System.out.println(no_columns);
			  
			  
			  while(rs.next())
			  {
				  for (int i = 0; i < no_columns; i++)
				  {
					  row += rs.getString(i+1);
				  }
				  list.add(row);
			  }
			  
			  if(row.length() == 0)
			  {
				  System.out.println("return null");
				  return null;
			  } else {
				  System.out.println("return list");
				  return list;
			  }
		  
		  } catch (SQLException sql) {
			  return null;
		  }
	  }
	  
	  public void executeDML(String dml) throws SQLException
	  {
		  Statement statement = conn.createStatement();
		  
		  statement.execute(dml);
		  
		  //ResultSet rs = statement.executeQuery(dml);
	  }

}
