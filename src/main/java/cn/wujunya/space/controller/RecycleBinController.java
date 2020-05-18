package cn.wujunya.space.controller;

import java.io.IOException;

import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import cn.wujunya.space.mo.MessageObject;
import cn.wujunya.space.pojo.Storage;
import cn.wujunya.space.service.StorageService;

@Controller
@RequestMapping("/recycleBin")
public class RecycleBinController {
	
	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/recycleBinPage.do")
	public String spacePage(Model m) {
		return "recycleBin_list";
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public PageInfo<Storage> list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "2") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public MessageObject delete(@RequestParam("id") Long[] id) throws IOException, MyException {
		int delete = storageService.delete(id);
		MessageObject mo = null;
		if (delete == 0) {
			mo = new MessageObject(0, "删除文件失败！");
			return mo;
		} else {
			mo = new MessageObject(1, "删除文件成功！");
			return mo;
		}
	}
	
	@RequestMapping("/move.do")
	@ResponseBody
	public MessageObject setState(@RequestParam("id") Long[] id) throws IOException, MyException {
		int delete = storageService.setState(id,1);
		MessageObject mo = null;
		if (delete == 0) {
			mo = new MessageObject(0, "还原文件失败！");
			return mo;
		} else {
			mo = new MessageObject(1, "还原文件成功！");
			return mo;
		}
	}
	
	
}
