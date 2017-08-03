package at.usermanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Calling / Invoking Secure RESTful Web Service over HTTPS with JAX-RS in Java without Keystore & Truststore Information
// http://www.bhaveshthaker.com/24/calling-invoking-secure-restful-web-service-over-https-with-jax-rs-in-java-without-keystore-truststore-information/

@XmlRootElement(name = "user")
public class User implements Serializable {
	private int id;
	private String name;
	private String passwd;
	
	public User(){};
	
	public User(int id, String name, String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	@XmlElement
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	
}
