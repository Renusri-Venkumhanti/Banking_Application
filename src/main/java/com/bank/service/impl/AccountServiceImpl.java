package com.bank.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bean.Account;
import com.bank.bean.Customer;
import com.bank.dao.AccountRepository;
import com.bank.dao.CustomerRepository;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.TransactionException;
import com.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired 
	AccountRepository accountRepo; 
	
	@Autowired 
	CustomerRepository customerRepo; 
	
	@Override
	public Account addAccount(Account account) {

		Optional<Account> accountInput = Optional.ofNullable(account);
		
		Account createdAccount = null;
		if(accountInput.isPresent()) {
			createdAccount = accountRepo.save(account);				
		}
		return createdAccount;
	}

	@Override
	public List<Account> getAllAccounts() {

		List<Account> list = accountRepo.findAll();
		return list;
	}

	@Override
	public Account getAccountById(int id) {
		Optional<Account> account = accountRepo.findById(id); 
		if(account.isPresent())
			return account.get();
		else
			throw new AccountNotFoundException("No Account found with given id "+ id);
	}

	@Override
	public void deleteAccount(Account account) {
		if(account != null)
			accountRepo.delete(account);
		else
			throw new AccountNotFoundException("No Such Account found");
		
	}

	@Override
	public void deleteAllAccounts() {
		accountRepo.deleteAll();
		
	}

	@Override
	public boolean isAccountExists(int id) {

		if(accountRepo.existsById(id))
			return true;
		
		return false;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public String transferFunds(int from, int to, double amount) {
		if(!isAccountExists(from))
			throw new AccountNotFoundException("Source Account doesn't Exists");
		if(!isAccountExists(to))
			throw new AccountNotFoundException("Destination Account doesn't Exists");
		if(from == to)
			throw new TransactionException("Source and Destination are same");
		if(amount< 0)
			throw new TransactionException(" Invalid amount");
		
		Account fromAccount = getAccountById(from);
		Account toAccount = getAccountById(to);
		
		if(fromAccount.getBalance()< amount)
			throw new TransactionException("Insuffiecient Funds");
		
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		addAccount(fromAccount);
		toAccount.setBalance(toAccount.getBalance()+amount);
		addAccount(toAccount);
		
		return "Transaction is Successful";
	}

}
