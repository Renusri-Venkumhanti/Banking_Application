package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bean.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
