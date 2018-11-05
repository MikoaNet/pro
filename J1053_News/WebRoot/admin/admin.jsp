<%@ page language="java" import="java.util.*,com.whos.jsp.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
</head>
<body>

<%@include file="top.jsp" %>
<div id="main">
  <%@include file="left.jsp" %>
  <div id="opt_area">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <script language="javascript">
	function clickdel(){
		return confirm("删除请点击确认");
	}
	
</script>
    <ul class="classlist">
    	<c:forEach items="${newsList }" var="news">
      		<li> <a href="admin/comment?method=read&newsId=${news.newsId }">${news.newsTitle }</a> <span> 作者：
        	${news.newsAuthor }                                             
        	&#160;&#160;&#160;&#160; 
        	<a href='admin/news?method=gotoModify&newsId=${news.newsId }'>修改</a> 
        	&#160;&#160;&#160;&#160; 
        	<a href='admin/news?method=del&newsId=${news.newsId }' onclick='return clickdel()'>删除</a> </span> </li>
    	</c:forEach>
      <li class='space'></li>
      <p align="right"> 当前页数:[1/3]&nbsp; <a href="#">下一页</a> <a href="#">末页</a> </p>
    </ul>
  </div>
</div>
<%@ include file="bottom.jsp" %>
</body>
</html>
