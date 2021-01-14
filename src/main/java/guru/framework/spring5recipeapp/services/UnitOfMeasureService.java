package guru.framework.spring5recipeapp.services;

import guru.framework.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> getAllUom();

}
