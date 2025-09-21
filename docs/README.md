# Clanky User Guide

![Title Im](https://github.com/duckyfuz/ip/blob/master/docs/title.png)

Clanky is a command-line interface chatbot that helps you manage your tasks efficiently. It supports adding different types of tasks, tracking their completion status, and provides search functionality to find tasks quickly.

## Adding todos

Add a simple task without any deadline or time constraints.

Example: `todo read book`

A todo task will be added to your task list.

```
____________________________________________________________
added: [T][ ] read book
Now you have 1 tasks.
____________________________________________________________
```

## Adding deadlines

Add a task that needs to be completed by a specific date or time.

Example: `deadline return book /by June 6th`

A deadline task will be added to your task list with the specified due date.

```
____________________________________________________________
added: [D][ ] return book (by: June 6th)
Now you have 2 tasks.
____________________________________________________________
```

## Adding events

Add a task that occurs during a specific time period.

Example: `event team meeting /from 2pm /to 4pm`

An event task will be added to your task list with the specified time range.

```
____________________________________________________________
added: [E][ ] team meeting (from: 2pm to: 4pm)
Now you have 3 tasks.
____________________________________________________________
```

## Listing tasks

Display all your current tasks with their completion status.

Example: `list`

All tasks in your list will be displayed with their index numbers.

```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: June 6th)
3. [E][ ] team meeting (from: 2pm to: 4pm)
____________________________________________________________
```

## Marking tasks as done

Mark a task as completed by specifying its index number.

Example: `mark 1`

The specified task will be marked as done (shown with [X]).

```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```

## Unmarking tasks

Mark a previously completed task as not done.

Example: `unmark 1`

The specified task will be marked as not done (shown with [ ]).

```
____________________________________________________________
Ok. I've marked this task as not done yet:
[T][ ] read book
____________________________________________________________
```

## Deleting tasks

Remove a task from your list by specifying its index number.

Example: `delete 2`

The specified task will be removed from your task list.

```
____________________________________________________________
Can. I've removed this task:
[D][ ] return book (by: June 6th)
____________________________________________________________
```

## Finding tasks

Search for tasks containing a specific keyword in their description.

Example: `find book`

All tasks containing the keyword will be displayed with their original index numbers.

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
____________________________________________________________
```

## Exiting the application

Exit Clanky and save your tasks automatically.

Example: `bye`

Your tasks will be saved and the application will close.

```
____________________________________________________________
Bye! Don't come back.
____________________________________________________________
```
