package hospital.pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nurse implements Serializable {

	private static final long serialVersionUID = -888446701244086278L;

	private String name;
	private Integer id;
	private String email;
	private List<Patient> patients;

	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}

	public Nurse(String name, Integer id, String email) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
	}
	
	public Nurse(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Nurse(String name2) {
		this.name = name2;
	}

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
