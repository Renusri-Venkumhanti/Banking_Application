package com.bank;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.bank.bean.Account;
import com.bank.bean.Customer;
import com.bank.dao.AccountRepository;
import com.bank.service.impl.*; 

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServiceTest {
				@InjectMocks
                AccountServiceImpl accountService;
				@Mock
                AccountRepository accountRepository;

                private List<Account> accountList;

                @BeforeEach
                void setUp() throws Exception {
                                accountList = new ArrayList<>();
                                accountList.add(new Account(1, "current", 1000.0, new Customer(1, "Ash", "varanasi", "999999")));
                                accountList.add(new Account(2, "current", 9000.0, new Customer(2, "shi", "mumb", "111111")));
                                accountList.add(new Account(3, "current", 12000.0, new Customer(3, "Ram", "Pune", "654745")));                               
                }

                @Test
                void testAddAccount() {
                                Account expectedAccount = accountList.get(1);
                                when(accountRepository.save(any())).thenReturn(expectedAccount);
                                Account actualAccount = accountService.addAccount(expectedAccount);
                                assertEquals(expectedAccount, actualAccount);
                }

                @Test
                void testGetAllAccounts() {
                                when(accountRepository.findAll()).thenReturn(accountList);
                                List<Account> actual = accountService.getAllAccounts();
                                assertEquals(actual, accountList);
                                verify(accountRepository).findAll();
                }

                @Test
                void testGetAccountById() {
                                Optional<Account> account = Optional.of(accountList.get(1));
                                when(accountRepository.findById(1)).thenReturn(account);
                                Optional<Account> actual = Optional.of(accountService.getAccountById(1));
                                assertEquals(account, actual);
                }

                @Test
                void testDeleteAllAccounts() {
                                accountService.deleteAllAccounts();
                                verify(accountRepository,times(1)).deleteAll();
                }

                @Test
                void testDeleteAccount() {
                                Account account = accountList.get(1);
                                accountService.deleteAccount(account);
                                verify(accountRepository,times(1)).delete(account);
                }

                @Test
                void testTransferFunds() {
                                when(accountService.isAccountExists(3)).thenReturn(true);
                                when(accountService.isAccountExists(2)).thenReturn(true);
                                when(accountRepository.findById(2)).thenReturn(accountList.stream().filter(account -> account.getAccountId() == 2).findFirst());
                                when(accountRepository.findById(3)).thenReturn(accountList.stream().filter(account -> account.getAccountId() == 3).findFirst());

                                assertEquals("Transaction is successful", accountService.transferFunds(3, 2, 1000));
                                assertEquals(11000, accountList.get(2).getBalance());
                                assertEquals(10000, accountList.get(1).getBalance());
                                verify(accountRepository).findById(2);
                                verify(accountRepository).findById(3);
                                verify(accountRepository).save(accountList.get(2));
                                verify(accountRepository).save(accountList.get(1));
                }

//            @Test

//            void testGetAllCustomers() {

//                            fail("Not yet implemented");

//            }

 

}
