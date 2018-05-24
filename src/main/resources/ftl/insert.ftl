<insert id="${updateName}" parameterType="${beanName}">
		insert into ${tableName}
		 <trim prefix="(" suffix=")" suffixOverrides="," >
			<#list attrs as attr> 
			<if test="${attr} != null and ${attr} != ''">
				${attr}
			</if>
			</#list> 
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
			<#list attrs as attr> 
			<if test="${attr} != null and ${attr} != ''">
				${r'#{'}${attr}},
			</if>
			</#list> 
		<trim>
</insert>