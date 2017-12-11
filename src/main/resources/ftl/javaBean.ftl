package ${packageName}.model.pojo;

<#list attrs as attr> 
	<#if attr.type == "Date" >
import java.util.Date;
	</#if>
	<#if attr.type?index_of("List") !=-1  >
import java.util.List;
	</#if>
	<#if attr.type?index_of("Map") !=-1 >
import java.util.Map;
	</#if>
</#list>

public class ${className} {
    <#list attrs as attr> 
    private ${attr.type!} ${attr.camel};
    </#list>

    <#list attrs as attr>
    public ${className} set${attr.camel?cap_first}(${attr.type} ${attr.camel}){
        this.${attr.camel} = ${attr.camel};
        return this;
    }
    public ${attr.type} get${attr.camel?cap_first}(){
        return this.${attr.camel};
    }

    </#list>
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("${className}[");
    	 <#list attrs as attr>
        sb.append("${attr.camel}=" + ${attr.camel} + ", ");
        </#list>
    	sb.append("]");
        return sb.toString();
    }
}