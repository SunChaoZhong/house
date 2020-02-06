﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--格式化处理-->
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" src="/admin/js/jquery-1.8.3.js"></script>
<script language="JavaScript">
   $(function () {
       //网页加载完获取第一个选中项
       show($("#sel1").val())
       $("#sel1").change(function () {
           //取出区域id 查找对应的街道
           var did=$(this).val();
           //发送异步请求获取街道
           show(did);
       })
   })
  function show(did) {
      $.post("getStreetByDid",{"did":did},function (data) {
          $("#sel2>option").remove();
          for (var i=0;i<data.length;i++){
              //创建一个dom节点
              var option=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
              $("#sel2").append(option);//追加节点
          }
          $("#sel2").val(${house.streetId})
      },"json");
  }
</script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DD class=past>修改发布房屋信息</DD>
</DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action 
action="upHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><input type="hidden" value="${house.id}" name="id">
      <INPUT id=add_action_title class=text value="${house.title}" type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId>
      <c:forEach items="${types}" var="t">
        <OPTION <c:if test="${t.id==house.typeId}">selected="selected"</c:if> value=${t.id}>${t.name}</OPTION>
      </c:forEach>

    </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage value="${house.floorage}" class=text type=text
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price  value="${house.price}" class=text type=text name=price> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>" name=pubdate></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT id="sel1" class=text name=district_id>
      <c:forEach items="${districts}" var="d">
        <OPTION <c:if test="${house.districtId==d.id}">selected="selected"</c:if> value=${d.id}>${d.name}</OPTION>
      </c:forEach>
    </SELECT>

      街：<SELECT id="sel2" class=text name=streetId>
        <OPTION selected value=1000>请选择街道</OPTION>
      </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>图　　片：</TD>
    <TD><img src="http://localhost:82/${house.path}?a=<%=Math.random()%>" width="50px" height="50px">
      <input type="hidden" name="oldPic" value="${house.path}" >
      <INPUT id=tupian class=text type="file" name="pfile"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons>
  <INPUT value=立即更新 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
