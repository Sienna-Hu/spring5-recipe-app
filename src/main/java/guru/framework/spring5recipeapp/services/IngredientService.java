package guru.framework.spring5recipeapp.services;

import guru.framework.spring5recipeapp.commands.IngredientCommand;
import guru.framework.spring5recipeapp.domain.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}

