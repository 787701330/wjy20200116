package com.wjy.space.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wjy.space.mo.MessageObject;
import com.wjy.space.pojo.Storage;
import com.wjy.space.pojo.StorageExample;
import com.wjy.space.pojo.User;
import com.wjy.space.pojo.StorageExample.Criteria;
import com.wjy.space.service.StorageService;
import com.wjy.space.util.FastDfsUtil;
import com.wjy.space.util.FileSizeUtil;

@Controller
@RequestMapping("/space")
public class SpaceController {

	@Autowired
	private StorageService storageService;

	@RequestMapping("/spacePage.do")
	public String spacePage(Model m) {
		return "space_list";
	}
	
	@RequestMapping("/musicPage.do")
	public String musicPage(Model m) {
		return "music_list";
	}
	
	@RequestMapping("/documentPage.do")
	public String documentPage(Model m) {
		return "document_list";
	}
	
	@RequestMapping("/picturePage.do")
	public String picturePage(Model m) {
		return "picture_list";
	}
	
	@RequestMapping("/videoPage.do")
	public String videoPage(Model m) {
		return "video_list";
	}
	
	@RequestMapping("/torrentPage.do")
	public String torrentPage(Model m) {
		return "torrent_list";
	}
	
	@RequestMapping("/otherPage.do")
	public String otherPage(Model m) {
		return "other_list";
	}
	
	@RequestMapping("/other_list.do")
	@ResponseBody
	public PageInfo<Storage> other_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectOtherList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/torrent_list.do")
	@ResponseBody
	public PageInfo<Storage> torrent_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectTorrentList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/music_list.do")
	@ResponseBody
	public PageInfo<Storage> music_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectMusicList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/video_list.do")
	@ResponseBody
	public PageInfo<Storage> video_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectVideoList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/document_list.do")
	@ResponseBody
	public PageInfo<Storage> document_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectDocumentList(userId, keyword, parent, state, pageNum, pageSize);
	}
	
	@RequestMapping("/picture_list.do")
	@ResponseBody
	public PageInfo<Storage> picture_list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectPictureList(userId, keyword, parent, state, pageNum, pageSize);
	}

	@RequestMapping("/move.do")
	public String spaceMove(Model m, Long id) {
		Storage storage = storageService.selectByPrimaryKey(id);
		Long parent = storage.getParent();
		String name = storage.getName();
		m.addAttribute("name", name);
		m.addAttribute("id", id);
		m.addAttribute("parent", parent);
		return "space_move";
	}

	@RequestMapping("/list.do")
	@ResponseBody
	public PageInfo<Storage> list(Long userId, String keyword, @RequestParam(defaultValue = "1") Long parent,
			@RequestParam(defaultValue = "1") int state, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return storageService.selectList(userId, keyword, parent, state, pageNum, pageSize);
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public MessageObject delete(@RequestParam("id") Long[] id) {
		int delete = storageService.setState(id,2);
		MessageObject mo = null;
		if (delete == 0) {
			mo = new MessageObject(0, "删除文件失败！");
		} else {
			mo = new MessageObject(1, "删除文件成功！");
		}
		return mo;
	}

	@RequestMapping("/space_add.do")
	public String spaceAdd(Long parent, Model m) {
		m.addAttribute("parent", parent);
		return "space_add";
	}

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public MessageObject upload(Long userId, String name, MultipartFile file,
			@RequestParam(defaultValue = "1") Long parent) throws IOException, MyException {
		String fdfsUpload = null;
		int result = -1;
		MessageObject mo = null;
		try {
			fdfsUpload = FastDfsUtil.fdfsUpload(file);
			Storage storage = new Storage();
			storage.setUserId(userId);
			storage.setAdress(fdfsUpload);
			storage.setCreated(new Date());
			storage.setUpdated(storage.getCreated());
			FileInfo info1 = FastDfsUtil.fdfsFileInfo1(fdfsUpload);
			long fileSize = info1.getFileSize();
			String size = FileSizeUtil.FormetFileSize(fileSize);
			String filename = file.getOriginalFilename();
			String substring = filename.substring(filename.lastIndexOf("."));
			String name2 = name + substring;
			int seeName = storageService.seeName(name2, userId, parent);
			if (seeName == 1) {
				mo = new MessageObject(0, "该文件名已存在！");
				return mo;
			}
			storage.setSize(size);
			storage.setName(name2);
			storage.setState(1);
			storage.setParent(parent);
			storage.setIsFolder(0);
			result = storageService.insert(storage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotBlank(fdfsUpload) && result == 1) {
			mo = new MessageObject(1, "上传成功！");
		} else {
			mo = new MessageObject(0, "上传失败！");
		}
		return mo;
	}

	@RequestMapping(value = "/download.do")
	public void download(Long id, HttpServletResponse response) throws IOException, MyException {
		Storage storage = storageService.selectByPrimaryKey(id);
		String name = storage.getName();
		String adress = storage.getAdress();
		byte[] file = FastDfsUtil.fdfsDownload1(adress);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
		response.addHeader("Content-Disposition", "attachment;filename=" + name);
		ServletOutputStream outputStream = response.getOutputStream();
		IOUtils.copy(inputStream, outputStream);
	}

	@RequestMapping(value = "/rename.do")
	@ResponseBody
	public MessageObject rename(Long id, String name) throws Exception {
		int result = storageService.rename(id, name);
		MessageObject mo = null;
		if (result == 1) {
			mo = new MessageObject(1, "重命名成功！");
		} else if (result == 2) {
			mo = new MessageObject(0, "该用户名已存在！");
		} else {
			mo = new MessageObject(0, "重命名失败！");
		}
		return mo;
	}

	@RequestMapping(value = "/addFolder.do")
	@ResponseBody
	public MessageObject addFolder(Long userId, String name, @RequestParam(defaultValue = "1") Long parent) {
		int result = storageService.addFolder(userId, name.trim(), parent);
		MessageObject mo = null;
		if (result == 1) {
			mo = new MessageObject(1, "文件夹创建成功！");
		} else if (result == 2) {
			mo = new MessageObject(0, "该文件名已存在！");
		} else {
			mo = new MessageObject(0, "文件夹创建失败！");
		}
		return mo;
	}

	@RequestMapping(value = "/getSpace.do")
	@ResponseBody
	public List<Storage> getSpace(Long userId, Long parentId) {
		StorageExample example = new StorageExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIsFolderEqualTo(1);
		criteria.andStateEqualTo(1);
		Criteria criteria2 = example.createCriteria();
		criteria2.andIdEqualTo((long) 1);
		example.or(criteria2);
		return storageService.selectByExample(example);
	}

	@RequestMapping(value = "/move2.do")
	@ResponseBody
	public MessageObject move2(String name, Long parent, Long spaceId) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Long id = user.getId();
		int result = storageService.move2(name, parent, spaceId, id);
		MessageObject mo = null;
		if (result == 1) {
			mo = new MessageObject(1, "文件操作成功！");
		} else if (result == 2) {
			mo = new MessageObject(0, "该文件名已存在！");
		} else {
			mo = new MessageObject(0, "文件操作失败！");
		}
		return mo;
	}

}
