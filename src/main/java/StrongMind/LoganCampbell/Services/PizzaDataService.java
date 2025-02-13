package StrongMind.LoganCampbell.Services;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import StrongMind.LoganCampbell.Interfaces.DataAccessInterface;
import StrongMind.LoganCampbell.Interfaces.PizzaRepositoryInterface;
import StrongMind.LoganCampbell.Models.PizzaEntity;
import StrongMind.LoganCampbell.Models.PizzaModel;
import StrongMind.LoganCampbell.Models.ToppingEntity;
import StrongMind.LoganCampbell.Models.ToppingModel;

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
	
	
	@Override
	public PizzaModel getById(long id) {
		// TODO Auto-generated method stub
		PizzaEntity entity = pizzaRepository.findById(id).orElse(null);
		
		PizzaModel model = modelMapper.map(entity, PizzaModel.class);
		return model;
	}

	@Override
	public List<PizzaModel> getProducts() {
		// TODO Auto-generated method stub
		Iterable<PizzaEntity> entities = pizzaRepository.findAll();
		
		List<PizzaModel> orders = new ArrayList<PizzaModel>();
		for(PizzaEntity entity: entities) {
			orders.add(modelMapper.map(entity,  PizzaModel.class));
		}
		
		return orders;
	}

	@Override
	public List<PizzaModel> searchProducts(String searchTerm) {
		// TODO Auto-generated method stub
		Iterable<PizzaEntity> entities = pizzaRepository.findByToppingsContainingIgnoreCase(searchTerm);
		
		List<PizzaModel> pizzas = new ArrayList<PizzaModel>();
		for(PizzaEntity entity: entities) {
			pizzas.add(modelMapper.map(entity,  PizzaModel.class));
		}
		return pizzas;
	}

	@Override
	public long addOne(PizzaModel newOrder) {
		// TODO Auto-generated method stub
		PizzaEntity entity = modelMapper.map(newOrder, PizzaEntity.class);
		entity.setToppings(entity.getToppings().replace(",", ", "));
		entity.setId(null);
		if(checkForDuplicates(entity)){
			PizzaEntity result = pizzaRepository.save(entity);
			return result.getId();
		}
		return -1;
	}

	@Override
	public boolean deleteOne(long id) {
		// TODO Auto-generated method stub
		pizzaRepository.deleteById(id);
		return true;
	}

	@Override
	public long updateOne(long idToUpdate, PizzaModel updateOrder) {
		// TODO Auto-generated method stub
		PizzaEntity entity = modelMapper.map(updateOrder, PizzaEntity.class);
		entity.setToppings(entity.getToppings().replace(",", ", "));
		if(checkForDuplicates(entity)){
			pizzaRepository.save(entity);
			return entity.getId();
		}
		return -1;
	}

	public boolean checkForDuplicates(PizzaEntity newPizza){
		List<PizzaModel> CurrentPizzas = getProducts();

		// Check if topping already exists (checking for name, non case sensitive)
		for(PizzaModel pm : CurrentPizzas){
			if(pm.getToppings().equals(newPizza.getToppings()) ){
				return false;
			}
		}

		return true;
	}

}
