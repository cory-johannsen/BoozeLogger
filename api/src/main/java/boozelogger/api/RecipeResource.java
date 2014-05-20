package boozelogger.api;

import boozelogger.entity.Recipe;
import boozelogger.entity.dao.IRecipeDao;
import com.google.inject.Inject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import unification.configuration.Log;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;
import unification.security.RolesBanned;

import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 1:01 PM
 */
@Path("/{api_version}/recipe")
public class RecipeResource {

    @Log
    Logger logger;

    private IRecipeDao recipeDao;

    @Inject
    public RecipeResource(IRecipeDao recipeDao) {
        this.recipeDao = recipeDao;
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
    public List<Recipe> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Recipe list.");
        return recipeDao.loadAll();
    }

    @GET
    @Path("/{recipe_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Recipe getById(@PathParam("api_version") String apiVersion,
                          @PathParam("recipe_id") Integer recipeId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Recipe with ID " + recipeId);
        return recipeDao.loadById(recipeId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Recipe create(@PathParam("api_version") String apiVersion,
                         Recipe recipe)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Recipe " + recipe);
        return recipeDao.create(recipe);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Recipe update(@PathParam("api_version") String apiVersion,
                         Recipe recipe)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Recipe " + recipe);
        return recipeDao.store(recipe);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Recipe remove(@PathParam("api_version") String apiVersion,
                         Recipe recipe)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Recipe " + recipe);
        return recipeDao.remove(recipe);
    }
}
