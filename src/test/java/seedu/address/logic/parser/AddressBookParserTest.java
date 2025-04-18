package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEDDING_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AddWeddingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ConfirmCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DeleteWeddingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditWeddingCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.SortByWeddingDateCommand;
import seedu.address.logic.commands.SortByWeddingIdCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.wedding.WeddingId;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(keywords), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        String userInput = FilterCommand.COMMAND_WORD + " W1";
        assertTrue(parser.parseCommand(userInput) instanceof FilterCommand);
    }

    @Test
    public void parseCommand_tag() throws Exception {
        String userInput = TagCommand.COMMAND_WORD + " 1 W123";
        assertTrue(parser.parseCommand(userInput) instanceof TagCommand);
    }

    @Test
    public void parseCommand_confirm() throws Exception {
        assertTrue(parser.parseCommand(ConfirmCommand.COMMAND_WORD) instanceof ConfirmCommand);
        assertTrue(parser.parseCommand(ConfirmCommand.COMMAND_WORD + "   ") instanceof ConfirmCommand);
    }

    @Test
    public void parseCommand_addWeddingEvent() throws Exception {
        String userInput = AddWeddingCommand.COMMAND_WORD + " n/Alice & Bob d/01-Dec-2025 l/Paris";
        assertTrue(parser.parseCommand(userInput) instanceof AddWeddingCommand);
    }

    @Test
    public void parseCommand_editWeddingEvent() throws Exception {
        String userInput = EditWeddingCommand.COMMAND_WORD + " W3 " + PREFIX_WEDDING_NAME + "New Wedding Name";
        assertTrue(parser.parseCommand(userInput) instanceof EditWeddingCommand);
    }

    @Test
    public void parseCommand_deleteWedding() throws Exception {
        String userInput = DeleteWeddingCommand.COMMAND_WORD + " W12345";
        DeleteWeddingCommand command = (DeleteWeddingCommand) parser.parseCommand(userInput);
        assertEquals(new DeleteWeddingCommand(new WeddingId("W12345")), command);
    }

    @Test
    public void parseCommand_sortByWeddingDate() throws Exception {
        assertTrue(parser.parseCommand(SortByWeddingDateCommand.COMMAND_WORD)
                instanceof SortByWeddingDateCommand);
        assertTrue(parser.parseCommand(SortByWeddingDateCommand.COMMAND_WORD + "  ")
                instanceof SortByWeddingDateCommand);
    }

    @Test
    public void parseCommand_sortByWeddingId() throws Exception {
        assertTrue(parser.parseCommand(SortByWeddingIdCommand.COMMAND_WORD)
                instanceof SortByWeddingIdCommand);
        assertTrue(parser.parseCommand(SortByWeddingIdCommand.COMMAND_WORD + "  ")
                instanceof SortByWeddingIdCommand);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        // Example input: "addTask w/W1 desc/Book florist"
        String userInput = AddTaskCommand.COMMAND_WORD + " w/W1 desc/Book florist";
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(userInput);
        assertEquals(new AddTaskCommand(new WeddingId("W1"), "Book florist"), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        // Example input: "deleteTask w/W1 i/2"
        String userInput = DeleteTaskCommand.COMMAND_WORD + " w/W1 i/2";
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(userInput);
        assertEquals(new DeleteTaskCommand(new WeddingId("W1"), 2), command);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        // Example input: "listTask w/W1"
        String userInput = ListTaskCommand.COMMAND_WORD + " w/W1";
        assertTrue(parser.parseCommand(userInput) instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        // Example input: "mark w/W1 i/1"
        String userInput = MarkTaskCommand.COMMAND_WORD + " w/W1 i/1";
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(userInput);
        assertEquals(new MarkTaskCommand(new WeddingId("W1"), 1), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        // Example input: "unmark w/W1 i/3"
        String userInput = UnmarkTaskCommand.COMMAND_WORD + " w/W1 i/3";
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(userInput);
        assertEquals(new UnmarkTaskCommand(new WeddingId("W1"), 3), command);
    }

    @Test
    public void parseCommand_untag() throws Exception {
        // Example input: "untag 1 W123"
        // or if your parser uses a different prefix approach, adapt accordingly
        String userInput = UntagCommand.COMMAND_WORD + " 1 W123";
        assertTrue(parser.parseCommand(userInput) instanceof UntagCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
