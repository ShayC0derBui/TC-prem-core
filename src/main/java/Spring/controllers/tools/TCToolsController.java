package Spring.controllers.tools;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tools")
public class TCToolsController {

    @GetMapping("/ping")
    public String returnPong(){
        return "message: pong";
    }


}
