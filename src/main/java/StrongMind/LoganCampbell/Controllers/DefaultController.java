package StrongMind.LoganCampbell.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import StrongMind.LoganCampbell.Models.PizzaModel;
import StrongMind.LoganCampbell.Services.PizzaDataService;
import StrongMind.LoganCampbell.Services.ToppingsDataService;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
	public DefaultController(){
		super();
	}

    @GetMapping("/")
	public String index(Model model){
		return "home";
	}
    
}
