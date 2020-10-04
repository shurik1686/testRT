/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shurik1686.rttest.service;

import org.json.JSONArray;
import ru.shurik1686.rttest.repository.MemoryColorRepository;
import ru.shurik1686.rttest.repository.MsSqlColorRepository;
import ru.shurik1686.rttest.repository.TRRepository;

/**
 *
 * @author pop_av
 */
public class RTService {

    private final TRRepository repository;

    public RTService() {
        // Memory imple
        // repository = new MemoryColorRepository();
        // MS SQL imple
        repository = new MsSqlColorRepository();
        // Oracle imple
        //repository = new OracleColorRepository();
    }

    public JSONArray getJSON() {
        return new JSONArray(repository.getColors());
    }

}
