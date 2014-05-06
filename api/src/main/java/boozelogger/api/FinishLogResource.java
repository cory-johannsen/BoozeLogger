package boozelogger.api;

import boozelogger.entity.FinishLog;
import boozelogger.entity.dao.IFinishLogDao;
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
@Path("/{api_version}/finishLog")
public class FinishLogResource {
    @Log
    Logger logger;

    private IFinishLogDao finishLogDao;

    @Inject
    public FinishLogResource(IFinishLogDao finishLogDao) {
        this.finishLogDao = finishLogDao;
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
    public List<FinishLog> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FinishLog list.");
        return finishLogDao.loadAll();
    }

    @GET
    @Path("/{finish_log_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLog getById(@PathParam("api_version") String apiVersion,
                          @PathParam("finish_log_id") Integer finishLogId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FinishLog with ID " + finishLogId);
        return finishLogDao.loadById(finishLogId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLog create(@PathParam("api_version") String apiVersion,
                         FinishLog finishLog)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create FinishLog " + finishLog);
        return finishLogDao.create(finishLog);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLog update(@PathParam("api_version") String apiVersion,
                         FinishLog finishLog)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update FinishLog " + finishLog);
        return finishLogDao.store(finishLog);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLog remove(@PathParam("api_version") String apiVersion,
                         FinishLog finishLog)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove FinishLog " + finishLog);
        return finishLogDao.remove(finishLog);
    }
}
