package StrongMind.LoganCampbell.Interfaces;

import java.util.List;

public interface DataAccessInterface<T> {
	public T getById(long id);
	public List<T> getProducts();
	public List<T> searchProducts(String searchTerm);
	public long addOne(T newOrder);
	public boolean deleteOne(long id);
	public long updateOne(long idToUpdate, T updateOrder);
}
