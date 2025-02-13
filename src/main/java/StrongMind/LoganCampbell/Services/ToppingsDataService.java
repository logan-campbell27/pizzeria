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
import StrongMind.LoganCampbell.Interfaces.ToppingRepositoryInterface;
import StrongMind.LoganCampbell.Models.PizzaModel;
import StrongMind.LoganCampbell.Models.ToppingEntity;
import StrongMind.LoganCampbell.Models.ToppingModel;

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
	
	
	@Override
	public ToppingModel getById(long id) {
		// TODO Auto-generated method stub
		ToppingEntity entity = toppingDatabase.findById(id).orElse(null);
		
		ToppingModel model = modelMapper.map(entity, ToppingModel.class);
		return model;
	}

	@Override
	public List<ToppingModel> getProducts() {
		// TODO Auto-generated method stub
		Iterable<ToppingEntity> entities = toppingDatabase.findAll();
		
		List<ToppingModel> orders = new ArrayList<ToppingModel>();
		for(ToppingEntity entity: entities) {
			orders.add(modelMapper.map(entity,  ToppingModel.class));
		}
		
		return orders;
	}

	@Override
	public List<ToppingModel> searchProducts(String searchTerm) {
		// TODO Auto-generated method stub
		Iterable<ToppingEntity> entities = toppingDatabase.findByNameContainingIgnoreCase(searchTerm);
		
		List<ToppingModel> orders = new ArrayList<ToppingModel>();
		for(ToppingEntity entity: entities) {
			orders.add(modelMapper.map(entity,  ToppingModel.class));
		}
		return orders;
	}

	@Override
	public long addOne(ToppingModel newTopping) {
		ToppingEntity entity = modelMapper.map(newTopping, ToppingEntity.class);
		entity.setId(null);
		
		if(checkForDuplicates(entity)){
			ToppingEntity result = toppingDatabase.save(entity);
			return result.getId();
		}
		return -1;
		
	}

	@Override
	public boolean deleteOne(long id) {
		// TODO Auto-generated method stub
		List<PizzaModel> pizzas = pizzaDao.getProducts();
		ToppingModel removedTopping = getById(id);

		//  Check if toppings need to be removed from Pizzas
		for(PizzaModel pm : pizzas){
			if (pm.getToppings().contains(removedTopping.getName())){
				pm.setToppings(pm.getToppings().replace(removedTopping.getName(), ""));
				pizzaDao.updateOne(pm.getId(), pm);
			}
		}
	
		toppingDatabase.deleteById(id);
		
		return true;
	}

	@Override
	public long updateOne(long idToUpdate, ToppingModel updateOrder) {

		ToppingEntity entity = modelMapper.map(updateOrder, ToppingEntity.class);
		
		List<PizzaModel> pizzas = pizzaDao.getProducts();
		ToppingModel removedTopping = getById(idToUpdate);

		//  Check if toppings need to be edited on Pizzas
		for(PizzaModel pm : pizzas){
			if (pm.getToppings().contains(removedTopping.getName())){
				pm.setToppings(pm.getToppings().replace(removedTopping.getName(), entity.getName()));
				pizzaDao.updateOne(pm.getId(), pm);
			}
		}

		if(checkForDuplicates(entity)){
			toppingDatabase.save(entity);
			return entity.getId();
		}
		return -1;
	}

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
