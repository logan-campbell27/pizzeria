package StrongMind.Business;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import StrongMind.Interfaces.DataAccessInterface;
import StrongMind.Interfaces.BusinessServiceInterface;
import StrongMind.Models.PizzaModel;

// This class works as middleware between the data access layer and controller layer
// This is to ensure added security as well as make future implementation simpler
public class PizzaBusinessService implements BusinessServiceInterface<PizzaModel>{


	@Autowired
	DataAccessInterface<PizzaModel> pizzaDAO;
	
	@Override
	public void test() {
		System.out.println("The test method of the BusinessService appears to be working if you can see this.");
	}

	@Override
	public List<PizzaModel> getProducts() {
		return pizzaDAO.getProducts();
	}

	@Override
	public void init() {
		System.out.println("Init method called");
		
	}

	@Override
	public void destroy() {
		System.out.println("Destroy method called");
	}

	@Override
	public PizzaModel getById(int id) {
		return pizzaDAO.getById(id);
	}

	@Override
	public List<PizzaModel> searchProducts(String searchTerm) {
		return pizzaDAO.searchProducts(searchTerm);
	}

	@Override
	public long addOne(PizzaModel newOrder) {
		return pizzaDAO.addOne(newOrder);
	}

	@Override
	public boolean deleteOne(long id) {
		return pizzaDAO.deleteOne(id);
	}

	@Override
	public long updateOne(long idToUpdate, PizzaModel updateOrder) {
		return pizzaDAO.updateOne(idToUpdate, updateOrder);
	}

}
