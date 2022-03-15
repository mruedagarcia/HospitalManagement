package hospital.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nurse implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -888446701244086278L;
	private String name;
	private Integer id;
	//one to many relationship
	private List<Patient> patients;
	
	
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurse other = (Nurse) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Nurse [name=" + name + ", id=" + id + ", patients=" + patients + "]";
	}

	

}
