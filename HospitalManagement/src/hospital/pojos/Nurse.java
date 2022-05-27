package hospital.pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Nurse")
@XmlType(propOrder = { "name", "email", "patients"}) 

public class Nurse implements Serializable {

	private static final long serialVersionUID = -888446701244086278L;
    @XmlElement
	private String name;
    @XmlAttribute
	private Integer id;
<<<<<<< HEAD
    @XmlElement
	private String email;
    @XmlElement(name = "Patient")
=======
>>>>>>> branch 'master' of https://github.com/mruedagarcia/HospitalManagement
	private List<Patient> patients;

	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
		patients = new ArrayList<Patient>();
	}

	public Nurse(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
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
