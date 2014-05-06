package boozelogger.api;

import boozelogger.entity.DistillationLog;
import boozelogger.entity.dao.IDistillationLogDao;
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
@Path("/{api_version}/distillationLog")
public class DistillationLogResource {
    @Log
    Logger logger;

    private IDistillationLogDao distillationLogDao;

    @Inject
    public DistillationLogResource(IDistillationLogDao distillationLogDao) {
        this.distillationLogDao = distillationLogDao;
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
    public List<DistillationLog> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for DistillationLog list.");
        return distillationLogDao.loadAll();
    }

    @GET
    @Path("/{distillation_log_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLog getById(@PathParam("api_version") String apiVersion,
                          @PathParam("distillation_log_id") Integer distillationLogId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for DistillationLog with ID " + distillationLogId);
        return distillationLogDao.loadById(distillationLogId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLog create(@PathParam("api_version") String apiVersion,
                         DistillationLog distillationLog)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create DistillationLog " + distillationLog);
        return distillationLogDao.create(distillationLog);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLog update(@PathParam("api_version") String apiVersion,
                         DistillationLog distillationLog)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update DistillationLog " + distillationLog);
        return distillationLogDao.store(distillationLog);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLog remove(@PathParam("api_version") String apiVersion,
                         DistillationLog distillationLog)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove DistillationLog " + distillationLog);
        return distillationLogDao.remove(distillationLog);
    }
}
