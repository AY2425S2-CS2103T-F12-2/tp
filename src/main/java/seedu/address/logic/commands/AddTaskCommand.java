package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;
import seedu.address.model.wedding.WeddingId;
import seedu.address.model.wedding.WeddingTask;

/**
 * Adds a new task to a specific wedding identified by a Wedding ID.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to a specified wedding.\n"
            + "Parameters: w/WEDDING_ID desc/TASK_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " w/W1 desc/Book photographer";

    public static final String MESSAGE_SUCCESS = "New task added to wedding %1$s:\n%2$s";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid command format. " + MESSAGE_USAGE;

    private final WeddingId weddingId;
    private final String taskDescription;

    /**
     * Creates an AddTaskCommand to add a task with the specified description to the specified wedding ID.
     */
    public AddTaskCommand(WeddingId weddingId, String taskDescription) {
        requireNonNull(weddingId);
        requireNonNull(taskDescription);
        this.weddingId = weddingId;
        this.taskDescription = taskDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Wedding wedding = model.getFilteredWeddingList().stream()
                .filter(w -> w.getWeddingId().equals(weddingId))
                .findFirst()
                .orElseThrow(() -> new CommandException(String.format(Messages.MESSAGE_WEDDING_NOT_FOUND,
                        weddingId.value)));

        WeddingTask newTask = new WeddingTask(taskDescription);
        wedding.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, weddingId.value, newTask), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand c = (AddTaskCommand) other;
        return weddingId.equals(c.weddingId) && taskDescription.equals(c.taskDescription);
    }
}
