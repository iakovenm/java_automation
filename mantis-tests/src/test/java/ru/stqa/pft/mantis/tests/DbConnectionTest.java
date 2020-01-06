package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.mantis.model.UserData;

import java.sql.*;
import java.util.HashSet;

public class DbConnectionTest {

    @Test
    public void testDBConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,username,password,email from mantis_user_mantis");
           HashSet<UserData> users = new HashSet<UserData>();
            while (rs.next()) {
               users.add(new UserData().withId(rs.getInt("id")).
                        withUsername(rs.getString("username")).
                        withPassword(rs.getString("password"))
                        .withEmail(rs.getString("email")));

            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);


            // Do something with the Connection
        } catch (
                SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
