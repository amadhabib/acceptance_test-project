Feature: Show Hourly Forecast

	In order to get the hourly forecast 
	As a traveller
	I want to see the 3 or more hourly forecasts of selected day for a specific city
	
@traveller
Scenario: 3 or more hourly forecasts by clicking on the day
	Given User is on the application page
	When User enters a valid city
	And Clicks on the day
	Then 3 or more hourly forecasts should be displayed