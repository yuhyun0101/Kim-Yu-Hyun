package kr.mjc.yuhyun.web.mvc.article;

import kr.mjc.yuhyun.web.dao.Article;
import kr.mjc.yuhyun.web.dao.ArticleDao;
import kr.mjc.yuhyun.web.dao.User;
import kr.mjc.yuhyun.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ArticleController {

  private final ArticleDao articleDao;

  @Autowired
  public ArticleController(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  public ArticleController() {
    articleDao = null;
  }

  /**
   * 게시글 목록 화면
   */
  public void articleList(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.setAttribute("articleList", articleDao.listArticles(0, 100));

    request.getRequestDispatcher("/WEB-INF/jsp/article/articleList.jsp")
            .forward(request, response);
  }

  /**
   * 게시물 등록 액션
   */
  public void addArticle(HttpServletRequest request, HttpServletResponse response)
          throws IOException {

    Article article = new Article();
    article.setTitle(request.getParameter("title"));
    article.setContent(request.getParameter("content"));
    article.setName(request.getParameter("name"));

    try {
      articleDao.addArticle(article);
      response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    } catch (DuplicateKeyException e) {
      response.sendRedirect(request.getContextPath() +
              "/mvc/article/articleForm?msg=Duplicate email");
    }
  }


}