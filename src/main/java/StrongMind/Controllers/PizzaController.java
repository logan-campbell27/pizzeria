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

import StrongMind.Services.PizzaDataService;
import StrongMind.Services.ToppingsDataService;
import StrongMind.Models.PizzaModel;

// This controller will link to all pizza related pages and functions
// View, edit, delete, and add

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaDataService pizzaDAO;

	@Autowired
	private ToppingsDataService toppingsDAO;

	@Autowired
	public PizzaController(PizzaDataService pds, ToppingsDataService tds){
		super();
		this.pizzaDAO = pds;
		this.toppingsDAO = tds;
	}

	// Default view page
	@GetMapping("/")
	public String index(Model model){
		List<PizzaModel> pizzas = pizzaDAO.getProducts();
		
		model.addAttribute("pizzas",pizzas);
		
		return "pizzas";
	}

	// View page with filtered list of pizzas
	@GetMapping("/{search}")
	public String SearchForPizzas(@RequestParam(name="search", required=false) String search, Model model){

		List<PizzaModel> pizzas = pizzaDAO.searchProducts(search); 
		model.addAttribute("pizzas", pizzas);
		
		return "pizzas";
	}

	// New pizza form
	@GetMapping("/new")
	public String newPizzas(Model model){
		model.addAttribute("pizza", new PizzaModel());
		model.addAttribute("toppings", toppingsDAO.getProducts());
		return "newPizza";
	}
	
	// endpoint to process new pizzas being created
	@PostMapping("/processNew")
	public String processNew(PizzaModel pizzas, Model model){
		

		if(pizzaDAO.addOne(pizzas) != -1){
			return "redirect:/pizzas/";
		}

		String error = "This pizza already exists";
		model.addAttribute("toppings", toppingsDAO.getProducts());
		model.addAttribute("pizza", new PizzaModel());
		model.addAttribute("error",  error);
		return "newPizza";
	}

	// edit pizza form 
	@GetMapping("/edit/{id}")
	public String editPizzas(@PathVariable(value="id") Integer id, Model model){
		model.addAttribute("pizza", pizzaDAO.getById(id));
		model.addAttribute("toppings", toppingsDAO.getProducts());
		return "editPizza";
	}

	// process edited pizzas
	@PostMapping("edit/processEdit")
	public String processEdit(PizzaModel pizza, Model model){

		if(pizzaDAO.updateOne(pizza.getId(), pizza) != -1){
			return "redirect:/pizzas/";
		}

		String error = "This pizza already exists";
		model.addAttribute("pizza", pizza);
		model.addAttribute("error",  error);
		model.addAttribute("toppings", toppingsDAO.getProducts());
		return "editPizza";

	}

	// process delete button click to delete pizza 
	@GetMapping("/delete/{id}")
	public String deletePizzas(@PathVariable(value = "id")Integer id, Model model){
		pizzaDAO.deleteOne(id);
		return "redirect:/pizzas/";

	}
}
