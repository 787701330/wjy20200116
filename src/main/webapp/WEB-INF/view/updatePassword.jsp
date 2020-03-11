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
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/lib/html5shiv.js"></script>
<script type="text/javascript" src="/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>修改密码</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" action="/user/updatePassword2.do"
			method="post" enctype="multipart/form-data" id="form-space-add">
			<input type="hidden" value="${user.id}" name="userId">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">原密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" value="" placeholder=""
						id="password" name="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"> 密码： </label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" id="newPassword" name="newPassword"
						placeholder="不能少于6个字符，不可多于12个字符" value="" class="input-text">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">确认新密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" class="input-text" value="" placeholder=""
						id="newPassword2" name="newPassword2">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 修改
					</button>
					<button onClick="layer_close();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="/lib/jquery-1.11.3/jquery-1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"
		src="/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript"
		src="/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript"
		src="/lib/ueditor/1.4.3/ueditor.all.min.js">
		
	</script>
	<script type="text/javascript"
		src="/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			//表单验证
			$("#form-space-add")
					.validate(
							{
								rules : {
									password : {
										required : true,
										remote : {
											url : '/user/testPassword.do',
											dataType : 'json',
											data : {
												username : function() {
													return $('#password').val();
												},
												userId:'${user.id}'
											},
											type:'post'
										}
									},
									newPassword : {
										required : true,
										minlength : 6,
										maxlength : 12
									},
									newPassword2 : {
										required : true,
										equalTo : "#newPassword"
									}

								},messages : {
									password : {
										reuqired : '请输入密码！',
										remote : '密码不正确！'
									},
									newPassword : {
										reuqired : '请输入密码！',
										minlength : "密码不得少于6个字符！",
										maxlength : '密码不得多于12个字符！'
									},
									newPassword2 : {
										required : '请再次输入密码！',
										equalTo : "密码需与原密码一致！"
									}
								},
								submitHandler : function(form) {
									$(form)
											.ajaxSubmit(
													function(data) {
														layer
																.msg(
																		data.msg,
																		{
																			icon : data.code,
																			time : 2000
																		},
																		function() {
																			if (data.code != 0) {
																				var index = parent.layer
																						.getFrameIndex(window.name);
																				window.parent
																						.refreshTable();
																				parent.layer.close(index);
																			}
																		})
													})
								}
							})
		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
