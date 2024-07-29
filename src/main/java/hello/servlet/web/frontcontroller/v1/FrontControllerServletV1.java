package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v1/* 은 v1 하위에 어떤것이 들어와도 이 서블릿이 무조건 호출됨
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
 public class FrontControllerServletV1 extends HttpServlet {

 private Map<String, ControllerV1> controllerMap = new HashMap<>();
// 어떤 url이 호출되면 컨트롤러 v1을 꺼내서 호출하는식으로 구현함

 public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new
MemberFormControllerV1());
// 왼쪽 url 이 호출되면 오른쪽 객체 인스턴스가 실행됨
        controllerMap.put("/front-controller/v1/members/save", new
MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new
MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {

 System.out.println("FrontControllerServletV1.service");
 String requestURI = request.getRequestURI();
// requestURI 이 /front-controller/v1/members 과 같은 가져온 url 이다

// 다형성에 의해서 인터페이스로 받을수 있음 왜냐하면 MemberListControllerV1() 는
// 부모가 ControllerV1 인터페이스 이기 때문이다

// ControllerV1 controller = new MemberListControllerV1();
 ControllerV1 controller = controllerMap.get(requestURI);
 if (controller == null) { // 예외처리
     response.setStatus(HttpServletResponse.SC_NOT_FOUND);
     return; // 상태코드를 404로 바꾸고 리턴
 }

 controller.process(request, response);
 // 다형성에 의해서 override 된 MemberListControllerV1()의 process 메서드 호출
    }
 }