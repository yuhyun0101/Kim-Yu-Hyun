package kr.mjc.yuhyun.web.model2.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/model2/user/loginForm")
public class LoginFormServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String msg = Optional.ofNullable(request.getParameter("msg")).orElse("");
    out.format("""
        <!DOCTYPE html>
        <html>
        <body>
          <h3>로그인</h3>
          <form action="login" method="post">
            <p><input type="email" name="email" placeholder="이메일" required autofocus /></p>
            <p><input type="password" name="password" placeholder="비밀번호" required /></p>
            <p><button type="submit">로그인</button></p>
          </form>
          <p style="color:red;">%s</p>
        </body>
        </html>
        """, msg);
    out.close();
  }
}
