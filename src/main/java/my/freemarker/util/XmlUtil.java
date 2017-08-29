package my.freemarker.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {

    public static void main(String[] args) throws Exception {
        
    }
    
    public static Map<String, Object> parseXml(String filePath) throws Exception{
           // 定义工厂API 使应用程序能够从XML文档获取生成DOM对象树的解析器
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           // 获取此类的实例之后，将可以从各种输入源解析XML
           DocumentBuilder builder = factory.newDocumentBuilder();
           // builder.parse(this.getClass().getResourceAsStream("/" + fileName));
           // Document接口表示整个HTML或XML文档，从概念上讲，它是文档树的根，并提供对文档数据的基本访问
           Document document = builder.parse(XmlUtil.class.getResourceAsStream("/"+filePath));
           // 获取根节点
           Element root = document.getDocumentElement();
           NodeList nodeList = root.getElementsByTagName("tables");
           //tables
           List<Map<String, String>> tablelist = getChildNodeAttr(nodeList );
           //jdbcConnection
           Map<String, String>  jdbcConnectionMap =  getNodeAttr(root.getElementsByTagName("jdbcConnection").item(0));
           //base
           Map<String, String>  baseMap =  getNodeAttr(root.getElementsByTagName("base").item(0));
           
           Map<String, Object> map = new HashMap<>();
           map.put("tables", tablelist);
           map.put("jdbcConnection", jdbcConnectionMap);
           map.put("base", baseMap);
           return map;
       }
    
    public static Map<String, String> getNodeAttr(Node node){
        Map<String, String> map = new HashMap<>();
        if(node.getNodeType()!=1){
            return null;
        }
        NamedNodeMap attributes = node.getAttributes();
        for(int j=0;j<attributes.getLength();j++){
            Node attr = attributes.item(j);
            map.put(attr.getNodeName(), attr.getNodeValue());
        }
        return map;
    }
    public static List<Map<String, String>> getChildNodeAttr(NodeList nodeList){
        List<Map<String, String>> list = new ArrayList<>();
        for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            NodeList childNodeList = node.getChildNodes();
            for(int j=0;j<childNodeList.getLength();j++){
                Map<String, String> result = getNodeAttr(childNodeList.item(j) );
                if(result!=null){
                    list.add(result);
                }
            }
        }
        return list;
    }
}
