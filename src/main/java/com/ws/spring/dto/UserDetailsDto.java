package com.ws.spring.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ws.spring.model.UserProfile;

public class UserDetailsDto extends UserProfile implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2755202268634259707L;

	public UserDetailsDto(UserProfile userProfile) {
		super(userProfile);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRole().getRoleName());
		List<SimpleGrantedAuthority> authotityList = new ArrayList<>(1);
		authotityList.add(authority);
		return authotityList;
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
