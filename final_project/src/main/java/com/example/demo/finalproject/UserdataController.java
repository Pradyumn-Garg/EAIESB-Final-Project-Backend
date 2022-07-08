package com.example.demo.finalproject;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class UserdataController {
	
	@Autowired
	private UserdataRepository finaldataRepo;
	
	@PostMapping("/signup")
	public Userdata saveLocations(@Valid @RequestBody Userdata newuser) {
		return finaldataRepo.save(newuser);
	}
	
	@PostMapping("/login")
    public Userdata  getUsersbyId(@Valid @RequestBody Logindata login)
	{
//        Token t= new Token();
		Userdata user = finaldataRepo.findByEmail(login.getEmail());

        if (login.getPassword().equals(user.getPassword()))
        {
        	return user;
        }
        else
        {
        	return null;
        }
    }
}
