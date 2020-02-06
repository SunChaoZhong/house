$(function () {
    //使用datagrid绑定数据展示
    $('#dg').datagrid({
        url:'getAllUsers',
        fitColumns: true,
        pagination: true,
        pageList:[5,10,15,20],
        pageSize:5,
        toolbar:"#ToolBar",
        columns:[[
            {field:'ck',checkbox:true},  //复选框列
            {field:'id',title:'编号',width:100},
            {field:'name',title:'姓名',width:100},
            //{field:'password',title:'密码',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'isadmin',title:'管理者状态',width:100},
            {field:'age',title:'年龄',width:100},
            {field:'cz',title:'操作',width:100,
                formatter: function(value,row,index){
                    //value：字段的值。row：行数据。Index：行索引
                    //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:DeleteSingle("+row.id+")'>删除</a>"
                }
            },
        ]]
    });
})
//打开添加窗口
function Add(){
    $("#AddDialog").dialog("open").dialog('setTitle',"添加用户");
}
//关闭添加窗口
function CloseDialog(obj){
    $("#"+obj).dialog("close");
}
//保存添加窗口里的添加
function SaveDialog(){
    $('#SaveDialogForm').form('submit', {
        url:"addAllUsers",
        success:function(data){
            //将json字符串转成json对象
            var obj=$.parseJSON(data)
            if (obj.result>0){
                $.messager.alert('提示框','恭喜添加成功!');
                $("#AddDialog").dialog("close");
            } else{
                $.messager.alert('提示框','系统维护...');
            }
        }
    });
}
//弹出修改对话框
function ModifyBySelect() {
    //判断有没有选择修改的记录
    //获取所有选中行，返回的数据，如果没有选中返回空
    var SelectRows = $("#dg").datagrid('getSelections');
    if (SelectRows.length!=1){
        $.messager.alert('提示框','您还没有选中行，或者选择了多行');
        return;
    }
    //说明选中了一行
    //还原数据
    var SelectRow = SelectRows[0];  //获取当前行的json:{"id":1000,"name":"丰台"}
    $("#upDialog").dialog("open").dialog('setTitle',"修改用户");
    //获得行对象的数据加载到表单中显示
    //注意:SelectRow表示的就是当前行的Json:{"id":1000,"name":"丰台"}
    // 表单对象名称与json对象的键相同即可还原
    $("#upDialogForm").form('load',SelectRow);
}
//保存修改窗口里的修改
function SaveDialog2(){
    $('#upDialogForm').form('submit', {
        url:"upUsers",
        success:function(data){
            //将json字符串转成json对象
            var obj=$.parseJSON(data)
            if (obj.result>0){
                $.messager.alert('提示框','恭喜修改成功!');
                $("#upDialog").dialog("close");
                $("#dg").datagrid("reload");//刷新

            } else{
                $.messager.alert('提示框','系统维护...');
            }
        }
    });
}
//删除单条
function DeleteSingle(id) {
    $.messager.confirm('确认提示框', '确定要删除吗？', function(r){
        if (r){
            // 实现删除  发送异步请求实现删除
            //$.post("服务器地址",给服务器传参,回调函数,"json");
            //传参的格式: {"参数名称":值,"参数名称":值}
            $.post("deleteUsers",{"id":id},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload");//刷新

                } else{
                    $.messager.alert('提示框','系统维护...');
                }
            },"json")
        }
    });
}
function DeleteTypeMore() {
    //判断有没有选中行
    var SelectRows = $("#dg").datagrid('getSelections');
    if (SelectRows.length==0){
        $.messager.alert('提示框','您还没有选中行');
        return;
    }

    //有选中行 删除选中行 获取选中项的值   拼成:1,2,3
    var deleteValue="";
    for (var i = 0; i <SelectRows.length ; i++) {
        deleteValue=deleteValue+SelectRows[i].id+",";
    }
    //截取字符串去掉最后一个，
    deleteValue=deleteValue.substring(0,deleteValue.length-1);
    //发送异步请求到服务器
    $.post("deleteMoreUsers",{"ids":deleteValue},function(data){
        if(data.result>0){
            $("#dg").datagrid('reload'); //刷新
            //alert("添加成功");
            $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
        }
        else
        {
            $.messager.alert('提示框','系统正在维护!');
        }
    },"json");
}
//实现搜索
function searchUser() {
//datagrid控制重新加载的方法
    //$("#dg").datagrid("load",跟查询条件的参数);
    //取电话,开始年龄，结束年龄
    var $telephone=$("#tel").val();
    var $startAge=$("#startAge").val();
    var $endAge=$("#endAge").val();
    $("#dg").datagrid("load",{"telephone":$telephone,"startAge":$startAge,"endAge":$endAge});
}