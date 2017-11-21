package ${packageName}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import ${basePackageName}.Page;
import ${basePackageName}.exception.DaoException;
import ${packageName}.model.pojo.${className};
import ${packageName}.model.pojo.${className}Example;
import ${packageName}.service.${className}Service;

@Controller
@RequestMapping("${className?uncap_first}")
public class ${className}Controller {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ${className}Service ${className?uncap_first}Service;
    
    @RequestMapping("list.html")
    public String listHtml(){
        return "${className?uncap_first}/${className?uncap_first}List";
    }
    
    @RequestMapping("list.json")
    @ResponseBody
    public Map<String, Object> listJson(${className} ${className?uncap_first}, Page page){
        Map<String, Object> result = new HashMap<>();
        ${className}Example example = new ${className}Example();
        ${className}Example.Criteria criteria = example.createCriteria();
        //设置查询条件 。。。
        example.setPage(page);
        try {
            List< ${className}> list = ${className?uncap_first}Service.selectByExample(example);
            Integer count = ${className?uncap_first}Service.countByExample(example);
            result.put("rows", list);
            result.put("total", count);
            return result;
        } catch (DaoException e) {
            logger.error("Exception ", e);
        }
        return null;
    }
    
    @RequestMapping("add.html")
    public String addHtml(){
        return "${className?uncap_first}/${className?uncap_first}Add";
    }
    
    @RequestMapping("add.json")
    @Transactional
    public String addJson(HttpServletRequest request,Map<String, Object> map, ${className} ${className?uncap_first} ){
        try {
            Integer num = ${className?uncap_first}Service.insertSelective(${className?uncap_first});
            if(num==0){
                map.put("error", "保存失败");
                return "${className?uncap_first}/${className?uncap_first}Add";
            }
        } catch (DaoException e) {
            map.put("error", "保存失败，"+e.getMessage());
            logger.error("Exception ", e);
            return "${className?uncap_first}/${className?uncap_first}Add";
        }
        return "redirect:list.html";
    }
    
    @RequestMapping("edit.html")
    public String editHtml(Map<String, Object> map, String id){
        try {
            ${className} ${className?uncap_first} = ${className?uncap_first}Service.selectByPrimaryKey(id);
            map.put("info", ${className?uncap_first});
        } catch (DaoException e) {
            logger.error("Exception ", e);
        }
        return "${className?uncap_first}/${className?uncap_first}Edit";
    }
   
    @RequestMapping("edit.json")
    public String editJson(Map<String, Object> map, ${className} ${className?uncap_first}){
        try {
            Integer num = ${className?uncap_first}Service.updateByPrimaryKeySelective(${className?uncap_first});
            if(num==0){
                map.put("error", "保存失败");
                return "${className?uncap_first}/${className?uncap_first}Edit";
            }
        } catch (DaoException e) {
            map.put("error", "保存失败，"+e.getMessage());
            logger.error("Exception ", e);
            return "${className?uncap_first}/${className?uncap_first}Edit";
        }
        return "redirect:list.html";
    }
    
    @RequestMapping("del.json")
    @ResponseBody
    public String delJson(Map<String, Object> map, @RequestParam("ids[]") List<String >ids ){
        Integer num = 0;
        try {
            for(int i=0;i<ids.size();i++){
                num+=${className?uncap_first}Service.deleteByPrimaryKey(ids.get(i));
            }
        } catch (DaoException e) {
            logger.error("Exception ", e);
        }
        return num.toString();
    }
}