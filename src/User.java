public class User {
private String userid;
private String fullname;
private String role;
private String email;
private String password;


public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public User(String userid, String fullname, String role, String email, String password) {
	super();
	
	this.userid = userid;
	this.fullname = fullname;
	this.role = role;
	this.email = email;
	this.password = password;
}
public Object [] returnAsObj() {
	
	Object[] Ob = new Object[5];
	Ob[0] = this.userid;
	Ob[1] = this.fullname;
	Ob[2] = this.role;
	Ob[3] = this.email;
	Ob[4] = this.password;
	return Ob;
	
}

}
