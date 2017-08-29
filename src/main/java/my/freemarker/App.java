package my.freemarker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.freemarker.entity.JavaAttr;
import my.freemarker.util.GeneratorUtil;
import my.freemarker.util.JdbcUtil;
import my.freemarker.util.StringUtil;
import my.freemarker.util.XmlUtil;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main( String[] args ) throws Exception{
        Map<String, Object> m = XmlUtil.parseXml("context.xml");
        List<Map<String, String>> tables = (List<Map<String, String>>) m.get("tables");
        Map<String, String> jdbcConnectionMap = (Map<String, String>) m.get("jdbcConnection");
        Map<String, String> baseMap = (Map<String, String>) m.get("base");
        
        String driverClass = jdbcConnectionMap.get("driverClass");
        String url = jdbcConnectionMap.get("url");
        String username = jdbcConnectionMap.get("username");
        String password = jdbcConnectionMap.get("password");
        
        String packageName = baseMap.get("packageName");
        String basePackageName = baseMap.get("basePackageName");
        String [] dirs = packageName.split("\\.");
        Path dir = Paths.get(baseMap.get("outputDir"),dirs);
        String outputDir = dir.toString();
        tables.forEach(table->{
            String tableName = table.get("tableName");
            String className = StringUtil.underlineToUpperCamel(tableName);
            List<JavaAttr> attrList = JdbcUtil.getAttrList(tableName, url, username, password);
            GeneratorUtil g = new GeneratorUtil(tableName);
            g.generatorAll(packageName, basePackageName, className, attrList, outputDir);
        });
            
    }
    
}
