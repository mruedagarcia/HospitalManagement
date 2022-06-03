package hospital.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctors")
@XmlType(propOrder = { "doctors"})
public class Doctors {
    @XmlElement(name="Doctor")
	private List<Doctor> doctors;
	
}
