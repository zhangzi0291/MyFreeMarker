<update id="${updateName}" parameterType="${beanName}">
		update ${tableName}
		<set>
			<#list attrs as attr>
			<if test="${attr} != null and ${attr} != ''">
				${attr}=${r'#{'}${attr}},
			</if>
			</#list> 
		</set>
		where id = ${r'#{'}id}
</update>