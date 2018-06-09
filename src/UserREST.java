

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Path("user")
public class UserREST{

	@POST
	@Path("createUser")
	public String createUser(@FormParam("name") String name, @FormParam("ini") String ini, @FormParam("active")int active) 
	{
		String message;

		try 
		{
			if(name.equals("") || ini.equals("") || active < 0 && active > 1)
			{
				message = "Fejl i inputtet!";
			}
			else
			{
				message = "Brugeren " + name + " er oprettet.";
			}
		} 
		catch (Exception e) 
		{
			message = e.getMessage();		
		}
		System.out.println(message);
		return message;
	}

	@POST
	@Path("updateUser")
	public String updateUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("ini") String ini) 
	{
		String message;

		try 
		{
			if(name.equals("") || ini.equals(""))
			{
				message = "Fejl i inputtet!";
			}
			else
			{
				message = "Brugeren " + name + " er opdateret!";
			}
		} 
		catch (Exception e) 
		{
			message = e.getMessage();
		}
		System.out.println(message);
		return message;
	}

	@POST
	@Path("setUserState")
	public String setUserState(@FormParam("id") int id, @FormParam("state") int state) 
	{
		String message;

		try 
		{
			if(state == 0 || state == 1)
			{
				message = "Brugerens aktivitetsstatus er ændret til " + state;
			}
			else
			{
				message = "Brugerens aktivitetsstatus kan kun være 0 eller 1.";
			}
		} 
		catch (Exception e) 
		{
			message = e.getMessage();
		}

		System.out.println(message);
		return message;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getUser")
	public String getUser(@FormParam("id") int id)
	{
		String message;

		JSONObject userJSON = new JSONObject();

		try 
		{
			if(id != 0)
			{
				userJSON.put("ID", "Søren");
				userJSON.put("name", "Raasted");
				userJSON.put("ini", "SR");
				userJSON.put("active", "1");

				message = "Brugeren " + "Søren" + " blev fundet";
			}
			else
			{
				message = "Ugyldigt ID blev indtastet\nPrøv igen";
			}
		} 
		catch (Exception e) 
		{
			message = e.getMessage();
		}

		System.out.println(message);

		return userJSON.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAllUsers")
	public String getAllUsers() 
	{
		String message;

		JSONArray users = new JSONArray();

		try 
		{
			message = "Alle brugeren er fundet!";
		} 
		catch (Exception e) 
		{
			message = e.getMessage();
		}

		System.out.println(message);
		return users.toString();
	}

}
