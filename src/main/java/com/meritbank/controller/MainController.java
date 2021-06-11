package com.meritbank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.model.User;
import com.meritbank.security.JwtUtil;
import com.meritbank.security.model.AuthenticationRequest;
import com.meritbank.security.model.AuthenticationResponse;
import com.meritbank.service.MyUserDetailsService;
import com.meritbank.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MainController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String Home() {
		return "Welcome to Merit Bank Rest Services";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		UserInfoDTO dto = new UserInfoDTO(token, userDetails.getAuthorities().toString());

		return ResponseEntity.ok(dto);
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	public static class UserInfoDTO {
		public UserInfoDTO(String jwt, String roles) {
			this.jwt = jwt;
			this.roles = roles;
		}

		private String jwt;
		private String roles;

		public String getJwt() {
			return jwt;
		}

		public void setJwt(String jwt) {
			this.jwt = jwt;
		}

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

	}

	@PostMapping("/authenticate/createuser")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
