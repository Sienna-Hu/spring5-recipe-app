package guru.framework.spring5recipeapp.controllers;

import guru.framework.spring5recipeapp.domain.Category;
import guru.framework.spring5recipeapp.domain.Recipe;
import guru.framework.spring5recipeapp.domain.UnitOfMeasure;
import guru.framework.spring5recipeapp.repositories.CategoryRepository;
import guru.framework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.framework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

   private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
