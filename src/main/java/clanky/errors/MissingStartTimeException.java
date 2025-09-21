package clanky.errors;

/**
 * Exception thrown when an event command is missing the required start time.
 * This occurs when users try to create an event task without specifying the /from flag and start time.
 */
public class MissingStartTimeException extends ClankyException {
}
