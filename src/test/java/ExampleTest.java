import helpers.AccountHelper;
import model.Login;
import model.Message;
import model.Token;
import model.User;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.*;

public class ExampleTest {


    private AccountHelper accountHelper;

    @BeforeClass
    public void init() {
        accountHelper = new AccountHelper();


    }

    // FIRST SCENARIO
    @Test(dataProvider = "first-scenario", dataProviderClass = DataProviders.class)
    public void testUserPost(String username, String password, String checkCode, String checkMessage) {

        Message message = accountHelper.userPost(new Login(username, password)).as(Message.class);

        Assert.assertEquals(message.getCode().toString(), checkCode);
        Assert.assertEquals(message.getMessage(), checkMessage);
    }

    // SECOND SCENARIO
    @Test(dataProvider = "second-scenario", dataProviderClass = DataProviders.class)
    public void testAuthorizePost(
            String username,
            String password,
            String authorizeStatus,
            String generateStatus,
            String generateResult,
            String authorizeStatus2
    ) {

        Login credentials = new Login(username, password);
        User user = accountHelper.userPost(credentials).as(User.class);
        // CHECK BOOKS IS EMPTY AND USERNAME
        assertEquals(user.getUsername(), username);
        assertTrue(user.getBooks().isEmpty());


        // CHECK NEW USER AUTHORIZE STATUS
        String status = accountHelper.authorizePost(credentials);
        assertEquals(status, authorizeStatus);

        // GENERATE TOKEN CALL
        Token token = accountHelper.generateToken(credentials);

        assertEquals(token.getStatus(), generateStatus);
        assertEquals(token.getResult(), generateResult);

        // CHECK USER AUTHORIZE STATUS AGAIN
        String status2 = accountHelper.authorizePost(credentials);
        assertEquals(status2, authorizeStatus2);

    }
}
