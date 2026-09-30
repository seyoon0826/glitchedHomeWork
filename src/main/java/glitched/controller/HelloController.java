package glitched.controller;
import glitched.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService service;


    @GetMapping("/")
    public String hello(){
        return service.hello();
        // 10.150.3.213
    }
}
