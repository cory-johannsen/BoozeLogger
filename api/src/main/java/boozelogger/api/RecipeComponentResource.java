package boozelogger.api;

import boozelogger.entity.Recipe;
import boozelogger.entity.RecipeComponent;
import boozelogger.entity.dao.IRecipeComponentDao;
import boozelogger.entity.dao.IRecipeDao;
import com.google.inject.Inject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import unification.configuration.Log;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;
import unification.security.RolesBanned;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 1:01 PM
 */
@Path("/{api_version}/recipeComponent")
public class RecipeComponentResource {

    @Log
    Logger logger;

    private IRecipeComponentDao recipeComponentDao;

    @Inject
    public RecipeComponentResource(IRecipeComponentDao recipeComponentDao) {
        this.recipeComponentDao = recipeComponentDao;
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public List<RecipeComponent> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for RecipeComponent list.");
        return recipeComponentDao.loadAll();
    }

    @GET
    @Path("/{recipecomponent_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public RecipeComponent getById(@PathParam("api_version") String apiVersion,
                          @PathParam("recipecomponent_id") Integer recipeComponentId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for RecipeComponent with ID " + recipeComponentId);
        return recipeComponentDao.loadById(recipeComponentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public RecipeComponent create(@PathParam("api_version") String apiVersion,
                                  RecipeComponent recipeComponent)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create RecipeComponent " + recipeComponent);
        return recipeComponentDao.create(recipeComponent);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public RecipeComponent update(@PathParam("api_version") String apiVersion,
                                  RecipeComponent recipeComponent)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update RecipeComponent " + recipeComponent);
        return recipeComponentDao.store(recipeComponent);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public RecipeComponent remove(@PathParam("api_version") String apiVersion,
                         RecipeComponent recipeComponent)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Recipe " + recipeComponent);
        return recipeComponentDao.remove(recipeComponent);
    }
}
