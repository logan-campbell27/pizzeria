package StrongMind;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

import StrongMind.Business.PizzaBusinessService;
import StrongMind.Business.ToppingsBusinessService;
import StrongMind.Interfaces.BusinessServiceInterface;
import StrongMind.Services.PizzaDataService;
import StrongMind.Services.ToppingsDataService;
import StrongMind.Interfaces.DataAccessInterface;
import StrongMind.Models.PizzaModel;
import StrongMind.Models.ToppingModel;

@Configuration
public class SpringConfig {
	
	@Bean(name="PizzaBusinessService", initMethod = "init", destroyMethod = "destroy")
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public BusinessServiceInterface getPizzaBusiness() {
		return new PizzaBusinessService();
	}
	@Bean(name="ToppingsBusinessService", initMethod = "init", destroyMethod = "destroy")
	@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public BusinessServiceInterface getToppingsBusiness() {
		return new ToppingsBusinessService();
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name="pizzaDAO")
	@RequestScope
	public DataAccessInterface<PizzaModel> getPizzaService(){
		return new PizzaDataService(dataSource);
	}

	@Bean(name="toppingsDAO")
	@RequestScope
	public DataAccessInterface<ToppingModel> getToppingsService(){
		return new ToppingsDataService(dataSource);
	}
	
}
