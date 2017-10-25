package com.goldenducks.elife.entity;


public class ERecipe {

	private String id;
	private String recipeName;
	private String recipeType;
	private String username;
	private ERecipeDetails eRecipeDetails;
	
	public ERecipe() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}
	public ERecipeDetails geteRecipeDetails() {
		return eRecipeDetails;
	}

	public void seteRecipeDetails(ERecipeDetails eRecipeDetails) {
		this.eRecipeDetails = eRecipeDetails;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
