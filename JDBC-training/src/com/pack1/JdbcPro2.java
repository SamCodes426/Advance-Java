package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcPro2
{
    String driver = "oracle.jdbc.OracleDriver";
    String driverUrl = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    String dbUser = "system";
    String dbPwd = "root";
    String sqlQuery = "select * from employee";

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

    public static void main(String[] args)
    {
            JdbcPro2 obj = new JdbcPro2();
            obj.getEmpData();
    }
}
