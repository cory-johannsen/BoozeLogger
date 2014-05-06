package boozelogger.api;

import boozelogger.entity.DistillationLogEntry;
import boozelogger.entity.dao.IDistillationLogEntryDao;
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
@Path("/{api_version}/distillationLogEntry")
public class DistillationLogEntryResource {
    @Log
    Logger logger;

    private IDistillationLogEntryDao distillationLogEntryDao;

    @Inject
    public DistillationLogEntryResource(IDistillationLogEntryDao distillationLogEntryDao) {
        this.distillationLogEntryDao = distillationLogEntryDao;
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
    public List<DistillationLogEntry> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for DistillationLogEntry list.");
        return distillationLogEntryDao.loadAll();
    }

    @GET
    @Path("/{distillation_log_entry_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLogEntry getById(@PathParam("api_version") String apiVersion,
                          @PathParam("distillation_log_entry_id") Integer distillationLogEntryId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for DistillationLogEntry with ID " + distillationLogEntryId);
        return distillationLogEntryDao.loadById(distillationLogEntryId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLogEntry create(@PathParam("api_version") String apiVersion,
                         DistillationLogEntry distillationLogEntry)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create DistillationLogEntry " + distillationLogEntry);
        return distillationLogEntryDao.create(distillationLogEntry);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLogEntry update(@PathParam("api_version") String apiVersion,
                         DistillationLogEntry distillationLogEntry)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update DistillationLogEntry " + distillationLogEntry);
        return distillationLogEntryDao.store(distillationLogEntry);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public DistillationLogEntry remove(@PathParam("api_version") String apiVersion,
                         DistillationLogEntry distillationLogEntry)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove DistillationLogEntry " + distillationLogEntry);
        return distillationLogEntryDao.remove(distillationLogEntry);
    }
}
