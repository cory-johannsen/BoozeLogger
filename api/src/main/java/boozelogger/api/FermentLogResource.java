package boozelogger.api;

import boozelogger.entity.FermentLog;
import boozelogger.entity.dao.IFermentLogDao;
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
 * Time: 3:41 PM
 */
@Path("/{api_version}/fermentLog")
public class FermentLogResource {
    @Log
    Logger logger;

    private IFermentLogDao fermentLogDao;

    @Inject
    public FermentLogResource(IFermentLogDao fermentLogDao) {
        this.fermentLogDao = fermentLogDao;
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
    public List<FermentLog> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FermentLog list.");
        return fermentLogDao.loadAll();
    }

    @GET
    @Path("/{ferment_log_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLog getById(@PathParam("api_version") String apiVersion,
                          @PathParam("ferment_log_id") Integer fermentLogId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FermentLog with ID " + fermentLogId);
        return fermentLogDao.loadById(fermentLogId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLog create(@PathParam("api_version") String apiVersion,
                         FermentLog fermentLog)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create FermentLog " + fermentLog);
        return fermentLogDao.create(fermentLog);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLog update(@PathParam("api_version") String apiVersion,
                         FermentLog fermentLog)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update FermentLog " + fermentLog);
        return fermentLogDao.store(fermentLog);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLog remove(@PathParam("api_version") String apiVersion,
                         FermentLog fermentLog)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove FermentLog " + fermentLog);
        return fermentLogDao.remove(fermentLog);
    }
}
