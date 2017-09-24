package at.webservice.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import at.usermanagement.User;

public class WebServiceTester {
	private Client client;
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	private String REST_SERVICE_URL = "http://localhost:8080/FileTresor/rest/UserService/users";
	private static final String SUCCESS_RESULT = "<result>success</result>";

	public WebServiceTester() {
		client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		WebServiceTester tester = new WebServiceTester();
		tester.testGetAllUsers();
		tester.testGetUser(1);
		tester.addUser();
		tester.updateUser();
		tester.deleteUser(9719465);
	}

	private void testGetAllUsers() {
		GenericType<List<User>> list = new GenericType<List<User>>() {
		};
		List<User> users = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).get(list);
		String result = PASS;
		if (users.isEmpty()) {
			result = FAIL;
		}
		System.out.println("Test case: testGetAllUsers, Result: " + result);
	}

	private void testGetUser(int id) {
		User user = client.target(REST_SERVICE_URL).path("/" + id).resolveTemplate("id", id)
				.request(MediaType.APPLICATION_XML).get(User.class);
		String result = FAIL;
		if (id == user.getId()) {
			result = PASS;
		}
		System.out.println("Test case: testGetUser, Result: " + result);
	}

	private void addUser() {
		Form form = new Form();
		form.param("name", "java");
		form.param("passwd", "RL7");

		String callResult = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),String.class);
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case: addUser, Result: " + result);
	}
	
	private void updateUser() {
		Form form = new Form();
		form.param("id", "9719465");
		form.param("name", "javaRL7");
		form.param("passwd", "RL7");
		
		String callResult = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case: updateUser, Result: " + result);
	}
	
	private void deleteUser(int id) {
		String callResult = client.target(REST_SERVICE_URL).path("/" + id).resolveTemplate("id", id).request(MediaType.APPLICATION_XML).delete(String.class);
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case: deleteUser, Result: " + result);
	}

}
