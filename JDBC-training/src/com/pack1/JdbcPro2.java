package com.pack1;

import java.sql.*;
import java.util.Scanner;

public class JdbcPro2
{
    String driver = "oracle.jdbc.OracleDriver";
    String driverUrl = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    String dbUser = "system";
    String dbPwd = "root";

    Scanner sc = new Scanner(System.in);

    String sqlQuery = "select * from employee";
    String sqlQuery2 = "insert into Employee values('107','Samarpit','Maurya', 45000, 'Rdr')";
    String sqlQuery3 = "delete from employee where eid='108';";
    String sqlQuery4 = "update employee set esal=65000 where eid='101';";



//    ------------------------------ Connect Method ------------------------------

    Connection connect()
    {
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(driverUrl,dbUser,dbPwd);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

//    ---------------------------------------------------------------------------------



//    ------------------------- Get Employee Data method -------------------------------

    void getEmpData()
    {
        System.out.println("-------------------- Employee Details -------------------\n");
        try
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(driverUrl,dbUser,dbPwd);
            System.out.println("Database Connection Established\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
//                int sal = rs.getInt(4);
//                String name = rs.getString(2);
//                System.out.println(name+" "+sal);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------------------------------




//--------------------------------- Insert Employee Data method -----------------------------------

    void insertEmpData()
    {
        System.out.println("Inserting the data into Employee table");

        try
        {

            Connection con = connect();
            Statement stmt = con.createStatement();
            int rowCount = stmt.executeUpdate(sqlQuery2);
            if (rowCount==0)
            {
                System.out.println("Data not inserted");
            }
            else
            {
                System.out.println(rowCount+" row is inserted");
                System.out.println("--------------------------------");
                getEmpData();
            }

        }

        catch (SQLIntegrityConstraintViolationException sicve)
        {
            System.out.println("Duplicate EmpId's are not allowed");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    //-----------------------------------------------------------------------------------------------




    //-------------------------------- Delete Employee Data method -----------------------------------

    void deleteEmpData()
    {
        System.out.println("Deleting data from Employee Table");
        try
        {

            Connection con = connect();
            Statement stmt = con.createStatement();
            int rowCount = stmt.executeUpdate(sqlQuery3);
            if (rowCount==0)
            {
                System.out.println("There is NO Employee with given Eid");
            }
            else
            {
                System.out.println(rowCount+" row deleted");
                System.out.println("Do you want to view the the table data ? (Y/N)");
                char choice = sc.nextLine().charAt(0);
                switch (choice)
                {
                    case 'y','Y':
                        getEmpData();
                        break;
                    case 'n','N':
                        System.exit(0);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//-----------------------------------------------------------------------------------------------





//-------------------------------- Update Employee Data method ---------------------------------

    void updateEmpData()
    {
        System.out.println("Updating Emp data");
        try
        {
                Connection con = connect();
                Statement stmt = con.createStatement();
                int rowCount = stmt.executeUpdate(sqlQuery4);
                if (rowCount==0)
                {
                    System.out.println("given empId is NOT available");
                }
                else
                {
                    System.out.println("Salary Updated");
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//-----------------------------------------------------------------------------------------------





//-------------------------------------- Main Method ------------------------------------------


    public static void main(String[] args)
    {
            JdbcPro2 obj = new JdbcPro2();
//            obj.getEmpData();
//            obj.insertEmpData();
//            obj.deleteEmpData();
//            obj.updateEmpData();
    }
}
