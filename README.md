# spring5-recipe-app
A toy recipe application following instruction of an online Spring Framework course

## Notes
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
