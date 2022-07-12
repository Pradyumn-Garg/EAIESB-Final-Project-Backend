package com.example.demo.finalproject;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class FlowdataController {
	
	@Autowired
	private FlowdataRepository flowdataRepo;
	
	@PostMapping("/getflows")
	public Flowdata getFlows(@Valid @RequestBody Getflowdata newflows) 
	{
		Flowdata flows = flowdataRepo.findByEmail(newflows.getEmail());
		return flows;
	}
	
	@PostMapping("/postflows")
	public Flowdata updateFlows(@Valid @RequestBody Flowdata newflows)
	{ 
		Flowdata flowsindb = flowdataRepo.findByEmail(newflows.getEmail());
        if(flowsindb!=null) {
    		flowdataRepo.deleteByEmail(newflows.getEmail());
    		return flowdataRepo.save(newflows);
        }
        else {
    		return flowdataRepo.save(newflows);
        }
    }
}
