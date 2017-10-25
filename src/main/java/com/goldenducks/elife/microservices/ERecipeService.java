package com.goldenducks.elife.microservices;

import java.util.List;

import com.goldenducks.elife.entity.ERecipe;

public interface ERecipeService {

	public List<ERecipe> getAllERecipes();

	public void createERecipe(ERecipe eRecipe);

	public List<ERecipe> searchERecipesByRecipeName(String username, String recipeName);

	public List<ERecipe> searchERecipesByRecipeType(String username, String recipeType);

	public void updateERecipe(ERecipe eRecipe);

	public void deleteERecipe(String username, String recipeName);

	public ERecipe getERecipe(String username, String recipeName);

}
