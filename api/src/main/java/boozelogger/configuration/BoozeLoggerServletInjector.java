package boozelogger.configuration;

import org.apache.shiro.guice.web.ShiroWebModule;
import unification.configuration.GrandUnificationModule;
import unification.configuration.GuiceServletInjector;

import javax.servlet.ServletContext;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 12:30 PM
 */
public class BoozeLoggerServletInjector extends GuiceServletInjector {

    @Override
    protected GrandUnificationModule createApplicationModule() {
        return new BoozeLoggerModule();
    }

    protected ShiroWebModule createShiroConfigurationModule(ServletContext context) {
        return new BoozeLoggerNoAuthShiroConfigurationModule(context);
    }
}
