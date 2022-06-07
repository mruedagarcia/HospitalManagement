package hospital.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patients")
@XmlType(propOrder = { "patients"})

public class Patients implements Serializable{
	
	private static final long serialVersionUID = 8129060454653563009L;
	
	@XmlElement(name="Patient")
	private List<Patient> patients;

	public Patients(List<Patient> patients) {
		super();
		this.patients = patients;
	}
	
	public Patients() {
		super();
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patients);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patients other = (Patients) obj;
		return Objects.equals(patients, other.patients);
	}

	@Override
	public String toString() {
		return "Patients [patients=" + patients + "]";
	}
	
}
