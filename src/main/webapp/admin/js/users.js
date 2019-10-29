//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        url:'getUserByCondition',
        toolbar:"#ToolBar",  //绑定工具栏
        pagination:true,
        pageSize:5,//只能选pageList:[3,5,10,20,50,100]中定义的大小
        pageList:[3,5,10,20,50,100],
        columns:[[
            {field:'ck',title:'全选',checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'用户名',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'cz',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:toDel("+row.id+")'>删除</a>"
                }

            }
        ]]
    });
});
function searchByCondition() {
    var name=$("#name").val();
    var tel=$("#telephone").val();
    $("#dg").datagrid("reload",{name:name,telephone:tel});
}

function toDel(id) {
    $.messager.confirm('提示信息', '确认删除？', function(r){
        if (r){
            $.post("delUsersById",{id:id},function (data) {
                if(data.result==1){
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert('提示信息','删除失败!','error');
                }
            },"json")
        }
    });
}
