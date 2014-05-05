package boozelogger.configuration;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import unification.configuration.GrandUnificationModule;

import java.util.Map;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 10:14 AM
 */
public class GuiceJerseyServletModule extends GrandUnificationModule {

    @Override
    protected Map<String, String> configureApplicationParameters(Map<String, String> parameters) {
        parameters.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        parameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, "boozelogger.api");

        // wire up the @RolesAllowed resource filter
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory");

        // wire up our custom @RolesBanned resource filter annotation.
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.vendscreen.security.RolesBannedResourceFilterFactory");

        // Install logging filters to log all incoming and outgoing traffic.
        // DEVELOPMENT ONLY -- NOT FOR PRODUCTION USE
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");

        return parameters;
    }
}
