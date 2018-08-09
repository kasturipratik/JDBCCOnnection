package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */

/*add .jar file to the module in intellij as*/

/**
 * 1. Navigate to File
 * 2. Select Project Structure
 * 3. Navigate to Dependencies tab
 * 4. There a little + sign on the right hand side click on it
 * 5. Select jar or Directories
 * 6. Navigate to the folder where the mysql connector .jar file has been stored
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","password");

            if(con!=null){
                System.out.println("Connection Successful");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
