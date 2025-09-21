package clanky.errors;

/**
 * Exception thrown when an event command is missing the required end time.
 * This occurs when users try to create an event task without specifying the /to flag and end time.
 */
public class MissingEndTimeException extends ClankyException {
}
