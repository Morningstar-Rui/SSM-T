<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <script src="js/jquery-1.8.3.js"></script>
  <script>
    $(function () {
        $("#btn1").click(function () {
            $.post("sendCode",{"tel":$("#tel").val()},function (data) {
                if (data.result>0){
                    alert("发送成功")
                } else {
                    alert("操作失败！")
                }
            },"json")
        });

    })
  </script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DIV class=box>
<H4>用户登录</H4>
<FORM id=user2 method=post name=user2 action="loginByTerm2">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD colSpan=2></TD></TR>
  <TR>
    <TD class=field>手 机 号：</TD>
    <TD><!-- <input type="text" class="text" name="name" /> --><INPUT 
      id=tel class=tel type=text name=tel> <input type="button" value="发送验证码" id="btn1"> </TD></TR>
  <TR>
    <TD class=field>验 证 码：</TD>
    <TD><!-- <input type="password" class="text" name="password" /> --><INPUT 
      id=code class=code type=text name=code> </TD></TR></TBODY></TABLE>
<DIV class=buttons> <INPUT value=登陆 type=submit name="sub">
  <INPUT onclick='document.location="regs.jsp"' value=注册 type=button>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
