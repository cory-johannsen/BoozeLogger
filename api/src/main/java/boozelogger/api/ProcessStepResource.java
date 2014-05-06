package boozelogger.api;

import boozelogger.entity.ProcessStep;
import boozelogger.entity.dao.IProcessStepDao;
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
@Path("/{api_version}/processStep")
public class ProcessStepResource {
    @Log
    Logger logger;

    private IProcessStepDao processStepDao;

    @Inject
    public ProcessStepResource(IProcessStepDao processStepDao) {
        this.processStepDao = processStepDao;
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
    public List<ProcessStep> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for ProcessStep list.");
        return processStepDao.loadAll();
    }

    @GET
    @Path("/{process_step_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public ProcessStep getById(@PathParam("api_version") String apiVersion,
                          @PathParam("process_step_id") Integer processStepId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for ProcessStep with ID " + processStepId);
        return processStepDao.loadById(processStepId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public ProcessStep create(@PathParam("api_version") String apiVersion,
                         ProcessStep processStep)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create ProcessStep " + processStep);
        return processStepDao.create(processStep);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public ProcessStep update(@PathParam("api_version") String apiVersion,
                         ProcessStep processStep)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update ProcessStep " + processStep);
        return processStepDao.store(processStep);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public ProcessStep remove(@PathParam("api_version") String apiVersion,
                         ProcessStep processStep)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove ProcessStep " + processStep);
        return processStepDao.remove(processStep);
    }
}
