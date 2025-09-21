package clanky.errors;

/**
 * Exception thrown when attempting to operate on a task that doesn't exist.
 * This occurs when users try to mark, unmark, or delete a task using an invalid task number.
 */
public class NonExistantTaskError extends ClankyException {
}
