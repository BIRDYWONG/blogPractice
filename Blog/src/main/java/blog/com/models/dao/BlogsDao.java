package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blogs;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogsDao extends JpaRepository<Blogs, Long> {
	Blogs save(Blogs blogs);
	
	List<Blogs>findAll();
	Blogs findByBlogTitle(String blogTitle);
	Blogs findByBlogId(Long blogId);
	
	void deleteByBlogId(Long blogId);
}
	


