<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="/lib/bootstrap-table-master/bootstrap-table.css"/>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>个人信息</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"> 真实姓名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="form-text">
					${user.realname}<a title="操作" href="javascript:;"
						onclick="updateInfo(this)" style="text-decoration: none"><i
						class="Hui-iconfont"><span class="glyphicon glyphicon-pencil"></span></i></a>
				</div>
				<div style="display: none;">
					<input type="text" class="input-text" value="${user.realname}"
						id="realname" name="realname"> <a title="确定"
						href="javascript:;" onclick="Info_submit(this)"
						style="text-decoration: none;"><i><span
							class="glyphicon glyphicon-ok" style="top: 5px"></span></i></a><a
						title="取消" href="javascript:;" onclick="Info_remove(this)"
						style="text-decoration: none;"><i class=""><span
							style="top: 5px" class="glyphicon glyphicon-remove"></span></i></a>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"> 性别：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="radio" name="gender" value="1" class="input-checkbox">男
				<input type="radio" name="gender" value="2" class="input-checkbox">女
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"> 电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="form-text">
					${user.phone}<a title="操作" href="javascript:;"
						onclick="updateInfo(this)" style="text-decoration: none"><i
						class="Hui-iconfont"><span class="glyphicon glyphicon-pencil"></span></i></a>
				</div>
				<div style="display: none;">
					<input type="text" class="input-text" value="${user.phone}"
						id="phone" name="phone"> <a title="确定" href="javascript:;"
						onclick="Info_submit(this)" style="text-decoration: none;"><i><span
							class="glyphicon glyphicon-ok" style="top: 5px"></span></i></a><a
						title="取消" href="javascript:;" onclick="Info_remove(this)"
						style="text-decoration: none;"><i class=""><span
							style="top: 5px" class="glyphicon glyphicon-remove"></span></i></a>
				</div>
			</div>
		</div>
				<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"> 邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="form-text">
					${user.email}<a title="操作" href="javascript:;"
						onclick="updateInfo(this)" style="text-decoration: none"><i
						class="Hui-iconfont"><span class="glyphicon glyphicon-pencil"></span></i></a>
				</div>
				<div style="display: none;">
					<input type="text" class="input-text" value="${user.email}"
						id="email" name="email"> <a title="确定" href="javascript:;"
						onclick="Info_submit(this)" style="text-decoration: none;"><i><span
							class="glyphicon glyphicon-ok" style="top: 5px"></span></i></a><a
						title="取消" href="javascript:;" onclick="Info_remove(this)"
						style="text-decoration: none;"><i class=""><span
							style="top: 5px" class="glyphicon glyphicon-remove"></span></i></a>
				</div>
			</div>
		</div>
	</article>

	<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery-1.11.3/jquery-1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/lib/bootstrap-table-master/bootstrap-table.js"></script>
<script type="text/javascript" src="/lib/bootstrap-table-master/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
		$(function() {
			var gender = '${user.gender}'
			$("input[name='gender'][value=" + gender + "]").attr("checked",
					"checked");
			$("input:radio[name='gender']").change(function(){
				var val=$("input[name='gender']:checked").val();
				$.ajax({
				url : "/user/updateInfomation.do",
				type : 'post',
				async : false,
				data : {
					'gender':val,
					'id':'${user.id}'
				},
				success : function(data) {
					window.location.reload();
				},
			});
			})
		})

		function updateInfo(obj) {
			var a = $(obj);
			var b = a.parent();
			b.attr("style", "display: none;");
			b.next().attr("style", "");
		}

		function Info_submit(obj) {
			var a= $(obj);
			var name = a.prev().attr("name")+"";
			var val = a.prev().val();
			var id = '${user.id}';
			var user={"id":id};
			user[name]=val;
			$.ajax({
				url : "/user/updateInfomation.do",
				type : 'post',
				async : false,
				data : user,
				success : function() {
					window.location.reload();
				},
				error : function() {
					a.parent().attr('style', "display: none;");
					a.parent().prev().attr("style", "");
				}
			})
		}

		function Info_remove(obj) {
			var a = $(obj);
			a.parent().attr("style", "display: none;");
			a.parent().prev().attr("style", "");
		}
	</script>
</body>
</html>
