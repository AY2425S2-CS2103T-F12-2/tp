package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_WEDDINGS;

import seedu.address.model.Model;

/**
 * Lists all persons in the contact book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons and weddings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // Reset the wedding list as well
        model.updateFilteredWeddingList(PREDICATE_SHOW_ALL_WEDDINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
