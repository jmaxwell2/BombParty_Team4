package lab5out_solution;

import java.util.*;
import java.sql.*;
import java.io.*;

public class Database {
	private Connection conn;
	// Add any other data fields you like – at least a Connection object is
	// mandatory

	public Database() {
		// Where you will start the connection to the database

		try {
			// 1. need to create the file input string
			// open the database properties using the FileInputString
			FileInputStream fis;
			fis = new FileInputStream("lab5out_solution/db.properties");

			// create a properties object
			Properties props = new Properties();

			// Load the properties
			props.load(fis);

			// get the URL from props
			String url = props.getProperty("url");

			// get the username from props
			String user = props.getProperty("user");

			// get the password from props
			String password = props.getProperty("password");

			// use the driver manager to create a connection
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> query(String query) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			// Create a statement from the connection object
			Statement stmt = conn.createStatement();

			// Create a result set
			ResultSet rs = stmt.executeQuery(query);

			// get the number of columns
			ResultSetMetaData rmd = rs.getMetaData();
			int noCols = rmd.getColumnCount();

			String row = new String();
			while (rs.next()) {
				row = "";

				// read each column within a row
				for (int i = 0; i < noCols; i++) {
					row += rs.getString(i + 1) + ",";
				}

				// add the row string to the ArrayList
				list.add(row);
			}

			if (row.length() == 0)
				return null;
			else
				return list;
		} catch (SQLException sqlError) {
			return null;
		}
	}

	public void executeDML(String dml) throws SQLException {
		// Add your code here

		// create your statement
		Statement stmt = conn.createStatement();

		// execute
		stmt.execute(dml);
	}

	// function that fetches a random word from the database in order to extract
	// random three letters
	public String getThreeLettersFromDatabase() {
		String q = "SELECT WORD FROM WORDS ORDER BY RAND() LIMIT 1;";

		// run the query and get the word in the first index of result
		ArrayList<String> result = query(q);
		String word = result.get(0);
		word = word.substring(0, word.length() - 1);

		// randomly grab an index within the range(size of string)
		Integer wordSize = word.length();
		Integer randIndex = (int) (Math.random() * wordSize);

		// ensure that randIndex+2 is not greater than the length-1 of the word
		while (randIndex + 2 >= wordSize)
			--randIndex;

		Integer randIndex2 = randIndex + 1;
		Integer randIndex3 = randIndex + 2;

		// grab the 3 letters starting from the randIndex letters
		String threeLetters = Character.toString(word.charAt(randIndex)) + Character.toString(word.charAt(randIndex2))
				+ Character.toString(word.charAt(randIndex3));

		return threeLetters;
	}
	
	// function that checks if the input word is within the database
	// how we will verify if the input word is REAL
	public Boolean verifyInputWord(String letters, String input) {
		
		// first gets all the words in the database that contains the three letters
		// these will be the pool of words that is used to verify if the input word is a "real" word
		String q = "SELECT * FROM WORDS WHERE WORD RLIKE('" + letters + "');";
		ArrayList<String> result = query(q);
		
		int len = 0;
		
		for (int i = 0; i < result.size(); i++) {
			len = result.get(i).length();
			result.set(i, result.get(i).substring(0, len - 1));
		}
		
		// next, verify if the input word is in the query result
		if (result.contains(input))
			return true;
		else
			return false;
	}

	// function that returns all the player data, given a username
	public Player getPlayerDatabaseData(String username) {
		String q = "SELECT USERNAME, WINS, LOSSES FROM USER WHERE USERNAME = '" + username + "';";

		ArrayList<String> result = query(q);

		// result is a String seperated by commas,,, so parse is into an array
		String[] data = result.get(0).split(",");

		// fill in the Player object to return
		Player returnPlayer = new Player();
		returnPlayer.setUsername(data[0]);
		returnPlayer.setWins(Integer.parseInt(data[1]));
		returnPlayer.setLosses(Integer.parseInt(data[2]));
		returnPlayer.setWinLossRatio(data[1] + " : " + data[2]);
		returnPlayer.setNumOfHearts(3);

		return returnPlayer;
	}

	// function that verifies a username and password are in the database
	public Boolean verifyAccount(String username, String password) {
		String q = "SELECT USERNAME FROM USER WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "';";

		// if the username and password is correct, then the array list will contain the
		// username
		// if the username and password are incorrect, then the resultList will be null
		ArrayList<String> result = query(q);

		if (result == null)
			return false;
		else
			return true;

	}

	// function that checks to see if the username does not already exist in the
	// database
	// if the username does NOT exist, then add the username and password to the
	// database
	public Boolean createNewAccount(String username, String password) {

		// check to see if the username exists in the database
		if (usernameExists(username))
			return false;
		else {
			// this means the username does not exist
			// therefore add the username and password to the database'
			String stm = "INSERT INTO USER VALUES('" + username + "','" + password + "', 0, 0);";

			try {
				executeDML(stm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	// helper function that determines if a usernname already exists in the database
	public Boolean usernameExists(String username) {
		String q = "SELECT USERNAME FROM USER WHERE USERNAME = '" + username + "';";

		// checks if the database contains the username
		ArrayList<String> result = query(q);

		// if null, then the username does not exist
		if (result == null)
			return false;
		else
			return true;

	}
	
	public void incrementWins(String username) {
		String stm = "update user set wins = wins + 1 where username = '" + username + "';";

		try {
			executeDML(stm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void incrementLosses(String username) {
		String stm = "update user set losses = losses + 1 where username = '" + username + "';";

		try {
			executeDML(stm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
