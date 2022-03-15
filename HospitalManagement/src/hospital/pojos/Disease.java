package hospital.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disease implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6837166362143027533L;
	private Integer id;
	private String name;
	//many to many relationship
	private List<Patient> patients;
	
	public Disease() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", patients=" + patients + "]";
	}
	
}
