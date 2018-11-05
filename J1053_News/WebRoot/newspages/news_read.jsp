<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%= basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻中国</title>
<link href="CSS/read.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	  		function check(){
	  			var cauthor = document.getElementById("cauthor");
	  			var content = document.getElementById("ccontent");
	  			if(cauthor.value == ""){
	  				alert("用户名不能为空！！");
	  				return false;
	  			}else if(content.value == ""){
	  				alert("评论内容不能为空！！");
	  				return false;
	  			}
	  			return true;
	  		}
	  		
	  		function isDel(){
	  			return confirm("是否删除");
	  		}
	  		
	  		function returnMenu(){
	  			history.go(-1);
	  		}
	  		
	  		function test(){
	  			alert("111");
	  		}
	  	</script>
</head>
<body>
<div id="header">
  <div id="top_login">
   	欢迎你，${sessionScope.user.username }
	<a href="logout" >退出登录</a>
	<a href="admin/news?method=gotoAdmin" >管理</a>
  </div>
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
      <ul class="classlist">
        <table width="80%" align="center">
          <tr width="100%">
            <td colspan="2" align="center">${news.newsTitle }</td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
          <tr>
            <td align="center"><fmt:formatDate value="${news.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
            <td align="left">${news.newsAuthor } </td>
          </tr>
          <tr>
            <td colspan="2" align="center"></td>
          </tr>
          <tr>
            <td colspan="2"> 
            ${news.newsContent } 
            <c:if test="${not empty news.newsPic.trim() }">
           		<img src="show?src=${news.newsPic}" alt="图片" />
           	</c:if>
            </td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
        </table>
      </ul>
     <!--  <ul class="classlist">
        <table width="80%" align="center">
          <td colspan="6"> 暂无评论！ </td>
          <tr>
            <td colspan="6"><hr />
            </td>
          </tr>
        </table>
      </ul> -->
      <ul class="classlist">
        <form action="admin/comment?method=save&newsId=${news.newsId }" method="post" onsubmit="return check()">
          <table width="80%" align="center">
          <c:if test="${empty commentList}">
          	<tr>
              <td> 暂无评论  </td>
            </tr>
          </c:if>
          <c:if test="${not empty commentList}">
            <tr>
              <td> 评 论: </td>
            </tr>
            <c:forEach items="${commentList }" var="comment">
	            <tr>
	              <td> ${comment.username }(${comment.ip}):${comment.content }
	              &#160;&#160;&#160;&#160;
	              <a href="admin/comment?method=del&commentId=${comment.commentId }&newsId=${news.newsId}" onclick="return isDel()"/>删除评论</a>
	              </td>
	            </tr>
            </c:forEach>
          </c:if>
            <tr>
              <td> 用户名： </td>
              <td><input id="cauthor" name="username" value="${user.username}"/>
                IP：
                <input name="ip" value="${ip }"
											readonly="readonly"/>
              </td>
            </tr>
            <tr>
              <td colspan="2"><textarea id="ccontent" name="content" cols="70" rows="10"></textarea>
              </td>
            </tr>
            <td>
            <input value="返回菜单" type="button" onclick="returnMenu()"/>
            <input name="submit" value="发  表" type="submit"/>${error }
              </td>
          </table>
        </form>
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
  <p class="copyright"> Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
</body>
</html>
