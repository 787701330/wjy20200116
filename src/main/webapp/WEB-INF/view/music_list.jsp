<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
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
<title>私人网盘</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 私人空间 <span class="c-gray en">&gt;</span> 存储列表:音乐 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span> </div>
	<table id="spaceTable" class="table table-border table-bordered table-bg">
	</table>
</div> 
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
var reN1=0;
var pId=1;
function space_download(id){
	var i="/space/download.do?id="+id;
	window.open(i);
}

function datadel(){
	var ids=[];
	var row=$("#spaceTable").bootstrapTable('getSelections');
	for(var i=0;i<row.length;i++){
		ids.push(row[i].id);
	}
	ids=ids+"";
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({url:"/space/delete.do",data:{id:ids},success:function(data){
			layer.msg(data.msg,{time:1000,icon:6});
			if(data.code=1){
				refreshTable();
			}
		},dataType:'json',type:'post'})
	});
}

function space_rename(obj,id){
	var a=$(obj).prev().val();
	$.ajax({url:"/space/rename.do",data:{id:id,name:a},success:function(data){
		layer.msg(data.msg,{time:1000,icon:6});
	}})
	refreshTable();
}

function space_move(obj,id){
	layer_show('文件操作','/space/move.do?id='+id);
}

/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function space_add(title,url,w,h){
	var a=url+"?parent="+pId;
	layer_show(title,a,w,h);
}
/*管理员-删除*/
function space_del(obj,id){
	
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({url:"/space/delete.do",data:{id:id},type:'post',success:function(data){
			layer.msg(data.msg,{time:1000,icon:6});
			if(data.code=1){
				refreshTable();
			}
		}})
	});
}

/*管理员-编辑*/
function space_edit(obj,id,isFolder){
	if(reN1==0){
		reN1=1;
	obj.removeAttribute("onClick");
	var a=$(obj).parent().prev().prev().prev().prev();
	if(isFolder==1){
		var b=a.first().text();
		b=b+"";
	}else{
		var b=a.text();	
	}
	c='<input type="text" class="input-text col-xs-10 col-sm-10" value='+b+'><a title="确定" href="javascript:;" onclick="space_rename(this,'+id+')" style="text-decoration:none "><i><span class="glyphicon glyphicon-ok" style="top:5px"></span></i></a><a title="取消" href="javascript:;" onclick="refreshTable()" style="text-decoration:none "><i class=""><span style="top:5px" class="glyphicon glyphicon-remove"></span></i></a>';
	a.html(c);
	}
}


/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
function refreshTable(){
	reN1=0;
	$("#spaceTable").bootstrapTable("refresh");
}
</script>
<script type="text/javascript">
  $(function(){
	  $('#spaceTable').bootstrapTable({
		  url:'/space/music_list.do',
		  responseHandler:function(res){
			  var data={rows:res.list,total:res.total};
			  return data;
		  },
		  toolbar:"#toolbar",
		  pagination:true,
		  contentType:'application/x-www-form-urlencoded',
		  search:true,
		  pageNumber:1,
		  pageSize:10,
		  pageList:[10,25,50,100],
		  sidePagination:"server",
		  paginationHAlign:'right',
		  showToggle:true,
		  cardView:false,
		  showColumns:true,
		  showRefresh:true,
		  columns:[
			  {checkbox:true},
			  {field:'name',title:'文件名称',formatter:addFolder},
			  {field:'size',title:'文件大小'},
			  {field:'created',title:'创建时间'},
			  {field:'updated',title:'修改时间'},
			  {field:'id',title:'操作',align:'center',formatter:operationFormatter}
		  ],
		  queryParams:function(params){
			  return {
				  pageNum:params.offset/params.limit+1,
				  pageSize:params.limit,
				  keyword:params.search,
				  userId:'${user.id}',
			  };
		  },
	  });
	  });
  
	  
  function operationFormatter(value,row,index){
	  var html;
	  if(index==0&&pId!=1){
		 
	  }else if(row.isFolder==1){
		  html='<a title="删除"" href="javascript:;" onclick="space_del(this,'+row.id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont"><span class="glyphicon glyphicon-trash"></span></i></a>';
	  }else{
	      html='<a title="下载" href="javascript:;" onclick="space_download('+row.id+')" style="text-decoration:none"><i class="Hui-iconfont"><span class="glyphicon glyphicon-save"></span></i></a>';
	      html+='<a title="删除"" href="javascript:;" onclick="space_del(this,'+row.id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont"><span class="glyphicon glyphicon-trash"></span></i></a>';
	  }
	  return html;
  }
  
  function addFolder(value,row,index){
	  var a=row.isFolder;
	  if(a==0){
		  return row.name;
	  }
	  if(a==1){
		  var b='<a href="javascript:;" onclick="addFolder2(this,'+row.id+')" style="text-decoration:none;color:black">'+row.name+'</a>';
		  return b;
	  }
  }
  
  function addFolder2(value,id){
	  $('#spaceTable').bootstrapTable('destroy');
	  $('#spaceTable').bootstrapTable({
		  url:'/space/music_list.do',
		  responseHandler:function(res){
			  var data={rows:res.list,total:res.total};
			  return data;
		  },
		  pagination:true,
		  toolbar:"#toolbar",
		  contentType:'application/x-www-form-urlencoded',
		  search:true,
		  pageNumber:1,
		  pageSize:10,
		  pageList:[10,25,50,100],
		  sidePagination:"server",
		  paginationHAlign:'right',
		  showToggle:true,
		  cardView:false,
		  showColumns:true,
		  showRefresh:true,
		  columns:[
			  {checkbox:true},
			  {field:'name',title:'文件名称',formatter:addFolder},
			  {field:'size',title:'文件大小'},
			  {field:'created',title:'创建时间'},
			  {field:'updated',title:'修改时间'},
			  {field:'id',title:'操作',align:'center',formatter:operationFormatter}
		  ],
		  queryParams:function(params){
			  return {
				  pageNum:params.offset/params.limit+1,
				  pageSize:params.limit,
				  keyword:params.search,
				  userId:'${user.id}',
				  parent:id
			  };
		  },  
	  });
	  pId=id;
  }

  
  function folder_add(){
	  var a='<tr> <td class="bs-checkbox" style="width:36px"> </td> <td style=""><input type="text" class="input-text col-xs-10 col-sm-10" ><a title="确定" href="javascript:;" onclick="folder_add2(this)" style="text-decoration:none "><i><span class="glyphicon glyphicon-ok" style="top:5px"></span></i></a><a title="取消" href="javascript:;" onclick="refreshTable()" style="text-decoration:none "><i class=""><span style="top:5px" class="glyphicon glyphicon-remove"></span></i></a></td><td></td><td></td><td></td><td></td></tbody>';
	  $('#spaceTable').append(a);
  }
  
  function folder_add2(obj){
	  var a=$(obj).prev().val();
	  $.ajax({url:"/space/addFolder.do",data:{userId:'${user.id}',parent:pId,name:a},success:function(data){
			layer.msg(data.msg,{time:1000,icon:6});
				refreshTable();
		},dataType:'json',type:'post'});
  }
</script>
</body>
</html>
