package com.meritbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritbank.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NegativeAmountException;
import com.meritbank.exceptions.NoResourceFoundException;
import com.meritbank.exceptions.NoSuchAccountException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.SavingsAccount;
import com.meritbank.model.User;
import com.meritbank.security.JwtUtil;
import com.meritbank.service.AccountsService;
import com.meritbank.service.UserService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountsService accountsService;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User updateUsers(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User deleteUsers(@RequestBody User user) {
		return userService.deleteUser(user);
	}
	
	@GetMapping("/Me")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AccountHolder getAccountHolderById() {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder();
	}

	@PostMapping("/Me/checkingaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@RequestBody CheckingAccount checkingAccount)
			throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
			NoSuchAccountException, InvalidArgumentException {
		if (checkingAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();
		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		if (accHolder.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
		}

		return accountsService.addCheckingAccount(accHolder.getId(), checkingAccount);
	}

	@GetMapping("/Me/checkingaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<CheckingAccount> getCheckingAccount() {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getCheckingAccounts();
	}

	@PostMapping("/Me/savingsaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@RequestBody SavingsAccount savingsAccount)
			throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
			NoSuchAccountException, InvalidArgumentException {
		if (savingsAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();
		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		if (accHolder.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
		}
		return accountsService.addSavingsAccount(accHolder.getId(), savingsAccount);
	}

	@GetMapping("/Me/savingsaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<SavingsAccount> getSavingsAccount() {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getSavingsAccounts();
	}

	@PostMapping("/Me/cdaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@RequestBody CDAccount cdAccount)
			throws NoResourceFoundException, NegativeAmountException, NoSuchAccountException,
			ExceedsCombinedBalanceLimitException, InvalidArgumentException {
		if (cdAccount.getBalance() < 0) {
			throw new NegativeAmountException();
		}
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		AccountHolder accHolder = user.getAccountHolder();

		if (accHolder == null) {
			throw new NoResourceFoundException("Invalid id");
		}
		return accountsService.addCDAccount(accHolder.getId(), cdAccount);
	}

	@GetMapping("/Me/cdaccounts")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<CDAccount> getCDAccount() {
		String username = jwtTokenUtil.getCurrentUserName();
		User user = userService.getUserByUserName(username);
		return user.getAccountHolder().getCdAccounts();
	}

}

