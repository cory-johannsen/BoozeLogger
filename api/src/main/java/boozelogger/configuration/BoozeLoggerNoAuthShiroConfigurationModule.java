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
        // API Version
        String apiVersion = System.getProperty(GrandUnificationModule.API_VERSION);
        System.out.println("NoAuthShiroConfigurationModule.configureFilterChains  - using API version " + apiVersion);

        // Filters are evaluated in the order in which they are defined so the least
        // specific should be added as the last filter chain. E.g. /** will match any
        // url so  it should be the last added to the the filters collection.
        addFilterChain("/" + apiVersion + "/*", NO_SESSION_CREATION, AUTHC_BASIC);
        addFilterChain("/**", ANON);
    }
}
