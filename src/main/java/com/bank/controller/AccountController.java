package com.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bean.Account;
import com.bank.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<?> getAccounts() {
		Map<String, Object> responseJson = new HashMap<>();

		List<Account> accounts = accountService.getAllAccounts();
		if(!accounts.isEmpty()) {
			responseJson.put("Status", 1);
			responseJson.put("Data", accounts);
			return new ResponseEntity<>(responseJson, HttpStatus.OK);
		}else {
			responseJson.clear();
			responseJson.put("Status", 0);
			responseJson.put("Message", "Accounts not found");
			return new ResponseEntity<>(responseJson, HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping("/accounts/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable Integer id) {

		Map<String, Object>responseJson = new HashMap<>();
		
		Account account = accountService.getAccountById(id);
		if(account != null) {
			responseJson.put("Status", 1);
			responseJson.put("Data", account);
			return new ResponseEntity<>(account, HttpStatus.OK);
		}
		else {
			responseJson.put("Status", 0);
			responseJson.put("Message", "Account not found for id:"+id);
			return new ResponseEntity<>(responseJson, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/account")
	public ResponseEntity<?> addAccount(@RequestBody Account account){
		Map<String, Object> responseJson = new HashMap<>();
		Account createdAccount = accountService.addAccount(account);
		responseJson.put("Status", 1);
		responseJson.put("Data", createdAccount);
		return new ResponseEntity<>(responseJson, HttpStatus.OK);
	}
	
	@DeleteMapping("/account/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable Integer id){
		Map<String, Object> responseJson = new HashMap<>();
		Account account = accountService.getAccountById(id);
		accountService.deleteAccount(account);
		responseJson.put("Status", 1);
		responseJson.put("message", "Account Successfully deleted for id :"+id);
		return new ResponseEntity<>(responseJson,HttpStatus.OK);
	}
	
	@DeleteMapping("/account")
	public ResponseEntity<?> deleteAllAccounts(){
		Map<String, Object> responseJson = new HashMap<>();
		accountService.deleteAllAccounts();
		responseJson.put("Status", 1);
		responseJson.put("message", "All Accounts are deleted Successfully");
		return new ResponseEntity<>(responseJson, HttpStatus.OK);
	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<?> updateTheUser(@PathVariable Integer id, @RequestBody Account account){
		Map<String, Object> responseJson = new HashMap<>();
		Account accountPresent = accountService.getAccountById(id);
		if(accountPresent != null) {
			accountPresent.setAccountId(id);
			accountPresent.setAccountType(account.getAccountType());
			accountPresent.setBalance(account.getBalance());
			accountPresent.setCustomer(account.getCustomer());
			accountService.addAccount(accountPresent);
			responseJson.put("Status", 1);
			responseJson.put("Data", accountService);
		}
					
		return new ResponseEntity<>(responseJson,HttpStatus.OK);
	}
	
	@PutMapping("/account/transfer")
	public ResponseEntity<?> transferFunds(@RequestParam Integer fromAccount, @RequestParam Integer toAccount, @RequestParam Double amount){
		Map<String, Object> responseJson = new HashMap<>();
		String message = accountService.transferFunds(fromAccount, toAccount, amount);
		responseJson.put("Status", 1);
		responseJson.put("message", message);
		return new ResponseEntity<>(responseJson,HttpStatus.OK);
	}
}
