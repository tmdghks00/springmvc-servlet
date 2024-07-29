package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

 private Map<String, ControllerV3> controllerMap = new HashMap<>();

 public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new
MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new
MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new
MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {

 String requestURI = request.getRequestURI();

 ControllerV3 controller = controllerMap.get(requestURI);
 if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
 return;
}

 Map<String, String> paramMap = createParamMap(request);
 ModelView mv = controller.process(paramMap);

 // new-form
 String viewName = mv.getViewName(); // 논리이름 new-form
 MyView view = viewResolver(viewName); // 뷰를 해결해준다는 의미로 단순화함
        view.render(mv.getModel(), request, response);
    }

 private Map<String, String> createParamMap(HttpServletRequest request) {
 Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
    return paramMap;
 }
private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
// 뷰의 논리이름을 가지고 실제물리이름을 만들면서 MyView 를 반환해주는 viewResolver
// 라는 메서드이름을 만듬
 }

 // createParamMap 을 가지고 HttpServletRequest 에 있는 파라미터를 다 뽑아서 paramMap
// 에다가 반환해줌