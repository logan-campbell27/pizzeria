package StrongMind.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import StrongMind.Models.ToppingModel;
import StrongMind.Services.ToppingsDataService;

// Controller will link to all topping related pages and functions
// View, edit, delete, create

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

	// Default view page of all toppings
	@GetMapping("/")
	public String index(Model model){
		// List<ToppingModel> orders = ordersService.getOrders();
		List<ToppingModel> toppings = toppingsDAO.getProducts();

		model.addAttribute("toppings",toppings);
		
		return "toppings";
	}


	// Filtered view page by search term
	@GetMapping("/{search}")
	public String SearchForToppings(@RequestParam(name="search", required=false) String search, Model model){

		List<ToppingModel> toppings = toppingsDAO.searchProducts(search); 
		model.addAttribute("toppings", toppings);
		
		return "toppings";
	}



	// link to new order form 
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

	// link to edit form
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

	// Delete button endpoint to delete topping based on ID and stay on view form
	@GetMapping("/delete/{id}")
	public String deleteToppings(@PathVariable(value = "id")Integer id, Model model){
		toppingsDAO.deleteOne(id);
		return "redirect:/toppings/";

	}
}
