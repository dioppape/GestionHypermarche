package fr.controllers;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/** Example resource class hosted at the URI path "/admin"
 */
@Path("/admin")
public class Serveur{
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getAdmin() {
        return "Bienvenue chez Admin!";
    }
    @Path("/route")
	 @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }
    /*
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void loginAdmin(
        @FormParam("username") String username,
        @FormParam("password") String password,
        @Context HttpServletResponse servletResponse) throws IOException {
      Administrateur admin = new Administrateur();
      if (username != null) {
    	  servletResponse.sendRedirect("view/admin.xhtml");
      }
     // TodoDao.instance.getModel().put(username, password);

     
    }
	public boolean loginAdmin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}*/
}