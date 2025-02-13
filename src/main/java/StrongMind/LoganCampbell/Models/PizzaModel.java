package StrongMind.LoganCampbell.Models;

public class PizzaModel {

	private int id = 0;
	String creator = "";
	String name ="";
	String toppings = "";

	public PizzaModel(){

	}
	
	public PizzaModel(int id, String creator, String name, String toppings) {
		super();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.toppings = toppings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setToppings (String toppings) {
		this.toppings = toppings;
	}

	@Override
	public String toString() {
		return "PizzaModel [id=" + id + ", creator=" + creator + ", name=" + name + ", toppings=" + toppings
				+ "]";
	}
	
}
