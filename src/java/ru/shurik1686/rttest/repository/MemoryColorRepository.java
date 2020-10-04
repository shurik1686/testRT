/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shurik1686.rttest.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import ru.shurik1686.rttest.model.Colors;

/**
 *
 * @author pop_av
 */
public class MemoryColorRepository implements TRRepository {

    @Override
    public List<Colors> getColors() {
        return Arrays.asList(new Colors("01", "red"), new Colors("02", "Orange"), new Colors("03", "Yellow"),
                new Colors("04", "Green"), new Colors("05", "Blue"), new Colors("06", "Dark blue"),
                new Colors("07", "Violet"), new Colors("08", "Green"), new Colors("09", "Orange")
        );
    }

    @Override
    public String getInfo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
