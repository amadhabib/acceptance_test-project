Feature: Daily Forecast Summarization

	In order to get the daily forecast summary 
	As a traveller
	I want to see the summary of hourly forecasts of selected day for a specific city
	
@traveller
Scenario: Maximum temperature 
	Given User is on the application page
	When User enters a valid city
	Then maximum hourly temperature should be displayed in the summary
	
Scenario: Minimum temperature 
	Given User is on the application page
	When User enters a valid city
	Then minimum hourly temperature should be displayed in the summary

Scenario: Most current wind speed
	Given User is on the application page
	When User enters a valid city
	Then Most current wind speed should be displayed in the summary	
	
Scenario: Aggregate Rainfall
	Given User is on the application page
	When User enters a valid city
	Then Aggregate rainfall should be displayed in the summary
	
Scenario: Most current condition
	Given User is on the application page
	When User enters a valid city
	Then Most current condition should be displayed in the summary	