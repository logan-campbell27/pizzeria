package StrongMind.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pizzas")
public class PizzaEntity {
   
	@Id
	@Column("id")
	Long id;
	
	@Column("creator")
	String creator;
	
	@Column("name")
	String name;
	
	@Column("toppings")
	String toppings;
	
	
	public PizzaEntity() {
		this.id = 0L;
		this.creator = "";
		this.name ="";
		this.toppings = "";
	}
    public PizzaEntity(Long id, String creator, String name, String toppings) {
		super();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.toppings = toppings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToppings() {
		return toppings;
	}

	public void setToppings(String toppings) {
		this.toppings = toppings;
	}

	@Override
	public String toString() {
		return "ToppingModel [id=" + id + ", creator =" + creator + ", name =" + name + ", toppings =" + toppings
				+ "]";
	}
}
