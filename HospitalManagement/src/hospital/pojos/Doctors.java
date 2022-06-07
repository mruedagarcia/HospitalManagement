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
@XmlRootElement(name = "Doctors")
@XmlType(propOrder = { "doctors"})

public class Doctors implements Serializable{
	
	private static final long serialVersionUID = -8652012355653969986L;
	
    @XmlElement(name="Doctor")
	private List<Doctor> doctors;

	public Doctors(List<Doctor> doctors) {
		super();
		this.doctors = doctors;
	}
    
    public Doctors() {
    	super();
    }

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctors other = (Doctors) obj;
		return Objects.equals(doctors, other.doctors);
	}

	@Override
	public String toString() {
		return "Doctors [doctors=" + doctors + "]";
	}
    
    
	
}
