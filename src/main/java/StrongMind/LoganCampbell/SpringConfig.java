package StrongMind.LoganCampbell;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

import StrongMind.LoganCampbell.Business.PizzaBusinessService;
import StrongMind.LoganCampbell.Business.ToppingsBusinessService;
import StrongMind.LoganCampbell.Interfaces.BusinessServiceInterface;
import StrongMind.LoganCampbell.Services.PizzaDataService;
import StrongMind.LoganCampbell.Services.ToppingsDataService;
import StrongMind.LoganCampbell.Interfaces.DataAccessInterface;
import StrongMind.LoganCampbell.Models.PizzaModel;
import StrongMind.LoganCampbell.Models.ToppingModel;

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
