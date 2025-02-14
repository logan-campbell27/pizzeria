package StrongMind.Services;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StrongMind.Interfaces.DataAccessInterface;
import StrongMind.Interfaces.PizzaRepositoryInterface;
import StrongMind.Models.PizzaEntity;
import StrongMind.Models.PizzaModel;

// Service that performs logic and maps models and entities for Pizzas
// Performs database interaction

@Primary
@Repository
public class PizzaDataService implements DataAccessInterface<PizzaModel> {
	
	@Autowired
	private PizzaRepositoryInterface pizzaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	ModelMapper modelMapper = new ModelMapper();
	

	public PizzaDataService(DataSource dataSource) {
		super();
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	// Gets a singular pizza using the pizzas ID
	@Override
	public PizzaModel getById(long id) {
		PizzaEntity entity = pizzaRepository.findById(id).orElse(null);
		PizzaModel model = modelMapper.map(entity, PizzaModel.class);
		return model;
	}

	// Gets all pizzas
	@Override
	public List<PizzaModel> getProducts() {
		Iterable<PizzaEntity> entities = pizzaRepository.findAll();
		
		List<PizzaModel> orders = new ArrayList<PizzaModel>();
		for(PizzaEntity entity: entities) {
			orders.add(modelMapper.map(entity,  PizzaModel.class));
		}
		
		return orders;
	}

	// Returns a filtered list of pizzas
	// Searches for pizzas who's toppings contain the search term
	@Override
	public List<PizzaModel> searchProducts(String searchTerm) {

		// Method to automatically filter
		Iterable<PizzaEntity> entities = pizzaRepository.findByToppingsContainingIgnoreCase(searchTerm);
		
		// Creates list and will then map entities to it
		List<PizzaModel> pizzas = new ArrayList<PizzaModel>();
		for(PizzaEntity entity: entities) {
			pizzas.add(modelMapper.map(entity,  PizzaModel.class));
		}
		return pizzas;
	}

	// Creates a new pizza
	@Override
	public long addOne(PizzaModel newPizza) {

		// Maps the new pizza model to an entity
		PizzaEntity entity = modelMapper.map(newPizza, PizzaEntity.class);

		// Format the toppings string
		entity.setToppings(entity.getToppings().replace(",", ", "));

		// Set ID to null to it auto increments when added to the database
		entity.setId(null);

		// Make sure pizza doesn't already exist
		// If it is unique, it will be saved and the ID will be returned
		if(checkForDuplicates(entity)){
			PizzaEntity result = pizzaRepository.save(entity);
			return result.getId();
		}

		// Return if the pizza already exists
		return -1;
	}

	@Override
	public boolean deleteOne(long id) {
		pizzaRepository.deleteById(id);
		return true;
	}

	// Updates an existing entity
	@Override
	public long updateOne(long idToUpdate, PizzaModel updateOrder) {

		// Map the model to an entity so it can interact w the database
		PizzaEntity entity = modelMapper.map(updateOrder, PizzaEntity.class);

		// Format the toppings string
		entity.setToppings(entity.getToppings().replace(",", ", "));

		// Check if a pizza with those toppings already exists
		// Will save if its new and return the ID
		if(checkForDuplicates(entity)){
			pizzaRepository.save(entity);
			return entity.getId();
		}

		// Return if the pizza already exists
		return -1;
	}

	// Helper function to make sure the pizza being created/edited doesn't already exist
	public boolean checkForDuplicates(PizzaEntity newPizza){
		
		// Gets all of the existing pizzas
		List<PizzaModel> CurrentPizzas = getProducts();

		// Check if pizza w these toppings already exists
		for(PizzaModel pm : CurrentPizzas){
			if(pm.getToppings().equals(newPizza.getToppings()) ){
				return false;
			}
		}

		return true;
	}

}
