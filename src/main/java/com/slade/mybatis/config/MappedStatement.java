package com.slade.mybatis.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappedStatement {
    private String id;
    private String resultType;
    private String parameterType;
    private String sql;
}
