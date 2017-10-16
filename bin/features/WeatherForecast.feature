Feature: Weather Forecast

	In order to get the weather forecast 
	As a traveller
	I want to see the weather forecast of 5 days for a specific city 

@traveller
Scenario: Enter a Valid City
	Given User is on the application page
	When User enters a valid city
	Then 5 day weather forecast should be displayed	
	
Scenario: Enter a InValid City
	Given User is on the application page
	When User enters a invalid city
	Then 5 day weather forecast should not be displayed