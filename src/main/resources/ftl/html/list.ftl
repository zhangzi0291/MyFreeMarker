<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<div class="box-title">${className}列表</div>
				</div>
				<div class="box-body">
					<table id = 'table'  cellspacing="0"></table>
				</div>
			</div>
		</div>
	</section>
</div>
<div id="toolbar">
<form class="form-inline" role="form">
	<!--	搜索	-->
	<div class="form-group">
		<label for="name" >时间：</label><input id="date"  />
	</div>

	
	<button id="search" class="btn btn-primary" type="button">搜索</button>
</form>
<div class="btn-toolbar" role="toolbar">
		<div class="btn-group">
			<button id="add" class="btn btn-primary">新增</button>
			<button id="edit" class="btn btn-warning">修改</button>
			<button id="del" class="btn btn-danger">删除</button>
		</div>
</div>
</div>
</body>
<jsp:include page="/jsp/jstool.jsp" flush="true"/>
<script type="text/javascript">
var $table
$(function(){
	initTable();
	initEvent();
})
function initEvent(){
	$("#add").on("click",function(){
		jumpPage({
			url:basePath+"/${className?uncap_first}/add.html",
		})
	})
	$("#edit").on("click",function(){
		var row = $table.bootstrapTable('getSelections');
		if(row.length != 1){
			layer.msg("请选择一条记录")
		}
		if(row[0].${attrs[0].camel}==undefined){
			layer.msg("跳转错误")
			return;
		}
		jumpPage({
			method:"POST",	
			url:basePath+"/${className?uncap_first}/edit.html",
			${attrs[0].camel}:row[0].${attrs[0].camel}
		})
	})
	$("#del").on("click",function(){
		var rows = $table.bootstrapTable('getSelections');
		if(rows.length == 0){
			layer.msg("请至少选择一条记录")
		}else{
			layer.confirm('确定要删除？', {
				  btn: ['是','否'] //按钮
			},function(index){
				var ids =[]; 
				for (var i in rows){
					ids.push(rows[i].${attrs[0].camel})
				}
				$.ajax({
					type:"POST",
					url:basePath+"/${className?uncap_first}/del.json ",
					data:{
						ids:ids
					},
					success:function(data){
						console.log(data)
						if(data>0){
							layer.msg("删除成功")
						}else{
							layer.msg("删除失败")
						}
						$table.bootstrapTable('refresh');
						layer.close(index)
					},
					error:function(){
						layer.msg("服务异常，删除失败")
						layer.close(index)
					}
				})
			},
			function(){})
		}
	})
	$("body").on("click","i.fa-trash",function(){
		$("#del").click();
	})
	$("body").on("click","i.fa-edit",function(){
		$("#edit").click();
	})
	$("#search").on("click",function(){
			$table.bootstrapTable('refresh');
	})
	$table.on('load-error.bs.table', function (e, status) {
        console.log(e)
        console.log(status)
        layer.msg("加载错误")
    })
}
function initTable(){
	tableOption = getOption({
		url:basePath + "/${className?uncap_first}/list.json",
		queryParams:function (params) {
			//设置查询条件
			return params;
		},
		columns:[	
			   { "title" : "check",   checkbox:true, },
			  <#list attrs as attr> 
				   { "title" : "${attr.camel}",   "field": "${attr.camel}", },
			  </#list> 

		  	],
	})
	$table=$("#table").bootstrapTable(tableOption);
}

</script>
</html>
