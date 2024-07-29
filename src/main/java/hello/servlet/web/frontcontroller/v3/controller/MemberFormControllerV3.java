package hello.servlet.web.frontcontroller.v3.controller;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
// view 의 전체 경로를 넣는것이 아니라 논리적인 이름만 넣어줌
    }
}