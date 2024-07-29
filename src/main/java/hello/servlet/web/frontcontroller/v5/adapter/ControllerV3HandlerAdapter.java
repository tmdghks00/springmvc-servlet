package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    } // handler 가 ControllerV3 의 인스턴스야? 라고 물어보는것임
// ControllerV3 인터페이스를 구현한 무언가가 넘어오면 true 아니면 false 반환

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse
            response, Object handler) {
        //사실 MemberFormControllerV3 이다
        ControllerV3 controller = (ControllerV3) handler;
// handle 은 supports 에서 v3만 걸렀기때문에 캐스팅해서 사용해도됨
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        return mv;
// 어댑터의 역할은 핸들러를 호출해주고 그 결과가 오면 반환타입을 모델뷰로 맞춰서 반환
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }

}