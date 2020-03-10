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
<link rel="stylesheet" type="text/css" href="/lib/zTree_v3-master/css/demo.css" />
<link rel="stylesheet" type="text/css" href="/lib/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>文件操作</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" action="/space/move2.do" method="post" id="form-space-move">
		<input type="hidden" value="${id}" name="spaceId">
		<input type="hidden"  name="parent" id='parent'>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>文件名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${name}" placeholder=""
						id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">文件位置：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="article_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 上传
					</button>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="/lib/jquery-1.11.3/jquery-1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script>
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
		src="/lib/ueditor/1.4.3/ueditor.all.min.js"></script>
	<script type="text/javascript"
		src="/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="/lib/zTree_v3-master/js/jquery.ztree.all.js"></script>
	<script type="text/javascript">
	    var zTreeObj;
	    var setting={};
	    var zNodes=[];
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			//表单验证
			$("#form-space-move")
					.validate(
							{
								rules : {
									name : {
										required : true,
									},
									

								},

								submitHandler : function(form) {
									getCheckedData();
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
																				var index = parent.layer.getFrameIndex(window.name);
																				window.parent.refreshTable();
																				parent.layer.close(index);
																			}
																		})
													})
								}
							})
		});
		
		$(function(){
			var setting={
					check:{
						enable:true,
						chkStyle:"radio",
						radioType:'all',
					},
					data:{
						simpleData:{
							enable:true
						}
					},
					async:{
						enable:true,
						url:"/space/getSpace.do?userId="+'${user.id}',
					},
					callback:{
						onAsyncSuccess:zTreeOnAsyncSuccess
					}
			};
			

			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
		})
		
		function zTreeOnAsyncSuccess(event,treeId,treeNode,msg){
			var a='${parent}';
			var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
			var node=treeObj.getNodeByParam("id",a,null);
			treeObj.checkNode(node, true, false,false);
		}
		function getCheckedData(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node=treeObj.getCheckedNodes(true);
			$("#parent").val(node[0].id);
		}
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
