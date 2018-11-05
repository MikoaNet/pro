<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix ="c" %>
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
		if(document.MyForm.newsTitle.value==""){
			alert("标题不能为空");
			return false;
		}
		if(document.MyForm.newsAuthor.value==""){
			alert("作者不能为空");
			return false;
		}
		if(document.MyForm.newsContent.value==""){
			alert("内容不能为空");
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
    <h1 id="opt_type"> 添加新闻： </h1>
    <form name="MyForm" action="admin/news?method=add" method="post" onsubmit="return check()">
      <p>
        <label> 主题 </label>
        <select name="typeId">
        	<c:forEach items="${topicList }" var="type">
        		<option value='${type.typeId }'>${type.typeName }</option>   
        	</c:forEach>
        </select>
      </p>
      <p>
        <label> 标题 </label>
        <input name="newsTitle" type="text" class="opt_input" />
      </p>
      <p>
        <label> 作者 </label>
        <input name="newsAuthor" type="text" class="opt_input" />
      </p>
      <p>
        <label> 摘要 </label>
        <textarea name="newsSummary" cols="40" rows="3"></textarea>
      </p>
      <p>
        <label> 内容 </label>
        <textarea name="newsContent" cols="70" rows="10"></textarea>
      </p>
      <p>
        <label> 上传图片 </label>
        <input name="newsPic" type="file" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
      ${error }
    </form>
  </div>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>
