Feature: Hide Hourly Forecast

	In order to hide the hourly forecast 
	As a traveller
	I want to hide the 3 or more hourly forecasts of selected day for a specific city
	
@traveller
Scenario: Hide 3 or more hourly forecasts by clicking on the day
	Given User is on the application page
	And User enters a valid city
	And Clicks on the day
	When User again clicks on the day
	Then 3 or more hourly forecasts should be disappeared