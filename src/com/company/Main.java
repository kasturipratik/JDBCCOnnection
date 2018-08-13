package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


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
        Statement  st  = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/temp","root","password");

            if(con!=null){
                System.out.println("Connection Successful");
            }
            st = con.createStatement();
            //create table
            st.executeUpdate("create table employee( "
                    + "emp_id int(3) primary key , "
                    + "emp_name varchar(20),"
                    + " salary float(8,2),"
                    + "username varchar(20),"
                    + "password varchar(20))");

            //add data
            int x = st.executeUpdate("insert into employee values(100,'Pratik',9999.99,'pratik','password')");
            x+= st.executeUpdate("insert into employee values(101,'Pratap',9999.99,'pratap','password')");

            System.out.println(x + " Rows inserted...");
            //######################################
            rs = st.executeQuery("select * from employee;");

            //user input storing in database
            Scanner scan = new Scanner(System.in);
            ps = con.prepareStatement("insert into employee values(?,?,?,?,?)");
            String choice ="";
            do {
                System.out.println("Enter id:");
                int id = scan.nextInt();
                scan.nextLine();

                //insert statement using preparedStatement
                ps.setInt(1, id);
                //-------------------
                System.out.println("Enter name: ");
                String name = scan.nextLine();

                ps.setString(2, name);
                //---------------------
                System.out.println("Enter salary: ");
                double salary = scan.nextDouble();
                scan.nextLine();

                ps.setDouble(3, salary);
                //-------------------------
                System.out.println("Enter username: ");
                String userName = scan.nextLine();
                ps.setString(4,userName);

                //------------------------------
                System.out.println("Enter password: ");
                String password = scan.nextLine();
                ps.setString(5, password);


                int num = ps.executeUpdate();

                System.out.println("Do you want to add more user:");
                choice  = scan.nextLine();

            }while(choice.equalsIgnoreCase("Y"));

            //view all the data
            rs = st.executeQuery("select * from employee;");
            while(rs.next()) {
                System.out.println("Employee Id: "+ rs.getInt(1));
                System.out.println("Name: "+ rs.getString(2));
                System.out.println("Salary: "+ rs.getDouble(3));
                System.out.println("User Name: " +rs.getString(4));
                System.out.println("Password :"+ rs.getString(5));
                System.out.println("********************");
            }

            //delete data from table

            ps =con.prepareStatement("delete from employee where emp_id =?");

            System.out.println("Enter the id to delete: ");
            int deleteId = scan.nextInt();
            scan.nextLine();

            ps.setInt(1, deleteId);
            int a = ps.executeUpdate();
            System.out.println(a +" row deleted");
            System.out.println("**********************");
            //data after delete is complete
            System.out.println("Data after delete is completed");
            rs = st.executeQuery("select * from employee;");
            while(rs.next()) {
                System.out.println("Employee Id: "+ rs.getInt(1));
                System.out.println("Name: "+ rs.getString(2));
                System.out.println("Salary: "+ rs.getDouble(3));
                System.out.println("User Name: " +rs.getString(4));
                System.out.println("Password :"+ rs.getString(5));
                System.out.println("********************");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
