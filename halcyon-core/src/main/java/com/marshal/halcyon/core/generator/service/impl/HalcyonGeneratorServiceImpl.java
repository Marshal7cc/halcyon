package com.marshal.halcyon.core.generator.service.impl;


import com.marshal.halcyon.core.generator.dto.DBColumn;
import com.marshal.halcyon.core.generator.dto.DBTable;
import com.marshal.halcyon.core.generator.dto.GeneratorInfo;
import com.marshal.halcyon.core.generator.service.HalcyonGeneratorService;
import com.marshal.halcyon.core.generator.util.DBUtil;
import com.marshal.halcyon.core.generator.util.FileUtil;
import com.marshal.halcyon.core.generator.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class HalcyonGeneratorServiceImpl implements HalcyonGeneratorService {

    @Autowired
    @Qualifier("sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<String> showTables() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<String> tables;

            Connection conn = DBUtil.getConnectionBySqlSession(sqlSession);
            tables = DBUtil.showAllTables(conn);
            conn.close();
            return tables;
        } catch (SQLException e) {
            logger.error("数据库查询出错");
        }
        return new ArrayList<>();
    }

    @Override
    public void generatorFile(GeneratorInfo info) throws Exception {
        int rs = 0;
        String tableName = info.getTargetName();
        String beanName = StringUtil.getBeanName(tableName);
        info.setDtoName(beanName + ".java");
        info.setControllerName(beanName + "Controller.java");
        info.setServiceName(beanName + "Service.java");
        info.setImplName(beanName + "ServiceImpl.java");
        info.setMapperName(beanName + "Mapper.java");
        info.setMapperXmlName(beanName + "Mapper.xml");
        DBTable dbTable = getTableInfo(tableName);
        try {
            rs = createFile(dbTable, info);
        } catch (IOException e) {
            rs = -1;
            logger.error(e.getMessage());
        } catch (Exception e) {
            rs = -1;
            logger.error(e.getMessage());
        }
    }

    // 获取table信息
    public DBTable getTableInfo(String tableName) {
        Connection conn = null;
        DBTable dbTable = new DBTable();
        List<DBColumn> columns = dbTable.getColumns();
        List<String> multiColumns = null;
        List<String> NotNullColumns = null;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 设置tablename
            dbTable.setName(tableName);
            conn = DBUtil.getConnectionBySqlSession(sqlSession);
            DatabaseMetaData dbmd = conn.getMetaData();
            // 获取主键字段
            String columnPk = DBUtil.getPrimaryKey(tableName, dbmd, conn.getCatalog());
            // 获取不为空的字段
            NotNullColumns = DBUtil.getNotNullColumn(tableName, dbmd, conn.getCatalog());
            // 获取表列信息
            ResultSet rs1 = DBUtil.getTableColumnInfo(tableName, dbmd, conn.getCatalog());

            while (rs1.next()) {
                String columnName = rs1.getString("COLUMN_NAME");
                if ("object_version".equalsIgnoreCase(columnName) || "create_time".equalsIgnoreCase(columnName) || "update_time".equalsIgnoreCase(columnName)) {
                    continue;
                }
                columns.add(setColumnInfo(rs1, columnPk, NotNullColumns, multiColumns));
            }
            rs1.close();
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return dbTable;
    }

    private DBColumn setColumnInfo(ResultSet rs1, String columnPk, List<String> NotNullColumns, List<String> multiColumns) throws SQLException {
        DBColumn column = new DBColumn();
        String columnName = rs1.getString("COLUMN_NAME");
        column.setName(columnName);
        String typeName = rs1.getString("TYPE_NAME");
        column.setJdbcType(typeName);
        if (StringUtils.isNotEmpty(rs1.getString("REMARKS"))) {
            column.setRemarks(rs1.getString("REMARKS"));
        }
        // 判断是否为主键
        if (columnName.equalsIgnoreCase(columnPk)) {
            column.setId(true);
        }
        // 判断是否为null字段
        for (String n : NotNullColumns) {
            if (columnName.equalsIgnoreCase(n) && !columnName.equalsIgnoreCase(columnPk)) {
                if ("BIGINT".equalsIgnoreCase(typeName)) {
                    column.setNotNull(true);
                } else if ("VARCHAR".equalsIgnoreCase(typeName)) {
                    column.setNotEmpty(true);
                }
            }
        }

        column.setColumnLength(rs1.getString("COLUMN_SIZE"));
        return column;
    }

    public int createFile(DBTable table, GeneratorInfo info) throws IOException {

        int rs = FileUtil.isFileExist(info);
        if (rs == 0) {
            if (!"NotOperation".equalsIgnoreCase(info.getDtoStatus())) {
                FileUtil.createDto(table, info);
            }
            if (!"NotOperation".equalsIgnoreCase(info.getControllerStatus())) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Controller, table, info);
            }
            if (!"NotOperation".equalsIgnoreCase(info.getMapperStatus())) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Mapper, table, info);
            }
            if (!"NotOperation".equalsIgnoreCase(info.getImplStatus())) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Impl, table, info);
            }
            if (!"NotOperation".equalsIgnoreCase(info.getServiceStatus())) {
                FileUtil.createFtlInfoByType(FileUtil.pType.Service, table, info);
            }
            if (!"NotOperation".equalsIgnoreCase(info.getMapperXmlStatus())) {
                FileUtil.createMapperXml(table, info);
            }
        }
        return rs;
    }

}
