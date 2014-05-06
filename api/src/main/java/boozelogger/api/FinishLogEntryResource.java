package boozelogger.api;

import boozelogger.entity.FinishLogEntry;
import boozelogger.entity.dao.IFinishLogEntryDao;
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
@Path("/{api_version}/finishLogEntry")
public class FinishLogEntryResource {
    @Log
    Logger logger;

    private IFinishLogEntryDao finishLogEntryDao;

    @Inject
    public FinishLogEntryResource(IFinishLogEntryDao finishLogEntryDao) {
        this.finishLogEntryDao = finishLogEntryDao;
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
    public List<FinishLogEntry> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FinishLogEntry list.");
        return finishLogEntryDao.loadAll();
    }

    @GET
    @Path("/{finish_log_entry_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLogEntry getById(@PathParam("api_version") String apiVersion,
                          @PathParam("finish_log_entry_id") Integer finishLogEntryId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FinishLogEntry with ID " + finishLogEntryId);
        return finishLogEntryDao.loadById(finishLogEntryId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLogEntry create(@PathParam("api_version") String apiVersion,
                         FinishLogEntry finishLogEntry)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create FinishLogEntry " + finishLogEntry);
        return finishLogEntryDao.create(finishLogEntry);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLogEntry update(@PathParam("api_version") String apiVersion,
                         FinishLogEntry finishLogEntry)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update FinishLogEntry " + finishLogEntry);
        return finishLogEntryDao.store(finishLogEntry);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FinishLogEntry remove(@PathParam("api_version") String apiVersion,
                         FinishLogEntry finishLogEntry)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove FinishLogEntry " + finishLogEntry);
        return finishLogEntryDao.remove(finishLogEntry);
    }
}
