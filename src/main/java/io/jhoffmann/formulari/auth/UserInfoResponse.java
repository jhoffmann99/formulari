package io.jhoffmann.formulari.auth;

import java.util.List;

public class UserInfoResponse {
	private String token;
	private String email;
	private List<String> roles;

	public UserInfoResponse(String email, String token, List<String> roles) {
		this.email = email;
		this.token = token;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
}
