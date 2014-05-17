package boozelogger.controller;

import boozelogger.entity.Recipe;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@RequestMapping(method = RequestMethod.GET)
	public String getRecipeList(ModelMap model) {

        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/api/1.0/recipe");
        List<Recipe> recipes = (List<Recipe>)resource.get(Recipe.class);
        //List<Recipe> recipes = new ArrayList<Recipe>();
        //recipes.add(new Recipe(1, "Recipe 1", RecipeType.WHISKEY_MASH, new Date(), null, null));
        //recipes.add(new Recipe(2, "Recipe 2", RecipeType.WINE_MUST, new Date(), null, null));
		model.addAttribute("recipes", recipes);
		return "recipeList";
	}

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreateForm(ModelMap model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "recipeCreate";
    }
}