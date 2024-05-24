package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogsDao;
import blog.com.models.entity.Blogs;

@Service
public class BlogsService {

	@Autowired
	private BlogsDao blogsDao;
	
	public List<Blogs>selectAllBlogList(Long accountId) {
	if (accountId == null) {
		return null;
	} else {
		return blogsDao.findAll();
		
	}
}

	
	public boolean createBlog(String blogTitle,
			String categoryName,
			String blogImage,
			String article,
			Long accountId) {
		if(blogsDao.findByBlogTitle(blogTitle)==null) {
			blogsDao.save(new Blogs(blogTitle,categoryName,blogImage,article,accountId));
				return true;
			}else {
				return false;
			}
	}	
	
	public Blogs blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogsDao.findByBlogId(blogId);
		}
	}
	
	// 更新处理的确认
	public boolean blogUpDate(Long blogId,
			String blogTitle,
			String categoryName,
			String blogImage,
			String article,
			Long accountId) {
		if(blogId == null) {
			return false;
		}else {
			Blogs blogs = blogsDao.findByBlogId(blogId);
			blogs.setBlogTitle(blogTitle);
			blogs.setCategoryName(categoryName);
			blogs.setBlogImage(blogImage);
			blogs.setArticle(article);
			blogs.setAccountId(accountId);
			blogsDao.save(blogs);
			return true;
		}
	}
	
	//删除处理
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogsDao.deleteByBlogId(blogId);
			return true;
		}
	}
		
}

