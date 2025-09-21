package clanky.errors;

/**
 * Exception thrown when a deadline command is missing the required due date.
 * This occurs when users try to create a deadline task without specifying the /by flag and due date.
 */
public class MissingDueDateException extends ClankyException {
}
