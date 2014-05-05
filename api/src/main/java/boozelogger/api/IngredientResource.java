package boozelogger.api;

import boozelogger.entity.Ingredient;
import boozelogger.entity.dao.IIngredientDao;
import com.google.inject.Inject;
import org.slf4j.Logger;
import unification.configuration.Log;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 3:41 PM
 */
@Path("/{api_version}/ingredient")
public class IngredientResource {
    @Log
    Logger logger;

    private IIngredientDao ingredientDao;

    @Inject
    public IngredientResource(IIngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Ingredient list.");
        return ingredientDao.loadAll();
    }

    @GET
    @Path("/{ingredient_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient getById(@PathParam("api_version") String apiVersion,
                          @PathParam("ingredient_id") Integer ingredientId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Ingredient with ID " + ingredientId);
        return ingredientDao.loadById(ingredientId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient create(@PathParam("api_version") String apiVersion,
                         Ingredient ingredient)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Ingredient " + ingredient);
        return ingredientDao.create(ingredient);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient update(@PathParam("api_version") String apiVersion,
                         Ingredient ingredient)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Ingredient " + ingredient);
        return ingredientDao.store(ingredient);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient remove(@PathParam("api_version") String apiVersion,
                         Ingredient ingredient)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Ingredient " + ingredient);
        return ingredientDao.remove(ingredient);
    }
}
