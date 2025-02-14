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
import StrongMind.Interfaces.ToppingRepositoryInterface;
import StrongMind.Models.PizzaModel;
import StrongMind.Models.ToppingEntity;
import StrongMind.Models.ToppingModel;

// Service that performs logic and maps models and entities for toppings
// Performs database interaction

@Primary
@Repository
public class ToppingsDataService implements DataAccessInterface<ToppingModel> {
	
	@Autowired
	private ToppingRepositoryInterface toppingDatabase;

	@Autowired
	private PizzaDataService pizzaDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	ModelMapper modelMapper = new ModelMapper();
	

	public ToppingsDataService(DataSource dataSource) {
		super();
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	// Gets a singular topping based on ID
	@Override
	public ToppingModel getById(long id) {
		ToppingEntity entity = toppingDatabase.findById(id).orElse(null);
		
		ToppingModel model = modelMapper.map(entity, ToppingModel.class);
		return model;
	}

	// Gets all toppings
	@Override
	public List<ToppingModel> getProducts() {
		Iterable<ToppingEntity> entities = toppingDatabase.findAll();
		
		List<ToppingModel> orders = new ArrayList<ToppingModel>();
		for(ToppingEntity entity: entities) {
			orders.add(modelMapper.map(entity,  ToppingModel.class));
		}
		
		return orders;
	}
	
	// Returns a filtered list of toppings
	// Searches for toppings who's name contain the search term
	@Override
	public List<ToppingModel> searchProducts(String searchTerm) {
		Iterable<ToppingEntity> entities = toppingDatabase.findByNameContainingIgnoreCase(searchTerm);
		
		List<ToppingModel> orders = new ArrayList<ToppingModel>();
		for(ToppingEntity entity: entities) {
			orders.add(modelMapper.map(entity,  ToppingModel.class));
		}
		return orders;
	}

	// Creates a new topping
	@Override
	public long addOne(ToppingModel newTopping) {

		// Maps the new topping model to an entity
		ToppingEntity entity = modelMapper.map(newTopping, ToppingEntity.class);

		// Set ID to null to it auto increments when added to the database
		entity.setId(null);
		
		// Make sure topping doesn't already exist
		// If it is unique, it will be saved and the ID will be returned
		if(checkForDuplicates(entity)){
			ToppingEntity result = toppingDatabase.save(entity);
			return result.getId();
		}

		// Return if the topping already exists
		return -1;
		
	}

	// Deletes a topping
	// Also checks if a pizza needs to be updated
	@Override
	public boolean deleteOne(long id) {

		// Gets all the pizzas
		List<PizzaModel> pizzas = pizzaDao.getProducts();

		// Finds what topping is being removed
		ToppingModel removedTopping = getById(id);

		//  Check if toppings need to be removed from Pizzas
		for(PizzaModel pm : pizzas){

			if (pm.getToppings().contains(removedTopping.getName())){

				// If the pizza contains the topping, remove it and then update the pizza
				pm.setToppings(pm.getToppings().replace(removedTopping.getName(), ""));
				pizzaDao.updateOne(pm.getId(), pm);
			}
		}

		// Delete the topping
		toppingDatabase.deleteById(id);
		
		return true;
	}

	@Override
	public long updateOne(long idToUpdate, ToppingModel updateOrder) {

		// Map the model to an entity so it can interact w the database
		ToppingEntity entity = modelMapper.map(updateOrder, ToppingEntity.class);
		
		// Gets all the pizzas
		List<PizzaModel> pizzas = pizzaDao.getProducts();

		// Finds what topping is being edited
		ToppingModel editedTopping = getById(idToUpdate);

		// Check for duplicates first
		// Ensures case sensitivity isn't violated
		if(checkForDuplicates(entity)){
			toppingDatabase.save(entity);

			// After saving it, edit pizzas to reflect the new topping
			for(PizzaModel pm : pizzas){
				if (pm.getToppings().contains(editedTopping.getName())){
	
					// Change the toppings name to match the new one, then update it
					pm.setToppings(pm.getToppings().replace(editedTopping.getName(), entity.getName()));
					pizzaDao.updateOne(pm.getId(), pm);
				}
			}
			return entity.getId();
		}

		return -1;
	}

	// Helper function to make sure topping doesn't already exist
	public boolean checkForDuplicates(ToppingEntity newTopping){
		List<ToppingModel> CurrentToppings = getProducts();

		// Check if topping already exists (checking for name, non case sensitive)
		for(ToppingModel tm : CurrentToppings){
			if(tm.getName().toLowerCase().equals(newTopping.getName().toLowerCase())){
				return false;
			}
		}

		return true;
	}

}
