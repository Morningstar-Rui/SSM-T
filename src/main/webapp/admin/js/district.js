//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        url:'getDistrictByPage',
        toolbar:"#ToolBar",  //绑定工具栏
        pagination:true,
        pageSize:10,//只能选pageList:[3,5,10,20,50,100]中定义的大小
        pageList:[3,5,10,20,50,100],
        columns:[[
            {field:'ck',title:'全选',checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'cz',title:'操作',width:200,
                formatter: function(value,row,index){
                    return "<a href='javascript:toDel("+row.id+")'>删除</a> | " +
                        "<a href='javascript:toUpdate("+row.id+")'>修改</a> | " +
                        "<a href='javascript:toDetail("+row.id+")'>街道信息</a>"
                }

            }
        ]]
    });

});
//添加区域
function Add() {
    $("#AddDialog").dialog("setTitle","添加区域");
    $("#AddDialog").dialog("open");//打开弹窗
}
//关闭弹窗
function CloseDialog(id) {
    $("#"+id).dialog("close");//关闭弹窗
}
function SaveDialog() {
    //第一种：使用jquery的post方法发送异步请求实现添加
    /* $.post("addDistrict",{"name":$("#name").val()},function (data) {
         if (data.result==1){
             $("#dg").datagrid('reload');//刷新
             $("#AddDialog").dialog('close');//关闭
         }else {
             alert("添加失败！")
         }
     },"json");*/
    //第二种 ：使用easyui的表单提示插件
    $('#add').form('submit',{
        url:"addDistrict",
        success:function(data){
            data= $.parseJSON(data);
            if (data.result==1){
                $("#dg").datagrid('reload');//刷新
                $("#AddDialog").dialog('close');//关闭
            }else {
                alert("添加失败！")
            }
        }
    });
}
//修改区域
function ModifyBySelect() {
    //判断用户是否选择行
    //获取dagagrid选中的行
    var SelectRows = $("#dg").datagrid('getSelections');
    if (SelectRows.length==1){
        //获取当前行的编号--》查询当前记录-->还原表单
        //1.获取当前的编号
        var bh=SelectRows[0].id;
        //2.发送异步请求获取服务器数据
        $.post("getDistrictById",{id:bh},function (data) {
            //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
            //获得行对象的数据加载到表单中显示
            //$("#upForm").form('load',{"名秒":值,"名称":值});
            $("#update").form('load',data);
        },"json");
        $("#upDialog").dialog("setTitle","修改区域");
        $("#upDialog").dialog("open");//打开弹窗
    }else {
        //消息框 alert
        $.messager.alert('提示信息','你没有选中行或者选中多行','warning');
    }
}
function upDialog() {
    $('#update').form('submit',{
        url:"updateDistrict",
        success:function(data){
            data= $.parseJSON(data);
            if (data.result==1){
                $("#dg").datagrid('reload');//刷新
                $("#upDialog").dialog('close');//关闭
            }else {
                alert("修改失败！")
            }
        }
    });
}

//删除操作
function toDel(id) {
    $.messager.confirm('提示信息', '确认删除？', function(r){
        if (r){
            $.post("delDistrict",{id:id},function (data) {
                if(data.result==1){
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert('提示信息','删除失败!','error');
                }
            },"json")
        }
    });
}
//修改操作
function toUpdate(id) {
    $.post("getDistrictById",{id:id},function (data) {
        //3.还原加载表单数据  //data的格式:{"id":1002,"name":"西城东区"}
        //获得行对象的数据加载到表单中显示
        //$("#upForm").form('load',{"名秒":值,"名称":值});
        $("#update").form('load',data);
    },"json");
    $("#upDialog").dialog("setTitle","修改区域");
    $("#upDialog").dialog("open");//打开弹窗
}

//批量删除区域
function DeleteByFruitName() {
    //获取选中行的数据,返回的是数组
    //获取选中行的数据
    var selectRows = $("#dg").datagrid("getSelections");

    //如果没有选中行的话，提示信息
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选择要删除的记录！", 'warning');
        return;
    }

    //如果选中行了，则要进行判断
    $.messager.confirm("确认消息", "确定要删除所选记录吗？", function (isDelete) {

        //如果为真的话
        if (isDelete) {
            //定义变量值
            /*var Ids = "";
            //拼接字符串，这里也可以使用数组，作用一样
            for (var i = 0; i < selectRows.length; i++) {
                Ids += selectRows[i].id + ",";
            }
            //循环切割
            Ids = strIds.substr(0, strIds.length - 1);*/
            var ids=[];
            for (var i = 0; i < selectRows.length; i++) {
                //获取自定义table 的中的checkbox值
                var id=selectRows[i].id;   //id这个是你要在列表中取的单个id
                ids.push(id); //然后把单个id循环放到ids的数组中
            }

            $.post('delDistrictByIds',{"ids":ids}, function (data) {
                if (data.result > 0) {
                    $.messager.alert('提示', '删除成功！');
                    $("#dg").datagrid("reload"); //删除成功后 刷新页面
                } else {
                    $.messager.alert('提示信息', '删除失败，请联系管理员！', 'warning');
                }
            }, "JSON");
        }
    });
}
//街道关联处理
function toDetail(id) {
    var subtitle='街道信息处理';
    var url="${pageContext.request.contextPath}/admin/street.jsp";
    var content = '<iframe scrolling="no" frameborder="0"'+' src="'+url+'" style="width:100%;height:100%;"></iframe>';
    /*if(!$('#tabs').tabs('exists','goodInfo')){*/
        $('#tabs').tabs('add',{
            title:'goodInfo',
            content:content,
            closable:true});
       /* });
    }else{
        $('#tabs').tabs('select','goodInfo');
    }*/
    /*function addTab(subtitle,url,icon){
        if(!$('#tabs').tabs('exists',subtitle)){
            $('#tabs').tabs('add',{
                title:subtitle,
                content:createFrame(url),
                closable:true,
                icon:icon
            });
        }else{
            $('#tabs').tabs('select',subtitle);
            $('#mm-tabupdate').click();
        }
    }*/
    /*$('#tabs').tabs('add',{
        title: '街道信息处理',
        content:createFrame(url),
        selected: false

    });*/


}