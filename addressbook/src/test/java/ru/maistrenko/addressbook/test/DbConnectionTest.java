package ru.maistrenko.addressbook.test;

import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by User on 07.02.2018.
 */
public class DbConnectionTest {
    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            int count = 0;
            while (resultSet.next()){
                groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                        .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));
                count++;
            }
            resultSet.close();
            statement.close();
            conn.close();
            System.out.println(count);
            System.out.println(groups);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
