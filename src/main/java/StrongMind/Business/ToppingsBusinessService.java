package StrongMind.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import StrongMind.Interfaces.DataAccessInterface;
import StrongMind.Interfaces.BusinessServiceInterface;
import StrongMind.Models.ToppingModel;

// This class works as middleware between the data access layer and controller layer
// This is to ensure added security as well as make future implementation simpler
public class ToppingsBusinessService implements BusinessServiceInterface<ToppingModel>{


	@Autowired
	DataAccessInterface<ToppingModel> toppingsDAO;
	
	@Override
	public void test() {
		System.out.println("The test method of the OrdersBusinessService appears to be working if you can see this.");
	}

	@Override
	public List<ToppingModel> getProducts() {
		return toppingsDAO.getProducts();
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
	public ToppingModel getById(int id) {
		return toppingsDAO.getById(id);
	}

	@Override
	public List<ToppingModel> searchProducts(String searchTerm) {
		return toppingsDAO.searchProducts(searchTerm);
	}

	@Override
	public long addOne(ToppingModel newOrder) {
		return toppingsDAO.addOne(newOrder);
	}

	@Override
	public boolean deleteOne(long id) {
		return toppingsDAO.deleteOne(id);
	}

	@Override
	public long updateOne(long idToUpdate, ToppingModel updateOrder) {
		return toppingsDAO.updateOne(idToUpdate, updateOrder);
	}

}
