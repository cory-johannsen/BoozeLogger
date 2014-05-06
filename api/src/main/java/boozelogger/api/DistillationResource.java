package boozelogger.api;

import boozelogger.entity.Distillation;
import boozelogger.entity.dao.IDistillationDao;
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
@Path("/{api_version}/distillation")
public class DistillationResource {

    @Log
    Logger logger;

    private IDistillationDao distillationDao;

    @Inject
    public DistillationResource(IDistillationDao distillationDao) {
        this.distillationDao = distillationDao;
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
    public List<Distillation> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Distillation list.");
        return distillationDao.loadAll();
    }

    @GET
    @Path("/{distillation_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Distillation getById(@PathParam("api_version") String apiVersion,
                          @PathParam("distillation_id") Integer distillationId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Distillation with ID " + distillationId);
        return distillationDao.loadById(distillationId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Distillation create(@PathParam("api_version") String apiVersion,
                         Distillation distillation)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Distillation " + distillation);
        return distillationDao.create(distillation);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Distillation update(@PathParam("api_version") String apiVersion,
                         Distillation distillation)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Distillation " + distillation);
        return distillationDao.store(distillation);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Distillation remove(@PathParam("api_version") String apiVersion,
                         Distillation distillation)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Distillation " + distillation);
        return distillationDao.remove(distillation);
    }
}
