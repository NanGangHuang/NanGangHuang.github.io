package springbootproject.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springbootproject.springboot.service.TestService;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    TestService testService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return testService.test();
    }

}
