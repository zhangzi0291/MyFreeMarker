package ${packageName}.dao;

import ${packageName}.entity.${className};
import ${packageName}.entity.${className}Example;

public interface ${className}Dao {
    
    int countByExample(${className}Example example);

    int deleteByExample(${className}Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(${className} record);

    int insertSelective(${className} record);

    List<${className}> selectByExample(${className}Example example);

    ${className} selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ${className} record, @Param("example") ${className}Example example);

    int updateByExample(@Param("record") ${className} record, @Param("example") ${className}Example example);

    int updateByPrimaryKeySelective(${className} record);

    int updateByPrimaryKey(${className} record);
    
}