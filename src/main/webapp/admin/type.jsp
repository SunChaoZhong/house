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
    <script language="JavaScript" src="js/type.js"></script>
</head>
<body>
    <table id="dg"></table>
    <div id="ToolBar">
        <div style="height: 40px;">
            <a href="javascript:Add()" class="easyui-linkbutton"
               iconCls="icon-add" plain="true">添加</a> <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a> <a
                href="javascript:DeleteTypeMore()" class="easyui-linkbutton"
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
</body>
</html>
