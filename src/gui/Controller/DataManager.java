package gui.Controller;

import java.sql.*;

public class DataManager
{
    public Connection myConn;
    public  DataManager() throws SQLException
    {
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcm?useTimezone=true&serverTimezone=UTC","root","2367");
        this.myConn = myConn;
    }

    public void addClientData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "insert into clients (name, type, signingDate, cost, activity, phoneNumber, clothesNum) values ";
        stm.execute(sql + values);
    }

    public void updateClientData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "update clients set ";
        stm.executeUpdate(sql + values);
    }

    public void addClothesData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "insert into clothes (owner_id, type, bodySize, status, colorType, arrDate, activity) values ";
        stm.execute(sql + values);
    }

    public void updateClothesData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "update clothes set ";
        stm.executeUpdate(sql + values);
    }

    public ResultSet getData(String tableName) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "select * from ";
        ResultSet rs = stm.executeQuery(sql + tableName);
        return rs;
    }

    public void addPaymentData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "insert into payments (employeeName,accountName,cost,date) values ";
        stm.execute(sql + values);
    }

    public void deleteData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "delete from ";
        stm.execute(sql + values);
    }

    public void addEmployeesData(String values) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "insert into employees (name,position,phoneNumber,salary,hiringDate) values ";
        stm.execute(sql + values);
    }
    public int getCount(String tableName) throws SQLException
    {
        Statement stm = myConn.createStatement();
        String sql = "select count(*) as rowcount from ";
        ResultSet rs = stm.executeQuery(sql + tableName);
        rs.next();
        int count = rs.getInt("rowcount");
        rs.close();
        return count;

    }

}
