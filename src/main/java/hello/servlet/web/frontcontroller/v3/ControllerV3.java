package hello.servlet.web.frontcontroller.v3;
import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
//ControllerV2 는 서블릿 기술이 들어갔지만 ControllerV3 는 서블릿에 종속적이지 않음
