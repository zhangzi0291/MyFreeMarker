package my.freemarker.entity;

import java.sql.Types;

public class JavaAttr {

    private String name ;
    private String javaType;
    private String jdbcType;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJavaType() {
        return javaType;
    }
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
    public String getJdbcType() {
        return jdbcType;
    }
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
    @Override
    public String toString() {
        return "JavaAttr [name=" + name + ", javaType=" + javaType + ", jdbcType=" + jdbcType + "]";
    }
    
}
