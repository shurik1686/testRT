/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shurik1686.rttest.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import ru.shurik1686.rttest.model.Colors;

/**
 *
 * @author pop_av
 */
public interface TRRepository {
    List<Colors> getColors();
    String getInfo(String parameter) throws SQLException;
    Connection getConnection() throws SQLException;
}
