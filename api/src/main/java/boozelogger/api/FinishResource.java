package boozelogger.api;

import boozelogger.entity.Finish;
import boozelogger.entity.dao.IFinishDao;
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
@Path("/{api_version}/finish")
public class FinishResource {

    @Log
    Logger logger;

    private IFinishDao finishDao;

    @Inject
    public FinishResource(IFinishDao finishDao) {
        this.finishDao = finishDao;
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
    public List<Finish> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Finish list.");
        return finishDao.loadAll();
    }

    @GET
    @Path("/{finish_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Finish getById(@PathParam("api_version") String apiVersion,
                          @PathParam("finish_id") Integer finishId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Finish with ID " + finishId);
        return finishDao.loadById(finishId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Finish create(@PathParam("api_version") String apiVersion,
                         Finish finish)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Finish " + finish);
        return finishDao.create(finish);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Finish update(@PathParam("api_version") String apiVersion,
                         Finish finish)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Finish " + finish);
        return finishDao.store(finish);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Finish remove(@PathParam("api_version") String apiVersion,
                         Finish finish)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Finish " + finish);
        return finishDao.remove(finish);
    }
}
