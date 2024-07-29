package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 요청
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // 응답
        response.setCharacterEncoding("utf-8"); // 인코딩 정보 알려줌
        // 위 2줄은 Content-Type(헤더 정보) 에 들어감
        response.getWriter().write("hello " + username);
        // getWriter() 라고 하면 HTTP 메시지 바디에 데이터가 들어감
    }
}
