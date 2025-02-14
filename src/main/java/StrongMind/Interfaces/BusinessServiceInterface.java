package StrongMind.Interfaces;

import java.util.List;

// Interface provides shared functions for business services

public interface BusinessServiceInterface<T> {
	public void test();
	public List<T> getProducts();
	public void init();
	public void destroy();
	public T getById(int id);
	public List<T> searchProducts(String searchTerm);
	public long addOne(T newOrder);
	public boolean deleteOne(long id);
	public long updateOne(long idToUpdate, T updateOrder);
}
