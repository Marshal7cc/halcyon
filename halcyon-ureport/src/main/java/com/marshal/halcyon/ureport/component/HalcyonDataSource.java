package com.marshal.halcyon.ureport.component;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
public class HalcyonDataSource implements BuildinDatasource {

    @Autowired
    DataSource dataSource;

    @Override
    public String name() {
        return "halcyonDataSource";
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("ureport get datasource conncection failed!");
            e.printStackTrace();
        }
        return connection;
    }
}
