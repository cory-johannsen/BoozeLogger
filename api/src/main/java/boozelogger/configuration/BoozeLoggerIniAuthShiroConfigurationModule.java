package boozelogger.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unification.configuration.GrandUnificationModule;
import unification.configuration.IniShiroConfigurationModule;
import unification.configuration.NoAuthShiroConfigurationModule;

import javax.servlet.ServletContext;

/**
 * User: cjohannsen
 * Date: 5/6/14
 * Time: 11:42 AM
 */
public class BoozeLoggerIniAuthShiroConfigurationModule extends IniShiroConfigurationModule {

    private static final Logger log = LoggerFactory.getLogger(BoozeLoggerIniAuthShiroConfigurationModule.class);

    public BoozeLoggerIniAuthShiroConfigurationModule(ServletContext servletContext) {
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
        log.debug("configureFilterChains  - using API version " + apiVersion);

        // Filters are evaluated in the order in which they are defined so the least
        // specific should be added as the last filter chain. E.g. /** will match any
        // url so  it should be the last added to the the filters collection.
        addFilterChain("/" + apiVersion + "/*", AUTHC_BASIC);
        addFilterChain("/**", ANON);
    }
}
