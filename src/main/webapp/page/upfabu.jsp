<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
  <script src="../scripts/jquery-1.8.3.js"></script>
  <script>
      $(function () {
          $.post("/admin/getAllType",null,function (data) {

              for (var i = 0; i <data.length ; i++) {
                  //创建节点
                  var op=$("<option value="+data[i].id+">"+data[i].name+"</option>>");
                  //添加节点
                  $("#type_id").append(op);
              };
              $("#type_id").val(${extHouse.typeId});
          },"json");

          $.post("/admin/getAllDistrict",null,function (data) {

              for (var i = 0; i <data.length ; i++) {
                  //创建节点
                  var op=$("<option value="+data[i].id+">"+data[i].name+"</option>>");
                  //添加节点
                  $("#district_id").append(op);
              };

              $("#district_id").val(${extHouse.did});
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

              $("#street_id").val(${extHouse.streetId})
          },"json");
      }
  </script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>

<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>房屋信息修改</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=update_action method=post name=update.action action=updateHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title value="${extHouse.title}">
        <INPUT type=hidden name=id value="${extHouse.id}">
    </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="type_id">
            <OPTION selected >请选择</OPTION>
        </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text name=floorage value="${extHouse.floorage}"></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${extHouse.price}"> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${extHouse.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=district_id id="district_id">
                <OPTION selected >请选择</OPTION>
            </SELECT>
      街：<SELECT class=text name=streetId id="street_id">
                <OPTION selected >请选择</OPTION>
          </SELECT>
    </TD>
  </TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${extHouse.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${extHouse.description}</TEXTAREA>
    </TD>
  </TR>
  <TR>
    <TD class=field>上传图片：</TD>
    <TD>
      <INPUT id=img class=text type=hidden name=oldpic value="${extHouse.path}">
      <img src="http://localhost:80/${extHouse.path}" alt="" width="100px">
      <input type="file" name="mfile" id="file" onchange="change()">
    </TD>
  </TR>
  </TBODY>
</TABLE>
<DIV class=buttons>
  <!--<INPUT onclick='document.location="list.jsp"' value=立即发布 type=button> -->
  <input type="submit" value="立即修改" >
</DIV>
</DIV>
</FORM>
</DIV>
</DIV>
</DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script>


</script>
</HTML>
