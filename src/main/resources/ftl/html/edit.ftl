<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>${r'${title }'}</title>
<jsp:include page="/jsp/csstool.jsp" flush="true"/>
</head>
<body class="skin-blue fixed">
<jsp:include page="/jsp/header.jsp" flush="true"/>
<div class="wrapper">

<jsp:include page="/jsp/menu.jsp" flush="true"/>

<aside class="control-sidebar control-sidebar-dark"></aside>
<div class="control-sidebar-bg"></div>

<div class="content-wrapper">
	<section class="content">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-title">编辑${className}</div>
				</div>
				<div class="box-body">
					<form action="${r'${basePath }'}/${className?uncap_first}/edit.json" method="post" class="form-horizontal">
						<c:if test="${r'${not empty error}'}">
							<div class="red">${r'${error }'}</div>
						</c:if>
					    <#list attrs as attr> 
 						<div class="form-group">
							<label class="col-sm-2 control-label">${attr.upperCamel}</label>
	                        <div class="col-sm-10">
	                        	<input type="text" class="form-control" name="${attr.camel} value="${r'${'}info.${attr.camel} }"">
	                        </div>
	                    </div>
			  			</#list> 
						<div class="hr-line-dashed"></div>
						<div class="align-right">
							<input id="save" type="submit" class="btn btn-primary" value="保存" >
							<input id="back" type="button" class="btn btn-warning" value="返回">
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</div>

<jsp:include page="/jsp/footer.jsp" flush="true"/>
</body>
<jsp:include page="/jsp/jstool.jsp" flush="true"/>
<script type="text/javascript">
$(function(){
	initEvent();
})
function initEvent(){
	$("#back").on("click",function(){
		window.history.go(-1)
	})
	
}

</script>
</html>
