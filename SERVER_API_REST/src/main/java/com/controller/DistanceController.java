package com.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoVille;
import com.dto.Ville;

@RestController
public class DistanceController {

private DaoVille daoVille = new com.dao.DaoVille();

	private static final Logger LOGGER = Logger.getLogger(DaoVille.class);
	
	@RequestMapping(value="/distance", method=RequestMethod.GET)
	@ResponseBody
	public double get(@RequestParam(required=true, value="id1") String id1, @RequestParam(required=true, value="id2") String id2) {
		Ville v1 = daoVille.findId(id1);
		Ville v2 = daoVille.findId(id2);
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("0.#");
		format.setDecimalFormatSymbols(symbols);
		
		double lat1 = 0;
		double long1 = 0;
		double lat2 = 0;
		double long2 = 0;
		try {
			lat1 = (Double) format.parse(v1.getLatitude());
			long1 = (Double) format.parse(v1.getLongitude());
			lat2 = (Double) format.parse(v2.getLatitude());
			long2 = (Double) format.parse(v2.getLongitude());
		} catch (ParseException e) {
			LOGGER.error("Error", e);
		}
		
		double temp1 = Math.sin(Math.toRadians(lat1))*Math.sin(Math.toRadians(lat2));
		double temp2 = Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(long2-long1));
		
		double result = 6173*Math.acos(temp1+temp2);
		return result;
	}
}
