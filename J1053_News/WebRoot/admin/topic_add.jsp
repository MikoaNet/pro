<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>添加主题--管理后台</title>
<link href="CSS/admin.css" rel="stylesheet" type="text/css" />

<script>
	function check(){
		var typeName = document.MyForm.typeName.value;
		if(typeName==""){
			alert("不能为空");
			return false; 
		}
		return true;
	}
</script>

</head>
<body>
<%@include file="top.jsp" %>
<div id="main">
  <%@include file="left.jsp" %>
  <div id="opt_area">
    <h1 id="opt_type"> 添加主题： </h1>
    <form name="MyForm" action="admin/topic?method=add" method="post" onsubmit="return check()">
      <p>
        <label> 主题名称 </label>
        <input name="typeName" type="text" class="opt_input" />
        ${error}
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>
