package ${packageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import ${packageName}.service.${className}Service;
import ${packageName}.dao.${className}Dao;

@Service("${className}Service")
public class ${className}ServiceImpl implements ${className}Service{
    
    @Resource
    private ${className}Dao dao;
    
}
