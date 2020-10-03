/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shurik1686.testRT;

import ru.shurik1686.testRT.model.Colors;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author pop_av
 */
public class ServiceRT implements AutoCloseable {

    private Connection connection;
    final private String server = "127.0.0.1";
    final private String port = "1521";
    final private String sn = "indd";

    public static void main(String[] args) throws SQLException {
        System.out.println("Start!");
        try (ServiceRT colorService = new ServiceRT()) {
            colorService.setConnection(colorService.getConnection());
            System.out.println("Соединение = " + colorService.testConnection());
            ResultSet resultSet;
            resultSet = colorService.testExecuteStatement("select * from TSVETA");
            resultSet.next();
            System.out.println("Test 1 " + resultSet.getString("color_number"));
            //colorService.testPreparedStatement("select * from i_smpp where id in (?)", new ArrayList<String>(Arrays.asList("21","22")));
            resultSet = colorService.testPreparedStatement("select * from TSVETA where id in (?)", "8");
            resultSet.next();
            System.out.println("Test 2 " + resultSet.getString("name"));
            System.out.println("Test 3 " + colorService.testCallableStatement("{ call dbo.get_info(?,?)} ", 2));
            colorService.close();
            System.out.println("Соединение = " + colorService.testConnection());
            System.out.println("End!");
            System.out.println(getJSON()); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/*
    public static JSONArray getJSONtest() {
        return new JSONArray(Arrays.asList(new Colors(1, "red"), new Colors(2, "Orange"), new Colors(3, "Yellow")));
    }
*/
    public static JSONArray getJSON() {
        List rez = new LinkedList();
        try (ServiceRT colorService = new ServiceRT()) {
            colorService.setConnection(colorService.getConnection());
            ResultSet resultSet = colorService.testExecuteStatement("select color_number, name from TSVETA");
            resultSet.next();
            
            while (resultSet.next()) {
                String colorNumber = resultSet.getString("color_number");
                String name = resultSet.getString("name");
                Colors color = new Colors(colorNumber, name);
                rez.add(color);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONArray(rez);
    }

    public Connection getConnection() throws SQLException {
        //String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sn;
        String url = "jdbc:sqlserver://localhost;databaseName=test";
         /*String user = "admin";
          String passwd = "admin";*/
        String user = "sa";
        String passwd = "Pkj,ysq";
        //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        return DriverManager.getConnection(url, user, passwd);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String testConnection() throws SQLException {
        return String.valueOf(!connection.isClosed());
    }

    public ResultSet testExecuteStatement(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public ResultSet testPreparedStatement(String sql, String in) throws SQLException {
        PreparedStatement preparedStatement
                = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setString(1, in);
        return preparedStatement.executeQuery();
    }
    // Задание 2.
    public String testCallableStatement(String sql, int in) throws SQLException {
        CallableStatement callableStatement
                = connection.prepareCall(sql);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.setInt(1, in);
        callableStatement.execute();
        return callableStatement.getString(2);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
