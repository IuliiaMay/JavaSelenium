package mantis.test;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.*;


/**
 * Created by User on 19.02.2018.
 */
public class LoginTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
