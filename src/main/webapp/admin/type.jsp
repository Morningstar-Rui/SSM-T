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
    <script src="js/type.js">

    </script>
</head>
<body>
<table id="dg"></table>
<!--工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:ModifyBySelect()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:DeleteByFruitName()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<!--制作添加区域的对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" data-options="modal:true">
    <form action="" id="add" name="add" method="post" >
        区域名称:<input type="text" id="name" name="name"><br/>
    </form>
</div>
<!--修改对话框-->
<div id="upDialog" class="easyui-dialog" buttons="#upButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" data-options="modal:true">
    <form action="" id="update" name="update" method="post">
        区域名称:<input type="text" name="name"><br/>
        <input type="hidden" name="id">
    </form>
</div>
<!--对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<div id="upButtons">
    <a href="javascript:upDialog()" class="easyui-linkbutton" iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('upDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
