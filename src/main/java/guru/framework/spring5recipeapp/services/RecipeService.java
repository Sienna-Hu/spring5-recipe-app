package guru.framework.spring5recipeapp.services;

import guru.framework.spring5recipeapp.commands.RecipeCommand;
import guru.framework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
