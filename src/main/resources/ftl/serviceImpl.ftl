package ${packageName}.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import ${basePackageName}.dao.BaseDao;
import ${basePackageName}.service.impl.BaseServiceImpl;
import ${basePackageName}.exception.DaoException;
import ${packageName}.dao.${className}Dao;
import ${packageName}.entity.${className};
import ${packageName}.entity.${className}Example;
import ${packageName}.service.${className}Service;

@Service("${className}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}, ${className}Example> implements ${className}Service{

    @Resource
    private ${className}Dao dao;

    @Override
    public BaseDao<${className}, ${className}Example> getDao() throws DaoException {
        return dao;
    }
    
    
}
