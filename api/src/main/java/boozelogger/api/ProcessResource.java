package boozelogger.api;

import boozelogger.entity.Process;
import boozelogger.entity.dao.IProcessDao;
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
@Path("/{api_version}/process")
public class ProcessResource {
    @Log
    Logger logger;

    private IProcessDao processDao;

    @Inject
    public ProcessResource(IProcessDao processDao) {
        this.processDao = processDao;
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
    public List<Process> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Process list.");
        return processDao.loadAll();
    }

    @GET
    @Path("/{process_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Process getById(@PathParam("api_version") String apiVersion,
                          @PathParam("process_id") Integer processId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Process with ID " + processId);
        return processDao.loadById(processId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Process create(@PathParam("api_version") String apiVersion,
                         Process process)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Process " + process);
        return processDao.create(process);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Process update(@PathParam("api_version") String apiVersion,
                         Process process)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Process " + process);
        return processDao.store(process);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Process remove(@PathParam("api_version") String apiVersion,
                         Process process)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Process " + process);
        return processDao.remove(process);
    }
}
