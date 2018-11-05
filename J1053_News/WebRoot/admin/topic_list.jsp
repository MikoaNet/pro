<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>添加主题--管理后台</title>
<base href="<%=basePath%>"/>
<link href="CSS/admin.css" rel="stylesheet" type="text/css" />
<script>
	function clickdel(){
		return confirm("是否删除");
	}
	
	function go(currentPage){
		if(currentPage<1){
			return;
		}
		if(currentPage>${page.countPage}){
			return;
		}
		var path = getContextPath();
		location.href = path + "/admin/topic?method=list&currentPage="+currentPage;
	}
	
	function jump(pageSize){
		if(pageSize.value == ""){
			return;
		}
		var path = getContextPath();
		location.href = path +"/admin/topic?method=list&pageSize="+pageSize.value;
		
	}
	
	function goPage(currentPage){
		if(currentPage.value == ""){
			return;
		}
		var path = getContextPath();
		location.href = path+"/admin/topic?method=list&"+
				"currentPage="+currentPage.value;
		
	}
	
	function getContextPath() {
		    var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var result = pathName.substr(0,index+1);
		    return result;
	}

</script>
</head>
<body>
<%@include file="top.jsp" %>
<div id="main">
   <%@include file="left.jsp" %>
  <div id="opt_area">
    <ul class="classlist">
    	${error}
    	<c:forEach items="${topicList }" var="type">
    		<li> &#160;&#160;&#160;&#160; ${type.typeName}&#160;&#160;&#160;&#160; <a href='admin/topic?method=gotoModify&typeId=${type.typeId }&typeName=${type.typeName}'>修改</a> &#160;&#160;&#160;&#160; <a href='admin/topic?method=delete&typeId=${type.typeId }'  onclick='return clickdel()'>删除</a> </li>
    	</c:forEach>	
      		<p align="right"> 当前页数:[${page.currentPage }/${page.countPage }]&nbsp;&nbsp;
      		每页<input type="text" name="pageSize" style="width: 30px" value="${page.pageSize }" onchange="jump(this)"/>行
      		<a href="javascript:go(1)">&nbsp;&nbsp;首页</a>
      		<a href="javascript:go(${page.currentPage-1 })">&nbsp;&nbsp;上一页</a>
      		<a href="javascript:go(${page.currentPage+1 })">&nbsp;&nbsp;下一页</a> 
      		<a href="javascript:go(${page.countPage })">&nbsp;&nbsp;末页</a>
      		&nbsp;&nbsp;跳转到第<input type="text" name="currentPage" style="width: 30px" value="${page.currentPage }" onchange="goPage(this)"/>页</p>
      
    </ul>
  </div>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>
