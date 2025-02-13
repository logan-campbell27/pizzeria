package StrongMind.LoganCampbell.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import StrongMind.LoganCampbell.Models.ToppingModel;
import StrongMind.LoganCampbell.Services.ToppingsDataService;

@Controller
@RequestMapping("/toppings")
public class ToppingsController {

	@Autowired
	private ToppingsDataService toppingsDAO;

	@Autowired
	public ToppingsController(ToppingsDataService s){
		super();
		this.toppingsDAO = s;
	}

	@GetMapping("/")
	public String index(Model model){
		// List<ToppingModel> orders = ordersService.getOrders();
		List<ToppingModel> toppings = toppingsDAO.getProducts();

		model.addAttribute("toppings",toppings);
		
		return "toppings";
	}

	@GetMapping("/{search}")
	public String SearchForToppings(@RequestParam(name="search", required=false) String search, Model model){

		List<ToppingModel> toppings = toppingsDAO.searchProducts(search); 
		model.addAttribute("toppings", toppings);
		
		return "toppings";
	}



	// new order 
	@GetMapping("/new")
	public String newOrder(Model model){
		model.addAttribute("topping", new ToppingModel());
		return "newTopping";
	}
	
	// process new order
	@PostMapping("/processNew")
	public String processNew(ToppingModel topping, Model model){
		
		if(toppingsDAO.addOne(topping) != -1){
			return "redirect:/toppings/";
		}

		String error = "This topping already exists";
		model.addAttribute("topping", new ToppingModel());
		model.addAttribute("error",  error);
		return "newTopping";
	}

	// edit 
	@GetMapping("/edit/{id}")
	public String editTopping(@PathVariable(value="id") Integer id, Model model){
		model.addAttribute("topping", toppingsDAO.getById(id));
		return "editTopping";
	}

	// process edit 
	@PostMapping("edit/processEdit")
	public String processEdit(ToppingModel topping, Model model){

		if(toppingsDAO.updateOne(topping.getId(), topping) != -1){
			return "redirect:/toppings/";
		}

		String error = "This topping already exists";
		model.addAttribute("topping", topping);
		model.addAttribute("error",  error);
		return "editTopping";
	}

	@GetMapping("/delete/{id}")
	public String deleteToppings(@PathVariable(value = "id")Integer id, Model model){
		toppingsDAO.deleteOne(id);
		return "redirect:/toppings/";

	}
}
