<%@ page language="java" import="java.util.*,com.whos.jsp.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserInfo user = (UserInfo)session.getAttribute("user");

String username = "";
String password = "";

Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(Cookie cookie:cookies){
		if(cookie.getName().equals("username")){
			username = cookie.getValue();
		}else if(cookie.getName().equals("password")){
			password = cookie.getValue();
		}
	}
}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" >

function check(){
	
	if(document.myForm.username.value==""){
		alert("用户名不能为空");
		return false;
	}
	if(document.myForm.password.value==""){
		alert("密码不能为空");
		return false;
	}
	if(document.myForm.code.value==""){
		alert("验证码不能为空");
		return false;
	}
	
	var result = confirm("是否保存Cookie");
	if(result){
		document.myForm.action = "login?saveCookie=1";
	}
	return true;
}

function modifyImg(img){
	var date = new Date();
	img.src = "image?a="+date;
}

function go(currentPage){
	if(currentPage<1){
		return;
	}
	if(currentPage>${page.countPage}){
		return;
	}
	var path = getContextPath();
	location.href = path + "/index?typeId=${typeId}&currentPage="+currentPage;
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
<div id="header">
  <div id="top_login">
  
  <form name="myForm" action="login" method="post" onSubmit="return check()">
  			<!-- <input type="hidden" name="saveCookie" value="0"/> -->
  	<c:choose>
  		<c:when test="${sessionScope.user eq null}">
  			<label> 登录名 </label>
  		    <input type="text" id="uname" name="username" value="<%=username==null?"":username%>" class="login_input" />
  		    <label> 密&#160;&#160;码 </label>
  		    <input type="password" id="upwd" name="password" value="<%=password==null?"":password%>" class="login_input" />
  		    <label> 验证码 </label>
  		    <input type="text" id="code" name="code" style="width: 40px"/>
  		    <!-- <a href="javascript:void(0)"><img src="image" align="middle" onclick="modifyImg(this)"/></a> -->
  		    <img src="image" style = "cursor:pointer;" align="middle"  onclick ="modifyImg(this)" />
  		    <input type="submit" class="login_sub" value="登录"/>
  		</c:when>
  		<c:otherwise>
	  		欢迎你，${sessionScope.user.username }
	  		<a href="logout" >退出登录</a>
	  		<a href="admin/news?method=gotoAdmin" >管理</a>
	  		<a href="refresh?src=index" >刷新</a>
	  	</c:otherwise>
    </c:choose>
    <c:if test="${ not empty requestScope.error }">
    	${requestScope.error }
    </c:if>
  </form>
  
    <label id="error"> </label>
    <img src="Images/friend_logo.gif" alt="Google" id="friend_logo" /> </div>
  <div id="nav">
    <div id="logo"> <img src="Images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="Images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="Images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>
      	<c:forEach items="${inlandList }" var="il">
      		<li> <a href='admin/comment?method=read&newsId=${il.newsId }'><b> ${il.newsTitle } </b></a> </li>
      	</c:forEach>
      </ul>
    </div>
    <h1> <img src="Images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul>
        <c:forEach items="${internationalList }" var="in">
      		<li> <a href='admin/comment?method=read&newsId=${in.newsId }'><b> ${in.newsTitle } </b></a> </li>
      	</c:forEach>
      </ul>
    </div>
    <h1> <img src="Images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>
        <c:forEach items="${enList }" var="en">
      		<li> <a href='admin/comment?method=read&newsId=${en.newsId }'><b> ${en.newsTitle } </b></a> </li>
      	</c:forEach>
      </ul>
    </div>
  </div>
  <div class="main">
    <div class="class_type"> <img src="Images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li id='class_month'> 
        	<a href='index?typeId=0'><b> 全部 </b></a>
        	<c:forEach items="${topicList }" var="t">
        		<a href='index?typeId=${t.typeId }'><b> ${t.typeName } </b></a>
        	</c:forEach>
        </li>
  
      </ul>
      <ul class="classlist">
        <c:forEach items="${newsList }" var="n" varStatus="status">
        	<li>
        		<a href='admin/comment?method=read&newsId=${n.newsId }'> ${n.newsTitle } </a>
        		<span> <fmt:formatDate value="${n.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
        	</li>
        	<c:if test="${status.count%5==0 }">
        	 <li class='space'></li>
        	</c:if>
        	
        </c:forEach>
       
        
        <p align="right"> 当前页数:[${page.currentPage }/${page.countPage }]&nbsp; 
			<a href="javascript:go(1)">&nbsp;&nbsp;首页</a>
      		<a href="javascript:go(${page.currentPage-1 })">&nbsp;&nbsp;上一页</a>
      		<a href="javascript:go(${page.currentPage+1 })">&nbsp;&nbsp;下一页</a> 
      		<a href="javascript:go(${page.countPage })">&nbsp;&nbsp;末页</a>
      	</p>
      </ul>
    </div>
    <div class="picnews">
      <ul>
      	<c:forEach items="${picList }" var="p">
        <li> 
        	<a href="admin/comment?method=read&newsId=${p.newsId }">
        	<img src="show?src=${p.newsPic}" width="249" alt="" /> 
        	</a>
        	<a href="admin/comment?method=read&newsId=${p.newsId }">${p.newsTitle }</a> 
        </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<div id="friend">
  <h1 class="friend_t"> <img src="Images/friend_ico.gif" alt="合作伙伴" /> </h1>
  <div class="friend_list">
    <ul>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
    </ul>
  </div>
</div>
<div id="footer">
  <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
    文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a> </p>
  <p class="copyright"> Copyright &copy; News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
</body>
</html>
