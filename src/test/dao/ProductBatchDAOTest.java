package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.connector.Connector;
import data.dao_implementation.ProductBatchDAO;
import data.dao_implementation.RecipeDAO;
import data.dao_interface.IProductBatchDAO;
import data.dao_interface.IRecipeDAO;
import data.dto.ProductBatchDTO;
import data.dto.RecipeDTO;
import exceptions.DALException;

public class ProductBatchDAOTest
{
	
	private IProductBatchDAO productBatchDAO;
	private IRecipeDAO recipeDAO; //Needed because ProductBatch has foreign key
	private int tempID; //Used to get multiple ID's

	@Before
	public void setUp()
	{
		tempID = 0;

		try 
		{
			recipeDAO = new RecipeDAO("91.100.3.26", 9865, "CDIOFinal_test", "Eclipse-bruger", "ySmTL37uDjYZmzyn");
			productBatchDAO = new ProductBatchDAO("91.100.3.26", 9865, "CDIOFinal_test", "Eclipse-bruger", "ySmTL37uDjYZmzyn");
		}
		catch(DALException e) 
		{
			System.out.println("Error connecting" + e.getMessage());
			fail("Error " + e.getMessage());
		}
	}

	@After
	public void teardown() 
	{
		try 
		{
			Connector con = new Connector();

			for(int i = 1; i <= tempID; i++) 
			{
				con.doUpdate("DELETE FROM productBatch WHERE productBatchID = " + i);
				con.doUpdate("DELETE FROM recipe WHERE recipeID = " + i);
			}
		}
		catch(DALException e) 
		{
			fail("Error " + e.getMessage());
		}
	}

	//TODO - skal tjekkes igennem
	@Test
	public void createAndGetProductBatchTEST() 
	{
		RecipeDTO recipeDTO = new RecipeDTO(1, "Test");
		ProductBatchDTO expected = new ProductBatchDTO(1, 1, 1);

		try 
		{
			recipeDAO.createRecipe(recipeDTO);

			productBatchDAO.createProductBatch(expected);
			tempID++;

			ProductBatchDTO actual = productBatchDAO.getProductBatch(1);
			assertEquals(expected.toString(), actual.toString());
		}
		catch(DALException e) 
		{
			fail("Error " + e.getMessage());
		}
	}

	//TODO - skal tjekkes igennem
	@Test
	public void updateProductBacthTEST() 
	{
		RecipeDTO recipeDTO = new RecipeDTO(1, "Test");
		ProductBatchDTO productBatchDTO = new ProductBatchDTO(1, 1, 1);
		ProductBatchDTO updateExpected = new ProductBatchDTO(1, 1, 0);

		try 
		{
			recipeDAO.createRecipe(recipeDTO);

			productBatchDAO.createProductBatch(productBatchDTO);
			productBatchDAO.updateProductBatch(updateExpected);
			tempID++;

			ProductBatchDTO actual = productBatchDAO.getProductBatch(1);

			assertEquals(updateExpected.toString(), actual.toString());
		}
		catch(DALException e) 
		{
			fail("Error " + e.getMessage());
		}
	}

	@Test
	public void getuserTEST()
	{
		//TODO
	}
	
	//TODO - skal tjekkes igennem
	@Test
	public void getALLUsersTEST() 
	{
		RecipeDTO recipeDTO1 = new RecipeDTO(1, "Test1");
		RecipeDTO recipeDTO2 = new RecipeDTO(2, "Test2");
		ProductBatchDTO expected1 = new ProductBatchDTO(1, 1, 1);
		ProductBatchDTO expected2 = new ProductBatchDTO(2, 2, 1);

		List<ProductBatchDTO> expectedList = new ArrayList<ProductBatchDTO>();
		expectedList.add(expected1);
		expectedList.add(expected2);

		try 
		{
			recipeDAO.createRecipe(recipeDTO1);
			productBatchDAO.createProductBatch(expected1);
			tempID++;

			recipeDAO.createRecipe(recipeDTO2);
			productBatchDAO.createProductBatch(expected2);
			tempID++;

			List<ProductBatchDTO> actualList = productBatchDAO.getAllProductBatches();

			assertEquals(expectedList.toString(), actualList.toString());
		}
		catch(DALException e) 
		{
			fail("Error " + e.getMessage());
		}
	}


}
