package com.netmillennium.gagrid.app.optchange;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.netmillennium.gagrid.model.Chromosome;
import com.netmillennium.gagrid.parameter.GAGrid;

@SpringBootApplication
public class OptimizeMakeChangeApplication implements CommandLineRunner {
	   
	@Autowired
	private GAGrid gaGrid;
	
	@Autowired 
	private Ignite ignite;
	
	public static void main(String[] args) {
	   SpringApplication.run(OptimizeMakeChangeApplication.class, args);
	   }
	 @Override
	 public void run(String... args) {  
		 int amountChange=0;
		 try {
		       amountChange =Integer.parseInt(System.getProperty("AMOUNTCHANGE"));
		       if (amountChange>=1 && amountChange<=99)
		       { 
		    	   Chromosome solution =  gaGrid.evolve();
		       }
		       else {
		    	   throw new Exception();
		       } 
		    } catch (Exception e) {
		      ignite.log().error("The ammount must a number must be a numer >=1 and <=99");
              System.exit(1);
		    }
	 }
}
