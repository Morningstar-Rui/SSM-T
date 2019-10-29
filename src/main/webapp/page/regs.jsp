<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
     $(function(){  //jquery加载事件
         //focusout()  失去焦点时触发事件
         $("#txtName").focusout(function () {
             //发送异步请求
             $.post("checkName",{"name":$("#txtName").val()},function(data){
                 if(data.result==0){
                     $("#notice").text("用户名可用").css("color","green");
                 }else {
                     $("#notice").text("用户名已存在").css("color","red");
                 }
             },"json");
         });


     });
    function check() {
        var name=$("#txtName").val();
        var password=$("#password").val();
        var repassword=$("#repassword").val();
        var telephone=$("#telephone").val();
        var age=$("#age").val();
        var reg_pwd=/^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9~!@&%#_]{8,16}$/;  //必须包含一个大写，一个小写字母，且长度为8到16位
        var reg_tel= /^1\d{10}$/;
        var reg_age= /^(1[6-9])|([2-9][0-9])$/;
        if (!name){
            $("#notice").text("用户名不能为空").css("color","red");
            return false;
        }
        if (!password){
            $("#notice1").text("密码不能为空").css("color","red");
            return false;
        }
        if (!telephone){
            $("#notice3").text("电话不能为空").css("color","red");
            return false;
        }
        if (!age){
            $("#notice4").text("年龄不能为空").css("color","red");
            return false;
        }
        if (password!=repassword){
            $("#notice2").text("两次密码不一样").css("color","red");
            return false;
        }
        if (reg_tel.test(telephone)){
            $("#notice3").text("请输入正确的电话格式").css("color","red");
            return false;
        }
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="regUser"  method="post" name="form1" onsubmit="return check()">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text id="txtName" type=text name=name>
      <span id="notice"></span>
      <%--<input id="but1" type="button" value="检查用户是否存在">--%>
    </TD>
  </TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"><span id="notice1"></span></TD>

  </TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="repassword"> <span id="notice2"></span></TD>

  </TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone id="telephone"> <span id="notice3"></span></TD>

  </TR>
  <TR>
    <TD class=field>年　　龄：</TD>
    <TD><INPUT class=text type=text name=age id="age"><span id="notice4"></span> </TD>

  </TR>
  </TBODY>
</TABLE>
<DIV class=buttons>
<INPUT  name="adduser" value=立即注册 type="submit" id="adduser">
</DIV>
</DIV>
</FORM>
</DIV>
</DIV>
</DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
</DL>
</DIV>
</BODY>
</HTML>
