package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application 
{
    public static void main( String[] args )
    {
    	try {
    	SpringApplication.run(Application.class, args);
    	System.out.println("Application démarée !");
    	}catch (Exception e){
    		System.err.println(e.getStackTrace()); 
    	}
    }
}
