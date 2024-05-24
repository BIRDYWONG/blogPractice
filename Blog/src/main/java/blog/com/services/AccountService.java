package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;
@Service
public class AccountService {
	
		@Autowired
		private AccountDao accountDao;
		
		public boolean creatAccount(String accountName,String accountEmail,String password) {
			if(accountDao.findByAccountEmail(accountEmail) == null) {
				accountDao.save(new Account(accountName, accountEmail, password));
				return true;
			}else {
				return false;
			}
		}
		
		public Account loginCheck(String accountEmail,String password) {
			Account account = accountDao.findByAccountEmailAndPassword(accountEmail,password);
			if(account == null) {
				return null;
			}else {
				return account;
			}
			
		}
		
	}

