package com.bank.service;

import java.util.List;

import com.bank.bean.Account;
import com.bank.bean.Customer;

public interface AccountService {
	public Account addAccount(Account acount);
	public List<Account> getAllAccounts();
	public Account getAccountById(int id);
	public void deleteAccount(Account account);
	public void deleteAllAccounts();
	public boolean isAccountExists(int id);
	public List<Customer> getAllCustomers();
	public String transferFunds(int from, int to, double amount);
	
	

}
