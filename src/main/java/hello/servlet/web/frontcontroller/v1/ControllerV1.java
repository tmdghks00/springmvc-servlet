package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// 서블릿이랑 모양이 같은 인터페이스를 만듬
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}