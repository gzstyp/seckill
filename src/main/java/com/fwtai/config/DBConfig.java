package com.fwtai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@SpringBootConfiguration
public class DBConfig{

    @Autowired
    private Environment env; /**从配置里获取*/

}