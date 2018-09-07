package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.inject.persist.Transactional;

@Entity(name="Reg")
//@Transactional
public class Registration 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="email")
	private String email;
	
	public Registration() {}
	
	public String getId() { return id; }
	public void setId(String id) { this.id=id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name=name; }
	
	public String getPass() { return pass;}
	public void setPass(String pass) { this.pass=pass;}
	
	public String getEmail() { return email;}
	public void setEmail(String email) {this.email=email;}
}
