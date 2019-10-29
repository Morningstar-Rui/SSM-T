<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script src="js/pass.js">

    </script>
</head>
<body>
<table id="dg"></table>
<!--工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        用户名：<input type="text" name="name" id="name" >
        电话：<input type="text" name="telephone" id="telephone" >
        <a href="javascript:searchByCondition()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
    </div>
</div>
</body>
</html>
