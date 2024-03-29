<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
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
<title>首页</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs">私人空间</a> <a
					class="logo navbar-logo-m f-l mr-10 visible-xs"></a> <span
					class="logo navbar-slogan f-l mr-10 hidden-xs">v1.1</span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>
				<!-- 
				<nav class="nav navbar-nav">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="javascript:;"
							class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i
								class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;"
									onclick="article_add('添加资讯','article-add.html')"><i
										class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
								<li><a href="javascript:;"
									onclick="picture_add('添加资讯','picture-add.html')"><i
										class="Hui-iconfont">&#xe613;</i> 图片</a></li>
								<li><a href="javascript:;"
									onclick="product_add('添加资讯','product-add.html')"><i
										class="Hui-iconfont">&#xe620;</i> 产品</a></li>
								<li><a href="javascript:;"
									onclick="member_add('添加用户','member-add.html','','510')"><i
										class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
							</ul></li>
					</ul>
				</nav>
				 -->
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${user.username}<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
							    
								<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
								<li><a href="javascript:;" onClick="updatePassword()">修改密码</a></li>
								<li><a href="logout.do">切换账户</a></li>
								<li><a href="logout.do">退出</a></li>
							</ul></li>
						<!-- 
						<li id="Hui-msg"><a href="#" title="消息"><span
								class="badge badge-danger">1</span><i class="Hui-iconfont"
								style="font-size: 18px">&#xe68a;</i></a></li>
						 -->
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt class="selected">
					<i class="Hui-iconfont">&#xe616;</i>私人网盘<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd style="display: block;">
					<ul>
						<li><a data-href="/space/picturePage.do" data-title="图片"
							href="javascript:;">图片</a></li>
						<li><a data-href="/space/documentPage.do" data-title="文档"
							href="javascript:void(0)">文档</a></li>
						<li><a data-href="/space/videoPage.do" data-title="视频"
							href="javascript:void(0)">视频</a></li>
						<li><a data-href="/space/torrentPage.do" data-title="种子"
							href="javascript:void(0)">种子</a></li>
						<li><a data-href="/space/musicPage.do" data-title="音乐"
							href="javascript:void(0)">音乐</a></li>
						<li><a data-href="/space/otherPage.do" data-title="其他"
							href="javascript:void(0)">其他</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<a data-href="/recycleBin/recycleBinPage.do" data-title="回收站"
						href="javascript:void(0)"><i class="Hui-iconfont">&#xe609;</i>
						回收站</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>

			</dl>
			<!-- 
			<dl id="menu-comments">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 评论管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="http://h-ui.duoshuo.com/admin/"
							data-title="评论列表" href="javascript:;">评论列表</a></li>
						<li><a data-href="feedback-list.html" data-title="意见反馈"
							href="javascript:void(0)">意见反馈</a></li>
					</ul>
				</dd>
			</dl>
			 -->
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="私人空间"
						data-href="/space/spacePage.do">私人空间</span> <em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="/space/spacePage.do"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
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
		src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		$(function() {
			/*$("#min_title_list li").contextMenu('Huiadminmenu', {
				bindings: {
					'closethis': function(t) {
						console.log(t);
						if(t.find("i")){
							t.find("i").trigger("click");
						}		
					},
					'closeall': function(t) {
						alert('Trigger was '+t.id+'\nAction was Email');
					},
				}
			});*/
		});
		function updatePassword(){
			layer_show('修改密码','/user/updatePassword.do',400,300);
		}
		/*个人信息*/
		function myselfinfo() {
			layer_show('个人信息','/user/personalInfomation.do',400,300);
		}

		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>
</body>
</html>
