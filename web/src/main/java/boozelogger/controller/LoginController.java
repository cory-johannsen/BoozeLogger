package boozelogger.controller;

import boozelogger.entity.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: cjohannsen
 * Date: 5/9/14
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginForm(ModelMap model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String validateLoginForm(ModelMap model) {
        return "login";
    }
}
