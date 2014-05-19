package boozelogger.configuration;

import unification.configuration.GrandUnificationModule;
import unification.configuration.LDAPShiroConfigurationModule;
import unification.configuration.NoAuthShiroConfigurationModule;

import javax.servlet.ServletContext;

/**
 * User: cjohannsen
 * Date: 5/6/14
 * Time: 11:42 AM
 */
public class BoozeLoggerNoAuthShiroConfigurationModule extends NoAuthShiroConfigurationModule {

    public BoozeLoggerNoAuthShiroConfigurationModule(ServletContext servletContext) {
        super(servletContext);
    }

    /* (non-Javadoc)
     * @see unification.configuration.GuiceShiroConfigurationModule#configureFilterChains()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void configureFilterChains() {
        //Key<SharedSecretAuthenticatingFilter> SHARED_SECRET = Key.get(SharedSecretAuthenticatingFilter.class);

        // API Version
        String apiVersion = System.getProperty(GrandUnificationModule.API_VERSION);
        System.out.println("NoAuthShiroConfigurationModule.configureFilterChains  - using API version " + apiVersion);

        // Filters are evaluated in the order in which they are defined so the least specific should be added as the last filter
        // chain. E.g. /** will match any url so  it should be the last added to the the filters collection.

        // The Shared Secret filter is custom to the unification api. It will return either a UsernamePasswordToken if
        // the client  is a generic caller, or a custom SharedSecretToken if it detects the presence of a custom http
        // header.  Since it handles the generation of both authentication tokens will use, we only need the one
        // filter to perform authentication.
        addFilterChain("/" + apiVersion + "/*", NO_SESSION_CREATION, AUTHC_BASIC);
        addFilterChain("/**", ANON);
    }
}