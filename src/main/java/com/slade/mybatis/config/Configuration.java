package com.slade.mybatis.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private DataSource dataSource;
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();
}
