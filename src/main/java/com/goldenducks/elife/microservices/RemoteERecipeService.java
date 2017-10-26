package com.goldenducks.elife.microservices;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.goldenducks.elife.entity.ERecipe;
import com.goldenducks.elife.exception.ELifeException;

public class RemoteERecipeService implements ERecipeService {

	@Autowired
	protected RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(RemoteERecipeService.class);
	protected String serviceUrl;

	// sets serviceUrl with the URL passed to it
	public RemoteERecipeService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}

	
	// this method calls eRecipe service registered in Eureka to get all recipes from its repository
	public List<ERecipe> getAllERecipes() {
		try {
			logger.debug("getAllERecipes method of RemoteERecipeService called");
			return Arrays
					.asList(restTemplate.getForObject(serviceUrl + "/erecipe", ERecipe[].class));
		} catch (Exception e) {
			logger.debug("Exception in getAllERecipes method of RemoteERecipeService \r\n Exception Details:"
					+ e.getMessage());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to get completed details of a recipe from its repository
	public ERecipe getERecipe(String username, String recipeName) {
		try {
			logger.debug("getERecipe method of RemoteERecipeService called");
			return restTemplate.getForObject(serviceUrl + "/erecipe/{username}/{recipeName}", ERecipe.class, username,
					recipeName);
		} catch (Exception e) {
			logger.debug("Exception in getERecipe method of RemoteERecipeService \r\n Exception Details:"
					+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to save a recipe to its repository 
	public void createERecipe(ERecipe eRecipe) {
		try {
			logger.debug("createERecipe method of RemoteERecipeService called");
			restTemplate.postForObject(serviceUrl + "/erecipe", eRecipe, ERecipe.class);
		} catch (Exception e) {
			logger.debug("Exception in createERecipe method of RemoteERecipeService \r\n Exception Details:"
					+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to get all recipes from its repository matching with username and recipeName passed to it
	public List<ERecipe> searchERecipesByRecipeName(String username, String recipeName) {
		try {
			logger.debug("searchERecipesByRecipeName method of RemoteERecipeService called");
			return Arrays.asList(restTemplate.getForObject(serviceUrl + "/erecipe/searchbyname/{username}/{recipeName}",
					ERecipe[].class, username, recipeName));
		} catch (Exception e) {
			logger.debug(
					"Exception in searchERecipesByRecipeName method of RemoteERecipeService \r\n  Exception Details:"
							+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to get all recipes from its repository matching with username and recipeType passed to it
	public List<ERecipe> searchERecipesByRecipeType(String username, String recipeType) {
		try {
			logger.debug("searchERecipesByRecipeType method of RemoteERecipeService called");
			return Arrays.asList(restTemplate.getForObject(serviceUrl + "/erecipe/searchbytype/{username}/{recipeType}",
					ERecipe[].class, username, recipeType));
		} catch (Exception e) {
			logger.debug(
					"Exception in searchERecipesByRecipeType method of RemoteERecipeService \r\n Exception Details:"
							+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to update details of a recipe in its repository
	public void updateERecipe(ERecipe eRecipe) {
		try {
			logger.debug("updateERecipe method of RemoteERecipeService called");
			restTemplate.put(serviceUrl + "/erecipe", eRecipe);
		} catch (Exception e) {
			logger.debug("Exception in updateERecipe method of RemoteERecipeService \r\n Exception Details:"
					+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

	// this method calls eRecipe service registered in Eureka to delete a recipe from its repository
	public void deleteERecipe(String username, String recipeName) {
		try {
			logger.debug("deleteERecipe method of RemoteERecipeService called");
			restTemplate.delete(serviceUrl + "/erecipe/{username}/{recipeName}", username, recipeName);
		} catch (Exception e) {
			logger.debug("Exception in deleteERecipe method of RemoteERecipeService \r\n Exception Details:"
					+ e.getMessage() + "\r\n" + e.getCause());
			throw new ELifeException(e.getCause() + e.getMessage());
		}
	}

}
