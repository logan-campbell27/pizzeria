package StrongMind.LoganCampbell.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("toppings")
public class ToppingEntity {
   
	@Id
	@Column("id")
	Long id;
	
	@Column("creator")
	String creator;
	
	@Column("name")
	String name;
	
	@Column("description")
	String description;
	
	
	public ToppingEntity() {
		this.id = 0L;
		this.creator = "";
		this.name ="";
		this.description = "";
	}
    public ToppingEntity(Long id, String creator, String name, String description) {
		super();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ToppingModel [id=" + id + ", creator =" + creator + ", name =" + name + ", description =" + description
				+ "]";
	}
}
