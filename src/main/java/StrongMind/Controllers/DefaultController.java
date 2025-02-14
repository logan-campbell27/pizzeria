package StrongMind.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// This controller will link to the home page
// Would eventually contain login w the landing page

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
	public DefaultController(){
		super();
	}

	// Default home page
    @GetMapping("/")
	public String index(Model model){
		return "home";
	}
    
}
