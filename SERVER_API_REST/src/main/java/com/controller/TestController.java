package com.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody
	public String get(@RequestParam(required=false, value="value") String value) {
		System.out.println("Appel GET");
		System.out.println("value: "+value);
		
		return value;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	@ResponseBody
	public String get(@RequestParam Map<String,String> allParams) {
		System.out.println("Appel POST");
		Set<Entry<String, String>> set = allParams.entrySet();
		System.out.println(set.toString());
		return set.toString();
	}
}
