package com.goldenducks.elife.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldenducks.elife.entity.ERecipe;
import com.goldenducks.elife.microservices.ERecipeService;
import com.goldenducks.elife.controller.ERecipeController;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/elife/erecipe")
public class ERecipeController {
	
	@Autowired
	private ERecipeService eRecipeService;
	
	private static final Logger logger = LoggerFactory.getLogger(ERecipeController.class);
	
	// Service which gets all Recipes from repository
	@GetMapping
	public List<ERecipe> getAllERecipes() {

		logger.debug("getAllERecipes method of ERecipeController called");
		return eRecipeService.getAllERecipes();

	}

	// Service which gets complete details of a recipe from repository
	@GetMapping(value = "/{username}/{recipeName}")
	public ERecipe getERecipe(@PathVariable("username") String username,
			@PathVariable("recipeName") String recipeName) {

		logger.debug("getERecipe method of ERecipeController called");
		return eRecipeService.getERecipe(username, recipeName);

	}

	// Service which posts a recipe and saves it into repository
	@PostMapping(consumes = "application/json")
	public void createERecipe(@RequestBody ERecipe eRecipe) {

		logger.debug("createERecipe method of ERecipeController called");
		eRecipeService.createERecipe(eRecipe);

	}

	// Service which gets all recipes from repository matching username and recipeName passed to it
	@GetMapping(value = "/searchbyname/{username}/{recipeName}")
	public List<ERecipe> searchERecipesByRecipeName(@PathVariable("username") String username,
			@PathVariable("recipeName") String recipeName) {

		logger.debug("searchERecipesByRecipeName method of ERecipeController called");
		return eRecipeService.searchERecipesByRecipeName(username, recipeName);

	}

	// Service which gets all recipes from repository matching username and recipeType passed to it
	@GetMapping(value = "/searchbytype/{username}/{recipeType}")
	public List<ERecipe> searchERecipesByRecipeType(@PathVariable("username") String username,
			@PathVariable("recipeType") String recipeType) {

		logger.debug("searchERecipesByRecipeType method of ERecipeController called");
		return eRecipeService.searchERecipesByRecipeType(username, recipeType);

	}

	// Service which updates a specific recipe in repository
	@PutMapping(consumes = "application/json")
	public void updateERecipe(@RequestBody ERecipe eRecipe) {

		logger.debug("updateERecipe method of ERecipeController called");
		eRecipeService.updateERecipe(eRecipe);

	}

	// Service which deletes a matching recipe with username and recipeName passed to it
	@DeleteMapping(value= "/{username}/{recipeName}")
	public void deleteERecipe(@PathVariable("username") String username, @PathVariable("recipeName")String recipeName) {

		logger.debug("deleteERecipe method of ERecipeController called");
		eRecipeService.deleteERecipe(username, recipeName);

	}
}
