package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogsDeleteController {

	@Autowired
	private BlogsService blogsService;
	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogsDelete(Long blogId) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			if (blogsService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}
	}
}
