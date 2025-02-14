package StrongMind.Interfaces;

import java.util.List;

// Interface provides shared functions for the services interacting with databases

public interface DataAccessInterface<T> {
	public T getById(long id);
	public List<T> getProducts();
	public List<T> searchProducts(String searchTerm);
	public long addOne(T newOrder);
	public boolean deleteOne(long id);
	public long updateOne(long idToUpdate, T updateOrder);
}
