package api.endpoints;


/*
 * GET - https://petstore.swagger.io/v2/user/{username}
 * POST - https://petstore.swagger.io/v2/user
 * PUT - https://petstore.swagger.io/v2/user/{username}
 * DELETE - https://petstore.swagger.io/v2/user/{username}
 * 
 */
public class __Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User model
	public static String post_url = base_url+ "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String put_url = base_url +  "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	
	//Store model
	
	//Pet model
	
}
