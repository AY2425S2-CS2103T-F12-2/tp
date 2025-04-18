package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    /* Wedding Prefixes */
    public static final Prefix PREFIX_WEDDING_ID = new Prefix("w/");
    public static final Prefix PREFIX_WEDDING_NAME = new Prefix("n/");
    public static final Prefix PREFIX_WEDDING_DATE = new Prefix("d/");
    public static final Prefix PREFIX_WEDDING_LOCATION = new Prefix("l/");

    /* Task Prefixes */
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("desc/");

}
