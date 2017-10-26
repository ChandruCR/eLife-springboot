package com.goldenducks.elife.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.goldenducks.elife.entity.ERecipe;
import com.goldenducks.elife.entity.ERecipeDetails;
import com.goldenducks.elife.microservices.ERecipeService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ERecipeController.class, secure = false)
public class ERecipeControllerTest {

		@Autowired
		private MockMvc mockMvc;
		
		@MockBean 
		private ERecipeService eRecipeService;
		
		private static List<ERecipe> mockERecipeList = new ArrayList<ERecipe>();
		
		private static ERecipe mockERecipe = new ERecipe();
		
		private static int expectedResponseCode = 200;
		
		@BeforeClass
		public static void setup() {
			ERecipeDetails eRecipeDetails = new ERecipeDetails();
			eRecipeDetails.addInfo("description", "this is description for this recipe");
			eRecipeDetails.addInfo("tags", "this is a tag for this recipe");
			
			ERecipe eRecipe = new ERecipe();
			eRecipe.setId("1001");
			eRecipe.setRecipeName("MockRecipeName");
			eRecipe.setRecipeType("MockRecipeType");
			eRecipe.setUsername("MockUsername");
			eRecipe.seteRecipeDetails(eRecipeDetails);
			ERecipeControllerTest.mockERecipe = eRecipe;
			ERecipeControllerTest.mockERecipeList.add(eRecipe);
					
		}
	
		@Test
		public void testGetAllRecipe() throws Exception {	
			
			Mockito.when(
					eRecipeService.getAllERecipes()).thenReturn(ERecipeControllerTest.mockERecipeList);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/elife/erecipe").accept(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			String expected = "[{\"id\":\"1001\",\"recipeName\":\"MockRecipeName\",\"recipeType\":\"MockRecipeType\",\"username\":\"MockUsername\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}]";		
			int responseCode = mvcResult.getResponse().getStatus();
			
			JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(),false);
			Assert.assertEquals(expectedResponseCode , responseCode);
			
		}
		
		@Test
		public void testGetERecipe() throws Exception {	
			
			Mockito.when(
					eRecipeService.getERecipe(Mockito.anyString(),Mockito.anyString())).thenReturn(ERecipeControllerTest.mockERecipe);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/elife/erecipe/"+mockERecipe.getUsername()+"/"+mockERecipe.getRecipeName()).accept(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			String expected = "{\"id\":\"1001\",\"recipeName\":\"MockRecipeName\",\"recipeType\":\"MockRecipeType\",\"username\":\"MockUsername\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}";
			int responseCode = mvcResult.getResponse().getStatus();
			
			JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(),false);
			Assert.assertEquals(expectedResponseCode , responseCode);
			
		}
				
		@Test
		public void testSearchERecipesByRecipeName() throws Exception {	
			
			Mockito.when(
					eRecipeService.searchERecipesByRecipeName(Mockito.anyString(),Mockito.anyString())).thenReturn(ERecipeControllerTest.mockERecipeList);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/elife/erecipe/searchbyname/"+mockERecipe.getUsername()+"/"+mockERecipe.getRecipeName()).accept(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			String expected = "[{\"id\":\"1001\",\"recipeName\":\"MockRecipeName\",\"recipeType\":\"MockRecipeType\",\"username\":\"MockUsername\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}]";		
			int responseCode = mvcResult.getResponse().getStatus();
			
			JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(),false);
			Assert.assertEquals(expectedResponseCode , responseCode);
			
		}
		
		@Test
		public void testSearchERecipesByRecipeType() throws Exception {	
			
			Mockito.when(
					eRecipeService.searchERecipesByRecipeType(Mockito.anyString(),Mockito.anyString())).thenReturn(ERecipeControllerTest.mockERecipeList);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/elife/erecipe/searchbytype/"+mockERecipe.getUsername()+"/"+mockERecipe.getRecipeType()).accept(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			String expected = "[{\"id\":\"1001\",\"recipeName\":\"MockRecipeName\",\"recipeType\":\"MockRecipeType\",\"username\":\"MockUsername\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}]";		
			int responseCode = mvcResult.getResponse().getStatus();
			
			JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(),false);
			Assert.assertEquals(expectedResponseCode , responseCode);
			
		}
		
		@Test
		public void testCreateERecipe() throws Exception {	
		    
		    String newRecipe = "{\"id\":\"1002\",\"recipeName\":\"MockRecipeName1\",\"recipeType\":\"MockRecipeType1\",\"username\":\"MockUsername1\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}";
		    
			RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post("/elife/erecipe/")
			.accept(MediaType.APPLICATION_JSON).content(newRecipe)
			.contentType(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = mvcResult.getResponse();
			
			Assert.assertEquals(expectedResponseCode , response.getStatus());
			
		}
		
		@Test
		public void testUpdateERecipe() throws Exception {	
			
			String newRecipe = "{\"id\":\"1002\",\"recipeName\":\"MockRecipeNameModified\",\"recipeType\":\"MockRecipeType1\",\"username\":\"MockUsername1\",\"eRecipeDetails\":{\"info\":{\"description\":\"this is description for this recipe\",\"tags\":\"this is a tag for this recipe\"}}}";
		    
			RequestBuilder requestBuilder = MockMvcRequestBuilders
			.put("/elife/erecipe/")
			.accept(MediaType.APPLICATION_JSON).content(newRecipe)
			.contentType(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = mvcResult.getResponse();
			
			Assert.assertEquals(expectedResponseCode , response.getStatus());
			
		}
		
		@Test
		public void testDeleteERecipe() throws Exception {	
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/elife/erecipe/"+mockERecipe.getUsername()+"/"+mockERecipe.getRecipeName()).accept(MediaType.APPLICATION_JSON);
			
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			int responseCode = mvcResult.getResponse().getStatus();
			
			Assert.assertEquals(expectedResponseCode , responseCode);
			
		}
}