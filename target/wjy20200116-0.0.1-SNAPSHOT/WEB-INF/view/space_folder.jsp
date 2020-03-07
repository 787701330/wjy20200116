<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

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
<link rel="stylesheet" type="text/css"
	href="/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="/lib/bootstrap-table-master/bootstrap-table.css" />
<link rel="stylesheet" type="text/css" href="/lib/layui-v2.5.6/layui-v2.5.6/layui/css/layui.css">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>私人网盘</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		私人空间 <span class="c-gray en">&gt;</span> 存储列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick='space_add("上传文件","space/space_add.do","800","500")'
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					上传文件</a> <a href="javascript:;" onclick="folder_add()"
				class="btn btn-info radius"><i class="Hui-iconfont">&#xe63e;</i>
					新建文件夹</a></span>
		</div>
		<div class="lyui-fluid">
			<div class="layui-row layui-col-space10 demo-list">
				<div class="layui-col-sm4 layui-col-md3 layui-col-lg2">
					<div class="layui-card">1232</div>
				</div>
				<div class="layui-col-sm4 layui-col-md3 layui-col-lg2">
					<div class="layui-card">1324</div>
				</div>
			</div>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="lib/jquery-1.11.3/jquery-1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript"
		src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="lib/bootstrap-table-master/bootstrap-table.js"></script>
	<script type="text/javascript"
		src="lib/bootstrap-table-master/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="lib/layui-v2.5.6/layui-v2.5.6/layui.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
