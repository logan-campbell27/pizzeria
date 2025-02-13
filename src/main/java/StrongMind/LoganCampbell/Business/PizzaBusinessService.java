package StrongMind.LoganCampbell.Business;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import StrongMind.LoganCampbell.Interfaces.DataAccessInterface;
import StrongMind.LoganCampbell.Interfaces.BusinessServiceInterface;
import StrongMind.LoganCampbell.Models.PizzaModel;

public class PizzaBusinessService implements BusinessServiceInterface<PizzaModel>{


	@Autowired
	DataAccessInterface<PizzaModel> pizzaDAO;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("The test method of the BusinessService appears to be working if you can see this.");
	}

	@Override
	public List<PizzaModel> getProducts() {
		// TODO Auto-generated method stub
		return pizzaDAO.getProducts();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Init method called");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy method called");
	}

	@Override
	public PizzaModel getById(int id) {
		// TODO Auto-generated method stub
		return pizzaDAO.getById(id);
	}

	@Override
	public List<PizzaModel> searchProducts(String searchTerm) {
		// TODO Auto-generated method stub
		return pizzaDAO.searchProducts(searchTerm);
	}

	@Override
	public long addOne(PizzaModel newOrder) {
		// TODO Auto-generated method stub
		return pizzaDAO.addOne(newOrder);
	}

	@Override
	public boolean deleteOne(long id) {
		// TODO Auto-generated method stub
		return pizzaDAO.deleteOne(id);
	}

	@Override
	public long updateOne(long idToUpdate, PizzaModel updateOrder) {
		// TODO Auto-generated method stub
		return pizzaDAO.updateOne(idToUpdate, updateOrder);
	}

}
