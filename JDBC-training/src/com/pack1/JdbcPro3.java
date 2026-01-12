package com.pack1;

import java.sql.*;
import java.util.Scanner;

public class JdbcPro3
{
    String driver = "oracle.jdbc.OracleDriver";
    String driverUrl = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    String dbUser = "system";
    String dbPwd = "root";

    Scanner sc = new Scanner(System.in);



    Connection connect()
    {
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(driverUrl, dbUser, dbPwd);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

    void getEmpDetails()
    {
        System.out.println("------------- Employee Details -------------");
        try
        {
            Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Employee");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    void insertEmpData()
    {
        try
        {
            System.out.print("Enter Emp Id : ");
            String eid = sc.nextLine();

            System.out.print("Enter Emp First Name : ");
            String efname = sc.nextLine();

            System.out.print("Enter Emp Last Name : ");
            String elname = sc.nextLine();

            System.out.print("Enter Emp Salary : ");
            String esal = sc.nextLine();

            System.out.print("Enter Emp Address : ");
            String eaddr = sc.nextLine();

            Connection con = connect();
            Statement stmt = con.createStatement();

            System.out.println("-------- Enter the Employee details ---------\n");
            String sqlQuery = "insert into employee values('"+eid+"','"+efname+"','"+elname+"',"+esal+",'"+eaddr+"')";
            int rowCount = stmt.executeUpdate(sqlQuery);
            if (rowCount==0)
            {
                System.out.println("Data was not inserted");
            }
            else
            {
                System.out.println(rowCount+" row updated\n");
                System.out.println("-------------------------------------------------------------------------------");
                getEmpDetails();
            }
        }
        catch (SQLIntegrityConstraintViolationException sicve)
        {
            System.out.println("Duplicate Eid not Allowed");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    void getRecord()
    {
        System.out.print("Enter the Emp Id : ");
        String eId = sc.nextLine();
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Retrieving the Employee detail with Eid : "+eId+"\n");
        try
        {
            Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee where eid='"+eId+"'");
            if (rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+"\n");
            }
            else
            {
                System.out.println("No Employee found with Emp Id "+eId);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    void deleteEmpRecord()
    {
        System.out.print("Enter the Eid you want to Delete : ");
        String eId = sc.nextLine();
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("Deleting the Record for Emp Id "+eId);
        try
        {
            Connection con = connect();
            Statement stmt = con.createStatement();
            int rowCount = stmt.executeUpdate("delete from employee where eid = '"+eId+"'");
            if (rowCount==0)
            {
                System.out.println("Invalid Emp Id");
            }
            else
            {
                System.out.println("record with Emp id "+eId+" deleted successfully\n");
                System.out.println("-------------------------------------------------------------------------------");
                getEmpDetails();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    void updateSal()
    {
        System.out.println("Enter the Emp Id : ");
        String eId = sc.nextLine();

        System.out.println("Enter the New Salary : ");
        int sal = sc.nextInt();

        System.out.println("Updating the Salary for the Emp Id "+eId);
        try
        {
            Connection con = connect();
            Statement stmt = con.createStatement();
            int rowCount = stmt.executeUpdate("UPDATE employee SET esal = " + sal + " WHERE eid = '" + eId + "'");
            if (rowCount==0)
            {
                System.out.println("Emp id invalid");
            }
            else
            {
                System.out.println("Salary updated of the Employee with Eid "+eId+"\n");
                System.out.println("-------------------------------------------------------------------------------");
                getEmpDetails();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args)
    {
        JdbcPro3 obj = new JdbcPro3();
//      obj.getEmpDetails();
//        obj.insertEmpData();
//        obj.getRecord();
//        obj.deleteEmpRecord();
        obj.updateSal();
    }

}



