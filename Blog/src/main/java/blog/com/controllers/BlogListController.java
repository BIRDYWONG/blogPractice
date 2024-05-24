package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blogs;
import blog.com.services.BlogsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogsService blogsService;
	
	@GetMapping("/blog/list")
	public  String getBlogList(Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if(account == null) {
			return "redirect:/account/login";
		}else {
			List<Blogs>blogList = blogsService.selectAllBlogList(account.getAccountId());
			model.addAttribute("accountName", account.getAccountName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
