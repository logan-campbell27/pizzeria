package StrongMind.LoganCampbell.Interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import StrongMind.LoganCampbell.Models.ToppingEntity;

public interface ToppingRepositoryInterface extends CrudRepository<ToppingEntity, Long>{
	List<ToppingEntity> findByNameContainingIgnoreCase(String searchTerm);

}
