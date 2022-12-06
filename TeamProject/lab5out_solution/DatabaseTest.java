package lab5out_solution;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.*;

public class DatabaseTest {

	private String[] users = { "jmaxwell", "ahill", "twalters", "macie" };
	private String[] passwords = { "password", "password", "password", "password" };
	private String[] words = {"apple", "south", "storm", "hands", "bands", "lands", "sands", "stand"};
	private Database database;
	private int rando;
	private int randWord;

	// runs before any testing is done
	@Before
	public void setUp() throws Exception {
		database = new Database();
		rando = ((int) Math.random() * users.length);
		randWord = ((int) Math.random() * words.length);
	}

	
	@Test
	public void testUsernameExists() {
		String randRealUser = users[rando];
		
		Boolean expected = true;
		Boolean result = database.usernameExists(randRealUser);
				
		// compare expected with result
		assertEquals("Input: " + randRealUser, expected, result);
	}
	@Test
	public void testUsernameDoesNotExists() {
		String notRealUser = "not_real_user";
		
		Boolean expected = false;
		Boolean result = database.usernameExists(notRealUser);
				
		// compare expected with result
		assertEquals("Input: " + notRealUser, expected, result);
	}
	

	@Test
	public void testCreateNewAccount() throws SQLException {
		String newUser = "anewUser";
		String newPassword = "anewPassword";
		
		Boolean expected = true;
		Boolean result = database.createNewAccount(newUser, newPassword);
		
		// compare expected with result
		assertEquals("Username and Password: " + newUser + ", " + newPassword, expected, result);
	}
	@Test
	public void testCreateNewAccountFail() throws SQLException {
		// a username and password that already exists
		String user = "CoolUsername";
		String password = "thepassword";
		
		Boolean expected = false;
		Boolean result = database.createNewAccount(user, password);
		
		// compare expected with result
		assertEquals("Username and Password: " + user + ", " + password, expected, result);
	}

	
	@Test
	public void testVerifyAccount() {
		String user = "CoolUsername";
		String password = "thepassword";
		
		// should return true if account exists
		Boolean expected = true;
		Boolean result = database.verifyAccount(user, password);
		
		// compare expected with result
		assertEquals("Username and Password: " + user + ", " + password, expected, result);
	}
	@Test
	public void testVerifyAccountFail() {
		String user = "not_real_user";
		String password = "pass";

		// should return false because account does not exist
		Boolean expected = false;
		Boolean result = database.verifyAccount(user, password);
		
		// compare expected with result
		assertEquals("Userdname and Password: " + user + ", " + password, expected, result);
	}
	
	
	@Test
	public void testGetPlayerDatabaseData() {
		String expected = "CoolUsername";
		
		Player result = database.getPlayerDatabaseData(expected);
		
		// compare expected with result
		assertEquals("Username: " + expected, expected, result.getUsername());
	}
	

	@Test
	public void testGetThreeLetters() throws Exception {
		// either works or it doesn't
		String result = database.getThreeLettersFromDatabase();
	}
}
