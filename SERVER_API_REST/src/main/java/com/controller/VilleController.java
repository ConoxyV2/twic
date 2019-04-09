package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public List<Ville> post(@RequestParam Map<String,String> allParams) {
		List<Ville> villes = new ArrayList<Ville>();
		Set<Entry<String, String>> set = allParams.entrySet();
		for(Entry<String, String> entry : set) {
			villes.addAll(daoVille.find(entry.getValue()));
		}
		return villes;
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.PUT)
	@ResponseBody
	public boolean put(@RequestParam String ville) {
		Ville v = new Ville();
		v.setCode_commune_INSEE("a");
		v.setCode_postal("b");
		v.setLatitude("c");
		v.setLibelle_acheminement("d");
		v.setLigne_5("e");
		v.setLongitude("f");
		v.setNom_commune(ville);
		return daoVille.create(v);
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(@RequestParam String ville) {
		Ville v = new Ville();
		v.setCode_commune_INSEE("a");
		v.setCode_postal("b");
		v.setLatitude("c");
		v.setLibelle_acheminement("d");
		v.setLigne_5("e");
		v.setLongitude("f");
		v.setNom_commune(ville);
		return daoVille.delete(v);
	}
}
