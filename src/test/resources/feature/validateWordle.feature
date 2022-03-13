Feature: validate all basic functionality of Wordle

Scenario: verify header option on index page
	Given user navigates to nytimes url
	Then user verifies if instruction content visible
	When user dismiss instruction prompt
	Then user verifies header section of index page
	

Scenario: verify game board and keyboard element is present 
	And verifies game board section contains "6" rows having length of "5"
	Then verifies keyboard section contains "3" rows
	
	
Scenario: verify correct, absent and present data state
	When user enters "SHARK" keyword
	And user hit enter key
	And user enters "House" keyword
	And user hit enter key
	Then user verifies data states of keyboad with respect to board for entered character