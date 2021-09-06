package ua.hehmde.kvjewelry.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hehmde.kvjewelry.util.UserSession;

@Controller
public class MainController implements ErrorController {
    @GetMapping(value = {"/","/home"})
    public String mainPage(Model model){
        if (UserSession.getCurrentUserRole().equals("ADMIN")){
            return "redirect:/admin-panel";
        }
        model.addAttribute("role", UserSession.getCurrentUserRole());
        return "home";
    }

    @GetMapping(value = {"/error"})
    public String errorPage(){
        return "notExist";
    }

    @GetMapping(value = {"/no-access"})
    public String noAccessPage(){
        return "noAccess";
    }
}
