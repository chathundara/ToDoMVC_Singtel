Feature: Manage the todo list

Background: 
Given the URL "https://todomvc.com/examples/vue/" 


Scenario: Add an item to the empty list
Given an empty todo list
When I add the item "Go shopping"
Then only "Go shopping" item should be active
And "1 item left" message should be displayed
And the "All" and "Active" and "Completed" tabs should be there


Scenario: Add multiple items to the empty list
Given an empty todo list
When I add two items "Go shopping" and "Play Cricket"
Then only added items should be listed
And "2 items left" message should be displayed
And the "All" and "Active" and "Completed" tabs should be there


Scenario: Complete a single item from multiple items
Given a list with two items "Go shopping" and "Play Cricket"
When "Go shopping" item is complete
Then the "Play Cricket" item should only be active
And first item should be inactive
And "1 item left" message should be displayed
And "ClearCompleted" link should be appeared 
And the "All" and "Active" and "Completed" tabs should be there


Scenario: Active tab should not have completed tasks
Given a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When navigate to the "Active" tab
Then "Go shopping" item should not be in the "Active" list
And only "Play Cricket" item should be active
And "1 item left" message should be displayed


Scenario: Completed tab should not have active tasks
Given a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When navigate to the "Completed" tab
Then "Play Cricket" item should not be in the "Completed" list
And only "Play Cricket" item should be active
And "1 item left" message should be displayed


Scenario: 'Clear Completed' action should not remove active tasks
Given a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When 'Clear completed' is done
Then only "Play Cricket" item should be active


Scenario: A completed task make active again
Given  a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When Tap on the "Go shopping" item check mark
Then only "Go shopping" item should be active


Scenario: A completed task possible to be edited
Given  a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When change "Go shopping" item to "Read a book"
Then "Go shopping" item should be changed to "Read a book"
And "Read a book" item is still completed


Scenario: An active task possible to be edited
Given  a list with two items "Go shopping" and "Play Cricket"
And "Go shopping" item is complete
When change "Play Cricket" item to "Play a game"
Then "Play Cricket" item should be changed to "Play a game"
And "Play a game" item is still active


Scenario: Delete an item from the list
Given a list with a single item "shopping"
When Delete the item "shopping"
Then an empty todo list should be there 