/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shurik1686.rttest.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import ru.shurik1686.rttest.model.Colors;

/**
 *
 * @author zayc
 */
public class MsSqlColorRepository implements TRRepository {

    static {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    final private String server = "127.0.0.1"; //System.getenv("RT_SERVER_HOST") 
    final private String sn = "test";

    @Override
    public List<Colors> getColors() {
        List rez = new LinkedList();
        try (Connection conn = getConnection()) {
            ResultSet resultSet = conn.prepareStatement("select color_number, name from TSVETA").executeQuery();

            while (resultSet.next()) {
                String colorNumber = resultSet.getString("color_number");
                String name = resultSet.getString("name");
                Colors color = new Colors(colorNumber, name);
                rez.add(color);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rez;
    }

    @Override
    public String getInfo(String parameter) {
        String rez = "";
        try (Connection connection = getConnection()) {
            CallableStatement callableStatement
                    = connection.prepareCall("{ call admin.get_info(?,?)} ");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.setString(1, parameter);
            callableStatement.execute();
            rez = callableStatement.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://" + server + ";databaseName=" + sn;
        String user = "sa";
        String passwd = "Pkj,ysq";
        return DriverManager.getConnection(url, user, passwd);
    }

}
