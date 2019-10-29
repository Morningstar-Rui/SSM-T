//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        url:'getAllHouseIsPass',
        toolbar:"#ToolBar",  //绑定工具栏
        pagination:true,
        pageSize:5,//只能选pageList:[3,5,10,20,50,100]中定义的大小
        pageList:[5,10,20,50,100],
        columns:[[
            {field:'ck',title:'全选',checkbox:true},
            {field:'id',title:'编号',width:100},
            {field:'title',title:'标题',width:100},
            {field:'contact',title:'联系方式',width:100},
            {field:'floorage',title:'面积',width:100},
            {field:'price',title:'价格',width:100},
            {field:'dname',title:'区域',width:100,formatter: function(value,row,index){
                    return row.dname+row.sname;
                }},
            {field:'pubdate',title:'发布日期',width:100,formatter: function(value,row,index){
                var date=new Date(value);
                    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                }
            },
            {field:'tname',title:'类型',width:100},
            {field:'cz',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:toPass("+row.id+")'>取消审核</a>"
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

function toPass(id) {
     $.post("houseNotPass",{id:id},function (data) {
            if(data.result==1){
                    $("#dg").datagrid('reload'); //刷新
            }else{
                    $.messager.alert('提示信息','出错啦','error');
            }
     },"json")
}
