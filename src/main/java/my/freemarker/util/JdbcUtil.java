package my.freemarker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.freemarker.entity.JavaAttr;

public class JdbcUtil {

    public static void main(String[] args) throws Exception {
        
    }
    
    public static List<JavaAttr> getAttrList(String tableName,String url,String username,String password){
        List<JavaAttr> javaAttrList = new ArrayList<>();
        String sql = "select * from "+tableName;  
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();  
            conn = DriverManager.getConnection(url, username, password);   
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);  
            rs = stmt.executeQuery(sql);  
            ResultSetMetaData metaDate = rs.getMetaData();
            for(int i=0;i<metaDate.getColumnCount();i++){
                String name = metaDate.getColumnName(i+1);
                Integer type = metaDate.getColumnType(i+1);
                JavaAttr ja = new JavaAttr();
                ja.setName(name);
                ja.setJdbcType(JdbcTypeNameTranslator.getJdbcTypeName(type));
                ja.setJavaType(JavaTypeNameTranslator.getJavaTypeName(type));
                javaAttrList.add(ja);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();  
                stmt.close();  
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
        return javaAttrList;
    }
}
