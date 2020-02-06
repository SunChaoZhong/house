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
    <script language="JavaScript">
        $(function () {
            //使用datagrid绑定数据展示
            $('#dg').datagrid({
                url:'getAllDistrict',
                fitColumns: true,
                pagination: true,
                pageList:[5,10,15,20],
                pageSize:5,
                toolbar:"#ToolBar",
                columns:[[
                    {field:'ck',checkbox:true},  //复选框列
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'区域',width:100},
                    {field:'cz',title:'操作',width:100,
                         formatter: function(value,row,index){
                             //value：字段的值。row：行数据。Index：行索引
                             //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                             return "<a href='javascript:DeleteSingle("+row.id+")'>删除</a> | <a href='javascript:ByDidStreet("+row.id+")'>显示街道</a>"
                         }
                     },
                ]]
            });
        })
        //打开添加窗口
        function Add(){
            $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
        }
        //关闭添加窗口
        function CloseDialog(obj){
            $("#"+obj).dialog("close");
        }
        //保存添加窗口里的添加
        function SaveDialog(){
            $('#SaveDialogForm').form('submit', {
                    url:"addAllDistrict",
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
            $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
            //获得行对象的数据加载到表单中显示
            //注意:SelectRow表示的就是当前行的Json:{"id":1000,"name":"丰台"}
            // 表单对象名称与json对象的键相同即可还原
            $("#upDialogForm").form('load',SelectRow);
        }
        //保存修改窗口里的修改
        function SaveDialog2(){
            $('#upDialogForm').form('submit', {
                url:"upDistrict",
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
                    $.post("deleteDistrict",{"id":id},function (data) {
                        if (data.result>0){
                            $("#dg").datagrid("reload");//刷新

                        } else{
                            $.messager.alert('提示框','系统维护...');
                        }
                    },"json")
                }
            });
        }
        function DeleteDistreetMore() {
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
            $.post("deleteMoreDistrict",{"ids":deleteValue},function(data){
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
        function ByDidStreet(obj) {
            $("#streetDialog").dialog("open").dialog('setTitle',"街道信息");
            $('#streetDg').datagrid({
                url:'getAllSteet?did='+obj,
                fitColumns: true,
                pagination: true,
                pageList:[2,5,10],
                pageSize:2,
                //toolbar:"#ToolBar",
                columns:[[
                    {field:'ck',checkbox:true},  //复选框列
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'街道',width:100},
                    {field:'cz',title:'操作',width:100,
                        formatter: function(value,row,index){
                            //value：字段的值。row：行数据。Index：行索引
                            //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                            return "<a href='javascript:DeleteSingle("+row.id+")'>删除</a>"
                        }
                    },
                ]]
            });
        }
    </script>
</head>
<body>
    <table id="dg"></table>
    <div id="ToolBar">
        <div style="height: 40px;">
            <a href="javascript:Add()" class="easyui-linkbutton"
               iconCls="icon-add" plain="true">添加</a> <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a> <a
                href="javascript:DeleteDistreetMore()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除选中行</a>
        </div>
    </div>
    <!--添加窗口-->
    <div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
        <form id="SaveDialogForm" method="post">
            <table>
                <tr>
                    <td>姓名:</td>
                    <td><input type="text" name="name" id="author" /></td>
                </tr>
            </table>
        </form>
    </div>
    <!--给添加对话框添加按钮-->
    <div id="AddDialogButtons">
        <a href="javascript:SaveDialog()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog('AddDialog')"
           class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
    <!--修改对话框-->
    <div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
        <form id="upDialogForm" method="post">
            <table>
                <tr>
                    <td>编号:</td>
                    <td><input type="text" readonly name="id"/></td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input type="text" name="name"/></td>
                </tr>
            </table>
        </form>
    </div>
    <!--给修改对话框添加按钮-->
    <div id="upDialogButtons">
        <a href="javascript:SaveDialog2()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog('upDialog')"
           class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>

    <!--区域下的街道对话框-->
    <div id="streetDialog" class="easyui-dialog"
         style="width: 600px; height: 300px; padding: 10px 20px;" closed="true">
        <table id="streetDg"></table>
    </div>
</body>
</html>
