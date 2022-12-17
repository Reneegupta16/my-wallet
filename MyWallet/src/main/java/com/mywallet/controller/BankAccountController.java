package com.mywallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.exceptions.BankAccountException;
import com.mywallet.exceptions.CustomerException;
import com.mywallet.exceptions.WalletException;
import com.mywallet.model.BankAccount;
import com.mywallet.model.Wallet;
import com.mywallet.service.AccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class BankAccountController {
	
	@Autowired
	private AccountService accountService;	
	
	
	@PostMapping("/addbankaccounts/{k}")
	public ResponseEntity<String> addAccountMapping(@PathVariable("k") String key,@Valid @RequestBody BankAccount bankAccount) throws BankAccountException, CustomerException, WalletException{
		
		 accountService.addAccount(bankAccount, key);
		
		return new ResponseEntity<String>("Bank Account Added Successfully",HttpStatus.CREATED);
	}

	
	
	@DeleteMapping("/deletebankaccounts")
	public ResponseEntity<Wallet> removeAccountMapping(@Valid @RequestBody BankAccount bankAccount,@RequestParam String key) throws BankAccountException{
		
		Wallet wallet= accountService.removeAccount(bankAccount, key);
		
		return new ResponseEntity<Wallet>(wallet,HttpStatus.OK);
	}
	

	@GetMapping("/bankaccounts")
	public ResponseEntity<List<BankAccount>> getAllBankAccountMapping(@RequestParam String key) throws BankAccountException, CustomerException{
		
		Wallet wallet = new Wallet();
		
		List<BankAccount> bankAccounts = accountService.viewAllAccount(key);
		
		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.FOUND);
		
	}
	
	
	
	
	
}