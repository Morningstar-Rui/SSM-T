<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
  <script src="js/jquery-1.8.3.js"></script>
  <script>
      $(function () {
          $.post("/admin/getAllType",null,function (data) {

              for (var i = 0; i <data.length ; i++) {
                  //创建节点
                  var op=$("<option value="+data[i].id+">"+data[i].name+"</option>>");
                  //添加节点
                  $("#type_id").append(op);
              };
              $("#type_id").val(${term.tid});
          },"json");

          $.post("/admin/getAllDistrict",null,function (data) {

              for (var i = 0; i <data.length ; i++) {
                  //创建节点
                  var op=$("<option value="+data[i].id+">"+data[i].name+"</option>>");
                  //添加节点
                  $("#district_id").append(op);
              };

              $("#district_id").val(${term.did});
              changeArea();
          },"json");


          $("#district_id").change(function () {
              changeArea();
          });
      });
      function changeArea() {
          //清空选项
          $("#street_id>option:gt(0)").remove();
          $.post("getAllStreetByDistrictId",{"districtId":$("#district_id").val()},function (data) {
              for (var i = 0; i <data.length ; i++) {

                  //创建节点
                  var op=$("<option value="+data[i].id+">"+data[i].name+"</option>>");
                  //添加节点
                  $("#street_id").append(op);
              }

              $("#street_id").val(${term.sid})
          },"json");
      }

      function toPage(num) {
          $("#page").val(num);
          $("#sform").submit();
      }
  </script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
    <form action="/page/getAllHoesuBySearch" method="post" id="sform">
    <input type="hidden" value="1" name="page" id="page">
    标题: <input type="text" name="title" value="${term.title}">
    区域：<select name="did" id="district_id">
          <option value="">请选择</option>
          </select>
    街道：<select name="sid" id="street_id">
            <option value="">请选择</option>
          </select>
    类型：<select name="tid" id="type_id">
              <option value="" >请选择</option>
          </select>
    价格：<input type="text" name="minPrice" value="${term.minPrice}" style="width:100px">
    -<input type="text" name="maxPrice" value="${term.maxPrice}"style="width:100px" >

    <input type="submit" value="搜索" style="width: 70px;color:red;font-size: 13px">
    </form> </DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="house">
  <TR>
    <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">${house.title}</A></DT>
        <DD>${house.dname}${house.sname},${house.floorage}平米<BR>联系方式：${house.contact} </DD></DL></TD>
    <TD class=house-type>${house.tname}</TD>
    <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD></TR>
  </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><a href="javascript:toPage(1)">首页</a></LI>
  <LI><a href="javascript:toPage(${pageInfo.prePage})">上一页</a></LI>
  <LI><a href="javascript:toPage(${pageInfo.nextPage})">下一页</a></LI>
  <LI><a href="javascript:toPage(${pageInfo.lastPage})">尾页</a></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
