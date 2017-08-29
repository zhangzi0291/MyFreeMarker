<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
	<resultMap id="BaseResultMap" type="${javaBean}" >
	<#list attrs as attr> 
		<result column="${attr.underLine}" property="${attr.camel}" jdbcType="${attr.jdbcType}" />
	</#list> 
	</resultMap>
	<sql id="Example_Where_Clause" >
    <where >
      <foreach collection="oredCriteria" item="criteria" separator="or" >
        <if test="criteria.valid" >
          <trim prefix="(" suffix=")" prefixOverrides="and" >
            <foreach collection="criteria.criteria" item="criterion" >
              <choose >
                <when test="criterion.noValue" >
                  and ${r'${criterion.condition}'}
                </when>
                <when test="criterion.singleValue" >
                  and ${r'${criterion.condition}'} ${r'#{criterion.value}'}
                </when>
                <when test="criterion.betweenValue" >
                  and ${r'${criterion.condition}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
                </when>
                <when test="criterion.listValue" >
                  and ${r'${criterion.condition}'}
                  <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                    ${r'#{listItem}'}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Update_By_Example_Where_Clause" >
    <where >
      <foreach collection="example.oredCriteria" item="criteria" separator="or" >
        <if test="criteria.valid" >
          <trim prefix="(" suffix=")" prefixOverrides="and" >
            <foreach collection="criteria.criteria" item="criterion" >
              <choose >
                <when test="criterion.noValue" >
                  and ${r'${criterion.condition}'}
                </when>
                <when test="criterion.singleValue" >
                  and ${r'${criterion.condition}'} ${r'#{criterion.value}'}
                </when>
                <when test="criterion.betweenValue" >
                  and ${r'${criterion.condition}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
                </when>
                <when test="criterion.listValue" >
                  and ${r'${criterion.condition}'}
                  <foreach collection="criterion.value" item="listItem" open="(" close=")" separator="," >
                    ${r'#{listItem}'}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Base_Column_List" >
  <#list attrs as attr> 
    <#if attr_has_next>
    	${attr.underLine},
    <#else>
    	${attr.underLine}
    </#if>
  </#list> 
  </sql>
  
 <select id="selectByExample" resultMap="BaseResultMap" parameterType="${javaBeanExample}" >
    select
    <if test="distinct" >
      distinct
    </if>
    <include refid="Base_Column_List" />
    from ${tableName}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null" >
      order by ${r'${orderByClause}'}
    </if>
    <if test="page != null" >
      limit ${r'#{page.pageSize}'} offset ${r'#{page.begin}'}
    </if>
  </select>
  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Integer" >
    select 
    <include refid="Base_Column_List" />
    from ${tableName}
    where ${attrs[0].underLine} = ${r'#{'}${attrs[0].camel},jdbcType=${attrs[0].jdbcType}}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer" >
    delete from ${tableName}
    where ${attrs[0].underLine} = ${r'#{'}${attrs[0].camel},jdbcType=${attrs[0].jdbcType}}
  </delete>
  <delete id="deleteByExample" parameterType="${javaBeanExample}" >
    delete from ${tableName}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
  </delete>
  <insert id="insert" parameterType="${javaBean}" >
    insert into ${tableName} (
    <#list attrs as attr> 
    <#if attr_has_next>
    	${attr.underLine},
    <#else>
    	${attr.underLine}
    </#if>
  </#list> 
    )
    values (
    <#list attrs as attr> 
    <#if attr_has_next>
    	${r'#{'}${attr.camel},jdbcType=${attr.jdbcType}},
    <#else>
    	${attr.underLine}
    </#if>
 	</#list> 
    )
  </insert>
  <insert id="insertSelective" parameterType="${javaBean}" >
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides="," >
    <#list attrs as attr> 
      <if test="${attr.camel} != null" >
        ${attr.underLine},
      </if>
 	</#list> 
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
    <#list attrs as attr> 
   	  <if test="${attr.camel} != null" >
        ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}},
      </if>
 	</#list> 
    </trim>
  </insert>
  <select id="countByExample" parameterType="${javaBeanExample}" resultType="java.lang.Integer" >
    select count(*) from ${tableName}
    <if test="_parameter != null" >
      <include refid="Example_Where_Clause" />
    </if>
  </select>
  <update id="updateByExampleSelective" parameterType="map" >
    update ${tableName}
    <set >
       <#list attrs as attr> 
	   	  <if test="${attr.camel} != null" >
	        ${attr.underLine} = ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}},
	      </if>
	   </#list> 
    </set>
    <if test="_parameter != null" >
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByExample" parameterType="map" >
    update ${tableName}
    set 
    	<#list attrs as attr> 
    	<#if attr_has_next>
	        ${attr.underLine} = ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}},
	     <#else>
	        ${attr.underLine} = ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}}
	     </#if>
	   </#list> 
    <if test="_parameter != null" >
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByPrimaryKeySelective" parameterType="${javaBean}" >
    update ${tableName}
    <set >
    <#list attrs as attr> 
      <#if !attr?is_first>
   	  <if test="${attr.camel} != null" >
        ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}},
      </if>
      </#if>
 	</#list> 
    </set>
    where ${attrs[0].underLine} = ${r'#{'}${attrs[0].camel},jdbcType=${attrs[0].jdbcType}}
  </update>
  <update id="updateByPrimaryKey" parameterType="${javaBean}" >
    update ${tableName}
    set 
     <#list attrs as attr> 
      <#if !attr?is_first>
      <#if attr_has_next>
        ${attr.underLine} = ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}},
      <#else>
        ${attr.underLine} = ${r'#{'} ${attr.camel},jdbcType=${attr.jdbcType}}
      </#if>
      </#if>
 	</#list> 
    where ${attrs[0].underLine} = ${r'#{'}${attrs[0].camel},jdbcType=${attrs[0].jdbcType}}
  </update>
</mapper>