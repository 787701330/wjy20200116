package com.wjy.space.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wjy.space.pojo.User;

@Controller
public class LoginController {
	
	@RequestMapping("/login.do")
	public String Login(HttpServletRequest request,Model m) {
		String shiroLoginFailure=(String) request.getAttribute("shiroLoginFailure");
		if(shiroLoginFailure!=null) {
			if(UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("errorMsg","亲，帐号不存在！");
			}else if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
				m.addAttribute("errorMsg","亲，密码错误！");
			}
		}
		
		return "login";
	}
	

	
	@RequestMapping("/index.do")
	public String Index(HttpServletRequest request) {
		User user=(User) SecurityUtils.getSubject().getPrincipal();
	    request.getSession().setAttribute("user", user);
	    return "index";
	}
	

	
	@RequestMapping("/getVerifyCode.do")
	public void getVerifyCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
		BufferedImage img=new BufferedImage(125,69,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 125, 69);
		graphics.setFont(new Font("黑体", Font.BOLD, 40)); 
		String baseNumLetter= "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		int x=10;
		StringBuffer sBuffer=new StringBuffer();
		for(int i=0;i<4;i++) {
			graphics.setColor(new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
			
			int dot=new Random().nextInt(baseNumLetter.length());
			String ch=baseNumLetter.charAt(dot)+"";
			sBuffer.append(baseNumLetter.charAt(dot));
			int degree=new Random().nextInt()%30;
			graphics.rotate(degree*Math.PI/180,x,45);
			graphics.drawString(ch, x, 45);
			graphics.rotate(-degree*Math.PI/180,x,45);
			x+=25;
		}
		for(int i=0;i<20;i++) {
			graphics.setColor(new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
			graphics.drawLine(new Random().nextInt(125), new Random().nextInt(69), new Random().nextInt(125), new Random().nextInt(69));
		}
		
		for(int i=0;i<30;i++) {
			graphics.setColor(new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
			graphics.fillRect(new Random().nextInt(125), new Random().nextInt(69), 2, 2);
		}
		request.getSession().setAttribute("rand", sBuffer.toString());
		response.setContentType("image/png");
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(img, "png", outputStream);
		outputStream.flush();
		outputStream.close();
	}
	

	
}
