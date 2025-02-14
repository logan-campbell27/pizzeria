package StrongMind.Models;

public class ToppingModel {

	private int id = 0;
	String creator = "";
	String name ="";
	String description = "";

	public ToppingModel(){

	}
	
	public ToppingModel(int id, String creator, String name, String description) {
		super();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ToppingModel [id=" + id + ", creator=" + creator + ", name=" + name + ", description=" + description
				+ "]";
	}
	
}
