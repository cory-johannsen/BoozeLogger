package boozelogger.api;

import boozelogger.entity.FermentLogEntry;
import boozelogger.entity.dao.IFermentLogEntryDao;
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
@Path("/{api_version}/fermentLogEntry")
public class FermentLogEntryResource {
    @Log
    Logger logger;

    private IFermentLogEntryDao fermentLogEntryDao;

    @Inject
    public FermentLogEntryResource(IFermentLogEntryDao fermentLogEntryDao) {
        this.fermentLogEntryDao = fermentLogEntryDao;
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
    public List<FermentLogEntry> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FermentLogEntry list.");
        return fermentLogEntryDao.loadAll();
    }

    @GET
    @Path("/{ferment_log_entry_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLogEntry getById(@PathParam("api_version") String apiVersion,
                          @PathParam("ferment_log_entry_id") Integer fermentLogEntryId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for FermentLogEntry with ID " + fermentLogEntryId);
        return fermentLogEntryDao.loadById(fermentLogEntryId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLogEntry create(@PathParam("api_version") String apiVersion,
                         FermentLogEntry fermentLogEntry)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create FermentLogEntry " + fermentLogEntry);
        return fermentLogEntryDao.create(fermentLogEntry);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLogEntry update(@PathParam("api_version") String apiVersion,
                         FermentLogEntry fermentLogEntry)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update FermentLogEntry " + fermentLogEntry);
        return fermentLogEntryDao.store(fermentLogEntry);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public FermentLogEntry remove(@PathParam("api_version") String apiVersion,
                         FermentLogEntry fermentLogEntry)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove FermentLogEntry " + fermentLogEntry);
        return fermentLogEntryDao.remove(fermentLogEntry);
    }
}
