import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.examples.designpatterns.model.DaoFactory;
import com.examples.designpatterns.model.Database;
import com.examples.designpatterns.model.UserDAO;
import com.examples.designpatterns.model.UserDetails;

public class UserDAOTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.getInstance().connect();
		System.out.println("Setup Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Database.getInstance().disconnect();
		System.out.println("Tear Down After class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear Down");
	}

	@Test
	public void test() throws SQLException {
		UserDAO userDAO = DaoFactory.getUserDAO();
		
		UserDetails user1 = new UserDetails("bob", "letmein");
		UserDetails user2 = new UserDetails("george", "letmein");
		
		userDAO.addUser(user1);
		userDAO.addUser(user2);
		
		List<UserDetails> userDetailsList = userDAO.getUserList();
		
		Assert.assertEquals("Check for the records in the Database", 3, userDetailsList.size());
		
		Assert.assertEquals("These two people should be the same", user1, userDetailsList.get(1));
		Assert.assertEquals("These two people should be the same", user1, userDetailsList.get(2));
	}

}
