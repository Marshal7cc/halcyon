package com.marshal.halcyon.base.test.design.builder;

/**
 * @auth: Marshal
 * @date: 2019/7/30
 * @desc:
 */
public class DataSourceConfigBuilder {

    private String host;
    private String port;
    private String username;
    private String password;

    public DataSourceConfigBuilder host(String host) {
        this.host = host;
        return this;
    }

    public DataSourceConfigBuilder port(String port) {
        this.port = port;
        return this;
    }

    public DataSourceConfigBuilder username(String username) {
        this.username = username;
        return this;
    }

    public DataSourceConfigBuilder password(String password) {
        this.password = password;
        return this;
    }

    public DataSourceConfig build() {
        if (this.username == null) {
            throw new UnsupportedOperationException();
        }
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        if (host != null) {
            dataSourceConfig.setHost(host);
        }
        /**
         * ...其余属性省略
         */
        return dataSourceConfig;
    }

}
