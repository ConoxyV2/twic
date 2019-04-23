package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoVille;
import com.dto.Ville;

@RestController
public class VilleController {

	private DaoVille daoVille = new com.dao.DaoVille();
	
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	@ResponseBody
	public List<Ville> get(@RequestParam(required=false, value="codePostal") String codePostal) {
		if(codePostal != null) {
			return daoVille.find(codePostal);
		}else {
			return daoVille.find();
		}
		
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public boolean post(@RequestBody Ville ville) {
		return daoVille.create(ville);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.PUT, consumes="application/json", produces = "application/json")
	@ResponseBody
	public boolean put(@RequestBody Ville ville) {
		return daoVille.update(ville);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(@RequestParam(required=true, value="id") String id) {
		return daoVille.delete(id);
	}
}
