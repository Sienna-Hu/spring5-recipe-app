package guru.framework.spring5recipeapp.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import guru.framework.spring5recipeapp.commands.CategoryCommand;
import guru.framework.spring5recipeapp.commands.IngredientCommand;
import guru.framework.spring5recipeapp.commands.NotesCommand;
import guru.framework.spring5recipeapp.commands.RecipeCommand;
import guru.framework.spring5recipeapp.domain.Category;
import guru.framework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory toCategory;
    private final IngredientCommandToIngredient toIngredient;
    private final NotesCommandToNotes toNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory toCategory, IngredientCommandToIngredient toIngredient, NotesCommandToNotes toNotes) {
        this.toCategory = toCategory;
        this.toIngredient = toIngredient;
        this.toNotes = toNotes;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setNotes(toNotes.convert(recipeCommand.getNotes()));

        Set<CategoryCommand> recivedCategory = recipeCommand.getCategories();
        if (recivedCategory != null && recivedCategory.size()>0) {
            recivedCategory
                    .forEach(categoryCommand -> recipe.getCategories().add(toCategory.convert(categoryCommand)));
        }

        Set<IngredientCommand> receivedIngredient = recipeCommand.getIngredients();
        if (receivedIngredient != null && receivedIngredient.size() > 0) {
            receivedIngredient
                    .forEach(ingredientCommand -> recipe.getIngredients().add(toIngredient.convert(ingredientCommand)));
        }

        return recipe;

    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
