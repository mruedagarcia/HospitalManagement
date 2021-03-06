package ifaces;

import java.util.List;

import hospital.pojos.*;

public interface UserManager {

	public void disconnect();

	public void createUser(User u);

	public void deleteUser(User u);

	public void deleteRole(Role r);

	public void updateUser(User u,byte[] password);

	public void updateRole(Role r,String name);

	public Role getRole(String name);

	public List<Role> getRoles();

	/**
	 * 
	 * @param email
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(String email, String passwd);

}
