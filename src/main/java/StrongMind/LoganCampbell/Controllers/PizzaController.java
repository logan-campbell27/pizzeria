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

import StrongMind.LoganCampbell.Services.PizzaDataService;
import StrongMind.LoganCampbell.Services.ToppingsDataService;
import StrongMind.LoganCampbell.Models.PizzaModel;
import StrongMind.LoganCampbell.Models.ToppingModel;

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

	@GetMapping("/")
	public String index(Model model){
		List<PizzaModel> pizzas = pizzaDAO.getProducts();
		
		model.addAttribute("pizzas",pizzas);
		
		return "pizzas";
	}

	@GetMapping("/{search}")
	public String SearchForPizzas(@RequestParam(name="search", required=false) String search, Model model){

		List<PizzaModel> pizzas = pizzaDAO.searchProducts(search); 
		model.addAttribute("pizzas", pizzas);
		
		return "pizzas";
	}

	// new pizzas 
	@GetMapping("/new")
	public String newPizzas(Model model){
		model.addAttribute("pizza", new PizzaModel());
		model.addAttribute("toppings", toppingsDAO.getProducts());
		return "newPizza";
	}
	
	// process new pizzas
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

	// edit 
	@GetMapping("/edit/{id}")
	public String editPizzas(@PathVariable(value="id") Integer id, Model model){
		model.addAttribute("pizza", pizzaDAO.getById(id));
		model.addAttribute("toppings", toppingsDAO.getProducts());
		return "editPizza";
	}

	// process edit 
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

	// delete 
	@GetMapping("/delete/{id}")
	public String deletePizzas(@PathVariable(value = "id")Integer id, Model model){
		pizzaDAO.deleteOne(id);
		return "redirect:/pizzas/";

	}
}
