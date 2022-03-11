package hospital.pojos;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1080532239335630835L;
	private Integer id;
	private String email;
	private LocalDate dob;//??
	private Integer phone;
	private String name;
	private String status;

}
