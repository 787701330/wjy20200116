package com.wjy.space.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/space_folder")
public class SpaceFolderController {
	
	@RequestMapping("/list.do")
	public String list() {
		return "space_folder";
	}
}
