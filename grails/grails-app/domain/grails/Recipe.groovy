package grails

import boozelogger.entity.RecipeComponent
import boozelogger.entity.RecipeType

import java.sql.Timestamp

class Recipe {

    Integer id;
    String name;
    RecipeType type;
    Date createdAt;
    //List<RecipeComponent> components;
    //Process process;

    static constraints = {
    }
}
