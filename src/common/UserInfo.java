package common;
/*
 * 用户信息
 */
public class UserInfo {

	private int id;
	private String name;
	private String password;
	private String email;
	private String permission;
	
	public UserInfo(){}
	public UserInfo(String name, String password, String email){
		this.name = name;
		this.password = password;
		this.email= email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}	
	
}
