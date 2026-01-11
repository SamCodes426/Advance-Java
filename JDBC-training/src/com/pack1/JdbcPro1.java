package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcPro1
{

    String driver = "oracle.jdbc.OracleDriver";
    String dbUrl = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    String dbUser = "system";
    String dbPwd = "root";

    void connect()
    {
        System.out.println("Connecting to the Database");
        try
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
            System.out.println("Connection Established Successfully");
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JdbcPro1 obj = new JdbcPro1();
        obj.connect();
    }
}
