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
@XmlRootElement(name = "Nurses")
@XmlType(propOrder = { "nurses"})

public class Nurses implements Serializable {
	
	private static final long serialVersionUID = -4328101395200684385L;
    
	@XmlElement(name="Nurse")
	private List<Nurse> nurses;

	public Nurses(List<Nurse> nurses) {
		super();
		this.nurses = nurses;
	}

	public Nurses() {
		super();
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nurses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurses other = (Nurses) obj;
		return Objects.equals(nurses, other.nurses);
	}

	@Override
	public String toString() {
		return "Nurses [nurses=" + nurses + "]";
	}
	
	
	
	
}
