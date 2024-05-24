package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blogs;
import blog.com.services.BlogsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogsEditController {

	@Autowired
	private BlogsService blogsService;
	@Autowired
	private HttpSession session;
	private String fileName;

	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			Blogs blogs = blogsService.blogEditCheck(blogId);
			if (blogs == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blogs", blogs);
				return "blog_edit.html";
			}
		}
	}
	
	// 更新处理
	@PostMapping("/blog/edit/process")
	public String blogsUpdate(@RequestParam String blogTitle, 
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, 
			@RequestParam String article,
			@RequestParam Long blogId) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		if (blogsService.blogUpDate(blogId,blogTitle, categoryName,fileName,article, account.getAccountId())) {
			return "redirect:/blog/list";
		} else {
			return "redirect:/blog/edit"+blogId;
	}
	
	
	
	
	
	}
	

}
