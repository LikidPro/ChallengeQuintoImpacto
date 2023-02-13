package ChallengeBackend.QuintoImpacto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasControlador {
    @GetMapping("/")
    public String index(){
        return "index.html";
    }

}
