package com.example.demo.finalproject;

import java.util.Date;
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
import io.swagger.v3.oas.annotations.Operation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin
@RestController
public class UserdataController {
	
	@Autowired
	private UserdataRepository finaldataRepo;
	
	private String jwtSecret="EAIESB";
    private int jwtExpirationMs =1800000;
	
	@PostMapping("/signup")
	public Userdata saveUser(@Valid @RequestBody Userdata newuser) {
		return finaldataRepo.save(newuser);
	}
	
	@PostMapping("/login")
    @Operation(summary = "Login with user details, returns token", description = "Get Token", tags = { "Userdata" })
    public Token  getUsersbyId(@Valid @RequestBody Logindata login)
	{
        Token t= new Token();
		Userdata user = finaldataRepo.findByEmail(login.getEmail());

        if (login.getPassword().equals(user.getPassword()))
        {
        	String jwtoken = Jwts.builder()
			                    .setSubject((user.getEmail()))
			                    .setIssuedAt(new Date())
			                    .setIssuer("Pradyumn")
			                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
			                    .compact();
			t.setFirstname(user.getFirstname());
			t.setLastname(user.getLastname());
			t.setType("Bearer");
			t.setEmail(user.getEmail());
			t.setToken(jwtoken);
			return t;
        }
        else
        {
            System.out.println("Authentication Failed");
            return t;
        }
    }
}
