package StrongMind.LoganCampbell.Interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import StrongMind.LoganCampbell.Models.PizzaEntity;

public interface PizzaRepositoryInterface extends CrudRepository<PizzaEntity, Long>{
	List<PizzaEntity> findByToppingsContainingIgnoreCase(String searchTerm);

}
