package boozelogger.api;

import boozelogger.entity.Ferment;
import boozelogger.entity.dao.IFermentDao;
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
@Path("/{api_version}/ferment")
public class FermentResource {

    @Log
    Logger logger;

    private IFermentDao fermentDao;

    @Inject
    public FermentResource(IFermentDao fermentDao) {
        this.fermentDao = fermentDao;
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
    public List<Ferment> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Ferment list.");
        return fermentDao.loadAll();
    }

    @GET
    @Path("/{ferment_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Ferment getById(@PathParam("api_version") String apiVersion,
                          @PathParam("ferment_id") Integer fermentId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Ferment with ID " + fermentId);
        return fermentDao.loadById(fermentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Ferment create(@PathParam("api_version") String apiVersion,
                         Ferment ferment)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Ferment " + ferment);
        return fermentDao.create(ferment);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Ferment update(@PathParam("api_version") String apiVersion,
                         Ferment ferment)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Ferment " + ferment);
        return fermentDao.store(ferment);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Ferment remove(@PathParam("api_version") String apiVersion,
                         Ferment ferment)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Ferment " + ferment);
        return fermentDao.remove(ferment);
    }
}
