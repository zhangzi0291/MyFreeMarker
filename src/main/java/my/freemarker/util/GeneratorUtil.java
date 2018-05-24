package my.freemarker.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import my.freemarker.entity.JavaAttr;

public class GeneratorUtil {
    ClassLoader classLoader = null;
    Configuration cfg = null;
    Path p = null;
    Map<String, Object> root = null;
    String tableName = null;
    String path = null;
    {
        
        classLoader = this.getClass().getClassLoader()  ;
        path = classLoader.getResource("ftl").getPath().substring(1);
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(new File(path) );
        } catch (IOException e) {
            e.printStackTrace();
        } 
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
    public GeneratorUtil(String tableName  ) {
        this.tableName = tableName ;
    }
    
    public void generatorHtml(String className, List<JavaAttr> attrList, String outputDir){
        System.out.println("开始生成");
        generatorEditHtmlJsp(className,  attrList, outputDir);
        generatorAddHtmlJsp(className,  attrList, outputDir);
        generatorListHtmlJsp(className,  attrList, outputDir);
        System.out.println("全部完成");
    }
    
    public void generatorJava(String packageName, String basePackageName, String className, List<JavaAttr> attrList, String outputDir){
        System.out.println("开始生成");
        generatorController(packageName, className, basePackageName, attrList, outputDir);
        generatorDao(packageName, className, basePackageName, attrList, outputDir);
        generatorJavaBean(packageName, className, attrList, outputDir);
        generatorJavaBeanExample(packageName, className, basePackageName, attrList, outputDir);
        generatorMapper(packageName, className, attrList, outputDir);
        generatorService(packageName, className, basePackageName, attrList, outputDir);
        generatorServiceImpl(packageName, className, basePackageName, attrList, outputDir);
        System.out.println("全部完成");
    }
    
    public void generatorAll(String packageName, String basePackageName, String className, List<JavaAttr> attrList, String outputDir){
        System.out.println("开始生成");
        generatorEditHtmlJsp(className,  attrList, outputDir);
        generatorAddHtmlJsp(className,  attrList, outputDir);
        generatorListHtmlJsp(className,  attrList, outputDir);
        generatorController(packageName, className, basePackageName, attrList, outputDir);
        generatorDao(packageName, className, basePackageName, attrList, outputDir);
        generatorJavaBean(packageName, className, attrList, outputDir);
        generatorJavaBeanExample(packageName, className, basePackageName, attrList, outputDir);
        generatorMapper(packageName, className, attrList, outputDir);
        generatorService(packageName, className, basePackageName, attrList, outputDir);
        generatorServiceImpl(packageName, className, basePackageName, attrList, outputDir);
        System.out.println("全部完成");
    }

    public void generatorEditHtmlJsp(String className ,List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Edit");
        String filePath = outputDir + "/view/"+className.substring(0, 1).toLowerCase() + className.substring(1)+"Edit.jsp";
        String ftlName = "html/edit.ftl";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("className", className);
        
        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成Edit完成");
    }
    
    public void generatorAddHtmlJsp(String className ,List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Add");
        String filePath = outputDir + "/view/"+className.substring(0, 1).toLowerCase() + className.substring(1)+"Add.jsp";
        String ftlName = "html/add.ftl";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("className", className);
        
        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成Add完成");
    }
    
    public void generatorListHtmlJsp(String className ,List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成List");
        String filePath = outputDir + "/view/"+className.substring(0, 1).toLowerCase() + className.substring(1)+"List.jsp";
        String ftlName = "html/list.ftl";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("className", className);

        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成List完成");
    }
    
    public void generatorController(String packageName,String className ,String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Controller");
        String filePath = outputDir + "/controller/"+className+"Controller.java";
        String ftlName = "controller.ftl";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("basePackageName", basePackageName);
        root.put("className", className);
        
        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成Controller完成");
    }
    
    public void generatorServiceImpl(String packageName,String className ,String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成ServiceImpl");
        String filePath = outputDir + "/service/impl/"+className+"ServiceImpl.java";
        String ftlName = "serviceImpl.ftl";
        
        String Example = basePackageName + ".Example";
        String Page = basePackageName + ".Pageable";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("basePackageName", basePackageName);
        root.put("className", className);
        root.put("Example", Example);
        root.put("Page", Page);
        
        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成ServiceImpl完成");
    }
    /**
     * 
      * 生成导出excel文件
      *@param packageName
      *@param className
      *@param basePackageName
      *@param attrList
      *@param outputDir 
      *@date 2018年3月28日 下午2:47:09
      *@author zhengxiangnan
     */
    public void generatorExcelServiceImpl(String packageName,String className ,String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成ServiceImpl");
        String filePath = outputDir + "/service/impl/"+className+"ExcelServiceImpl.java";
        String ftlName = "ExcelServiceImpl.ftl";
        
        String Example = basePackageName + ".Example";
        String Page = basePackageName + ".Pageable";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("basePackageName", basePackageName);
        root.put("className", className);
        root.put("Example", Example);
        root.put("Page", Page);
        
        root.put("attrs", getAttrs(attrList));
        outFile(ftlName, filePath, root);
        System.out.println("生成ExcelServiceImpl完成");
    }
    public void generatorService(String packageName,String className ,String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Service");
        String filePath = outputDir + "/service/" + className + "Service.java";
        String ftlName = "service.ftl";
        
        String Example = basePackageName + ".Example";
        String Page = basePackageName + ".Pageable";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("basePackageName", basePackageName);
        root.put("className", className);
        root.put("Example", Example);
        root.put("Page", Page);
        
        root.put("attrs", getAttrs(attrList));
        
        outFile(ftlName, filePath, root);
        System.out.println("生成Service完成");
    }
    public void generatorDao(String packageName,String className ,String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Dao");
        String filePath = outputDir+"/dao/"+className+"Dao.java";
        String ftlName = "dao.ftl";

        String Example = basePackageName + ".Example";
        String Page = basePackageName + ".Pageable";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("basePackageName", basePackageName);
        root.put("className", className);
        root.put("Example", Example);
        root.put("Page", Page);
        
        root.put("attrs", getAttrs(attrList));
        
        outFile(ftlName, filePath, root);
        System.out.println("生成Dao完成");
    }
    
