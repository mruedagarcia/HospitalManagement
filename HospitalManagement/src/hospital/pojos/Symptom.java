package hospital.pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="symptoms")
public class Symptom implements Serializable {
	
	private static final long serialVersionUID = -9137519159299611555L;
	private String name;
	private Integer id;
	//many to many relationship
	private List<Patient> patients;
	private List<Disease> diseases;
	
	public Symptom() {
		super();
		patients = new ArrayList<Patient>();
	}

	public Symptom(String name2, Integer id2) {
		this.name = name2;
		this.id = id2;
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
		Symptom other = (Symptom) obj;
		return Objects.equals(id, other.id);
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

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Symptom [name=" + name + ", id=" + id + ", patients=" + patients + "]";
	}
	

}
