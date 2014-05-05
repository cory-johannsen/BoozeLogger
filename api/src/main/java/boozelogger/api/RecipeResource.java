package boozelogger.api;

import boozelogger.entity.Recipe;
import boozelogger.entity.dao.IRecipeDao;
import com.google.inject.Inject;
import unification.configuration.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;

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
    public List<Recipe> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Recipe list.");
        return recipeDao.loadAll();
    }


}
