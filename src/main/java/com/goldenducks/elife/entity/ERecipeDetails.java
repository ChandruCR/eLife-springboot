package com.goldenducks.elife.entity;

import java.util.HashMap;
import java.util.Map;

public class ERecipeDetails {

	private Map<String, String> info;

	  public ERecipeDetails() {
	    this.info = new HashMap<String, String>();
	  }

	  public ERecipeDetails(Map<String, String> info) {
	    this.info = info;
	  }

	  public Map<String, String> getInfo() {
	    return info;
	  }

	  public void setInfo(Map<String, String> info) {
	    this.info = info;
	  }

	  public ERecipeDetails addInfo(String title, String value) {
	    this.info.put(title, value);
	    return this;
	  }
	
}
