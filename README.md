# spring5-recipe-app
A toy recipe application following instruction of an online Spring Framework course

## Notes (Summarize steps)
1. Create needed POJOs as Spring Data JPA Entity
    Data Model: ![data_model](/imagesForReadMe/data_model.png)
    1. Create Entity: `Category`, `Difficulty`, `Ingredient`, `Notes`, `Recipe`, and `UnitOfMeasure`
    2. Establish relationships
   
2. Create Spring Data Repositories
    - `CategoryRepository`, `RecipeRepository`, and `UnitOfMeasureRepository`
    
3. Create Service Layer
    - Interface `RecipeService` and its implementation
    - Two methods: `getRecipes()` and `findById(Long id)`

4. Initialize database with Spring
    - a data.sql file: insertion statement to set description property for category and unit_of_measure

5. Initialize data in DataLoader
    - Retrieve all UnitOfMeasure and Category from database
    - Create 2 recipes and save them to recipeRepository
    
6. Create fundemental View layer using Thymeleaf
    - At first, only index page showing two recipe (id and description) 
    - Add a page showing all details of a particular recipe (on path `"/recipe/{recipeId}/show"`)
        - Add a view link on Index page
            - `<a href="#" th:href="@{/recipe/" + ${recipe.id} + "/show}">View</a>`
            - Thymeleaf generate the url when clicked
        - Create a RecipeController
            - RecipeController has a function `public String showById(@PathVariable String id, Model model)` annotated by `@GetMapping({"/recipe/{id}/show"})` 
            - When users clicked on the View link on Index page, they are redirected to url `"/recipe/{id}/show"` and the function `showById(@PathVariable String id, Model model)` executes.
        - Create a show.html to display the detailed recipe 
            - `showById(@PathVariable String id, Model model)` return the show.html to the user
          
7. Add functionality of processing form posts with Spring MVC
    - Create Command Object and Type Conversions 
        - How Command Object and Type converters work: 
            1. Take in Command Objects
            2. Convert command objects to any existing Entities
            3. Persist the converted object to the database
            4. Get entities out of the database
            5. Convert entities to Command Objects
        - Why we need them: will not expose domain entities to the Web Layer or transmit over the network
        - What we need: 
            - Command Object mimics entity
            - Converters: from command object to entity and from entity to command object
            - Service that can accept a command object, convert it, save it to the database, return an entity, and convert it to the command object
            
     - Add functionalities
         - Allow to create a recipe 
             - On path `/recipe/new`, users can create a new recipe. The new recipe will be saved to database and show on the Index page
             - Steps
                 1. Create a `recipeform.html`
                     - `<form ... method="post">`
                 2. RecipeService
                     - Add a function `public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand)`.
                         - The function accepts a RecipeCommand, convert it to a Recipe, save it to recipeRepository, and return back a RecipeCommand
                 3. RecipeController
                     - Add a function `public String newRecipe(Model model)` annotated by `@GetMapping("recipe/new")`.
                         - The function will pass a RecipeCommand object to `recipeform.html`, and return the recipeform.html to the user. The user can enter required information for a new recipe.
                     - Add a function `public String saveOrUpdate(@ModelAttribute RecipeCommand command)` annotated by `@PostMapping( "recipe")`. 
                         - The function executes when getting a post to `/recipe` path.
                         - The function will save the new recipe to recipeRepository and redirect the user to the page showing details of the newly created recipe.
                         - `@ModelAttribute` is the annotation that tells Spring to bind the form post parameters to the RecipeCommand object.
                         
          - Allow to update a recipe
              - On path `recipe/{id}/update` (Index Page contains an "Update" button that directs users to this path), users can update a recipe. The updated recipe will be saved to database.
              - Steps
                  1. RecipeService
                      - Add a function `public RecipeCommand findCommandById(Long l)`.
                          - The function finds a recipe with a specific id value and converts it to a RecipeCommand object.
                  1. RecipeController
                      - Add a function `public String updateRecipe(@PathVariable String id, Model model)` annotated by `@GetMapping("recipe/{id}/update")`
                          - The function passes a RecipeCommand object with specified id to `recipeform.html`, and return the recipeform.html to the user. The user can modified information for that recipe.
                      - Function `saveOrUpdate(@ModelAttribute RecipeCommand command)` will execute when user submit the `recipeform.html`.
                      
          - Allow to delete a recipe
               - Once on path `recipe/{id}/delete` (Index Page contains an "Delete" button that directs users to this path), the recipe with specified id will be deleted. 
               - Steps
                   1. RecipeService
                       - Add a function `public void deleteById(Long l)`.
                           - The function deletes a recipe with specified id from recipeRepository
                   2. RecipeController
                       - Add a function `public String deleteById(@PathVariable String id)` annotated by `@GetMapping("recipe/{id}/delete")`
                           - The function calls `recipeService.deleteById(Long.valueOf(id))` and redirects user to the index page.
                           
           - Allow to view, update, create, and delete an ingredient.
           - Allow to upload images, persisting images to database, and displaying images from database
                         
                         
                         
                         
                         
                         
                         
                         
                         
