package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.wedding.Wedding;
import seedu.address.model.wedding.WeddingDate;
import seedu.address.model.wedding.WeddingId;
import seedu.address.model.wedding.WeddingLocation;
import seedu.address.model.wedding.WeddingName;

/**
 * A utility class containing a list of {@code Wedding} objects to be used in tests.
 */
public class TypicalWeddings {

    public static final Wedding WEDDING_ONE;

    static {
        try {
            WEDDING_ONE = new Wedding(
                    new WeddingId("W001"),
                    new WeddingName("John & Jane Wedding"),
                    new WeddingDate("15-Jun-2025"),
                    new WeddingLocation("Central Park")
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Wedding WEDDING_TWO;

    static {
        try {
            WEDDING_TWO = new Wedding(
                    new WeddingId("W002"),
                    new WeddingName("Cheryl & Ben Wedding"),
                    new WeddingDate("14-Jun-2025"),
                    new WeddingLocation("Central Park")
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Wedding WEDDING_THREE;

    static {
        try {
            WEDDING_THREE = new Wedding(
                    new WeddingId("W003"),
                    new WeddingName("Alice & Bob Wedding"),
                    new WeddingDate("16-Jun-2025"),
                    new WeddingLocation("Downtown Hall")
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private TypicalWeddings() {
        // prevents instantiation
    }

    /**
     * Returns an {@code AddressBook} with all the typical weddings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addWedding(WEDDING_ONE);
        ab.addWedding(WEDDING_TWO);
        ab.addWedding(WEDDING_THREE);
        return ab;
    }

    public static List<Wedding> getTypicalWeddings() {
        return new ArrayList<>(Arrays.asList(WEDDING_ONE, WEDDING_TWO, WEDDING_THREE));
    }
}
