package guru.framework.spring5recipeapp.commands;

import guru.framework.spring5recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private Recipe recipe;
    private String recipeNotes;

}
