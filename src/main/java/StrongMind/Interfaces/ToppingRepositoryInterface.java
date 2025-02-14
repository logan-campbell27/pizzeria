package StrongMind.Interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import StrongMind.Models.ToppingEntity;

// Interface provides overload for a built in function to get a list of an entity
// This is used for the search functionality to get a list of items given a search term

public interface ToppingRepositoryInterface extends CrudRepository<ToppingEntity, Long>{
	List<ToppingEntity> findByNameContainingIgnoreCase(String searchTerm);

}
