package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
	public List<Ville> get(@RequestParam(value="codePostal") String codePostal) {
		return daoVille.find(codePostal);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public boolean post(@RequestBody Ville ville) {
		return daoVille.create(ville);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.PUT)
	@ResponseBody
	public boolean put(@RequestBody List<Ville> villes) {
		return daoVille.update(villes.get(0), villes.get(1));
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(@RequestBody Ville ville) {
		return daoVille.delete(ville);
	}
}