    public void generatorMapper(String packageName,String className , List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Mapper");
        String filePath = outputDir+"/mapper/"+className+"Mapper.xml";
        String ftlName = "MybatisMapper.ftl";

        String namespace = packageName+".dao."+className+"Dao";
        String javaBean = packageName+".model.pojo."+className;
        String javaBeanExample = packageName+".model.pojo."+className+"Example";
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("namespace", namespace);
        root.put("javaBean", javaBean);
        root.put("javaBeanExample", javaBeanExample);
        root.put("tableName", tableName);

        root.put("attrs", getAttrs(attrList));

        outFile(ftlName, filePath, root);
        System.out.println("生成Mapper完成");
    }
    public void generatorJavaBeanExample(String packageName,String className, String basePackageName, List<JavaAttr> attrList,String outputDir)  {
        System.out.println("开始生成Example");
        String filePath = outputDir+"/modal/pojo/"+className+"Example.java";
        String ftlName = "javaBeanExample.ftl";
        
        String Example = basePackageName + ".Example";
        String Page = basePackageName + ".Pageable";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("className", className);
        root.put("Example", Example);
        root.put("Page", Page);

        root.put("attrs", getAttrs(attrList));

        outFile(ftlName, filePath, root);
        System.out.println("生成Example完成");
    }
    
    public void generatorJavaBean(String packageName,String className,List<JavaAttr> attrList,String outputDir) {
        System.out.println("开始生成JavaBean");
        String filePath = outputDir+"/modal/pojo/"+className+".java";
        String ftlName = "javaBean.ftl";
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("className", className);

        root.put("attrs", getAttrs(attrList));
        
        outFile(ftlName, filePath, root);
        System.out.println("生成JavaBean完成");
    }
    
    private List<Map<String , Object>> getAttrs(List<JavaAttr> attrList){
        List<Map<String , Object>> attrs = new ArrayList<>();
        for(JavaAttr attr : attrList){
            Map<String, Object> map = new HashMap<>();
            map.put("underLine", attr.getName());
            map.put("camel", StringUtil.underlineToCamel(attr.getName()));
            map.put("upperCamel", StringUtil.underlineToUpperCamel(attr.getName()));
            map.put("jdbcType", attr.getJdbcType());
            map.put("type", attr.getJavaType());
            attrs.add(map);
        }
        return attrs;
    }
    
    private void outFile (String ftlName, String filePath, Map<String , Object> root){
        OutputStream fos = null;
        Writer out = null;
        try {
            Template temp = cfg.getTemplate(ftlName);
            
            Path savePath = Paths.get(filePath);
            if(!Files.exists(savePath.getParent())){
                Files.createDirectories(savePath.getParent());
            }
            fos = new  FileOutputStream( savePath.toFile());
            out = new OutputStreamWriter(fos);
            temp.process(root, out);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.flush();  
                fos.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void generatorUpdate(String outputDir,String updateName,String beanName)  {
        System.out.println("开始生成Update");
        String filePath = outputDir+"/update.txt";
        String ftlName = "update.ftl";

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("tableName", tableName);
        root.put("updateName", updateName);
        root.put("beanName", beanName);
        List<String> list = new ArrayList<>();
        
        list.add("value");
        list.add("describemessage");
        root.put("attrs", list);

        outFile(ftlName, filePath, root);
        System.out.println("生成Update完成");
    }
    public void generatorInsert(String outputDir,String updateName,String beanName)  {
        System.out.println("开始生成Update");
        String filePath = outputDir+"/insert.txt";
        String ftlName = "insert.ftl";
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("tableName", tableName);
        root.put("updateName", updateName);
        root.put("beanName", beanName);
        List<String> list = new ArrayList<>();
        
        list.add("id");
        
        list.add("value");
        list.add("describemessage");
        root.put("attrs", list);
        
        outFile(ftlName, filePath, root);
        System.out.println("生成Update完成");
    }
    public static void main(String[] args) {
        GeneratorUtil g = new GeneratorUtil("administrativeunit");
        g.generatorUpdate("D:/123/","updateAdministrativeUnit","administrativeUnit");
//        g.generatorInsert("D:/123/","insertAdministrativeUnit","administrativeUnit");
    }
}
