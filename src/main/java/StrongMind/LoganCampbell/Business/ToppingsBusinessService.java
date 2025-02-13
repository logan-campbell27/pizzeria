package StrongMind.LoganCampbell.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import StrongMind.LoganCampbell.Interfaces.DataAccessInterface;
import StrongMind.LoganCampbell.Interfaces.BusinessServiceInterface;
import StrongMind.LoganCampbell.Models.ToppingModel;

public class ToppingsBusinessService implements BusinessServiceInterface<ToppingModel>{


	@Autowired
	DataAccessInterface<ToppingModel> toppingsDAO;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("The test method of the OrdersBusinessService appears to be working if you can see this.");
	}

	@Override
	public List<ToppingModel> getProducts() {
		// TODO Auto-generated method stub
		return toppingsDAO.getProducts();
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
	public ToppingModel getById(int id) {
		// TODO Auto-generated method stub
		return toppingsDAO.getById(id);
	}

	@Override
	public List<ToppingModel> searchProducts(String searchTerm) {
		// TODO Auto-generated method stub
		return toppingsDAO.searchProducts(searchTerm);
	}

	@Override
	public long addOne(ToppingModel newOrder) {
		// TODO Auto-generated method stub
		return toppingsDAO.addOne(newOrder);
	}

	@Override
	public boolean deleteOne(long id) {
		// TODO Auto-generated method stub
		return toppingsDAO.deleteOne(id);
	}

	@Override
	public long updateOne(long idToUpdate, ToppingModel updateOrder) {
		// TODO Auto-generated method stub
		return toppingsDAO.updateOne(idToUpdate, updateOrder);
	}

}
