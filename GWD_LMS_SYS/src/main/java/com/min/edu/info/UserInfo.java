package com.min.edu.info;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo implements UserDetails{

	private String username;
	private String password;
	private boolean enabled;
	private String authority;
	private Collection<GrantedAuthority> authorities;
	
	
	
	public UserInfo(String username, String password, boolean enabled, Collection<GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public void setAuthority(String authority) {
		while(this.authorities.size()!=0) {
			System.out.println(this.authorities.iterator());
		}
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authority="
				+ authority + ", authorities=" + authorities + "]";
	}
	
	
	
}
