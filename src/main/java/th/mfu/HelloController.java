package th.mfu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello "+ name;
    }
    @GetMapping("/sum/{a}/{b}")
    int sum(@PathVariable int a,@PathVariable int b){
        return  (a+b);
    }

   
}
