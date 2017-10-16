package stepdefinition;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class WeatherForecast {

	//Code for launching the application in Firefox
	WebDriver driver;
	@Given("^User is on the application page$")
	public void user_is_on_the_application_page() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://localhost:3000/"); 
	}

	//Entering city in the text field
	@When("^User enters a ([^\\\"]*) city$")
	public void user_enters_a_city(String scenario) throws Throwable {
		
		//Valid scenario
		if(scenario.equals("valid"))
		{
			driver.findElement(By.id("city")).clear();
			driver.findElement(By.id("city")).sendKeys("edinburgh");
			driver.findElement(By.id("city")).sendKeys(Keys.RETURN);
		}
		
		//Invalid scenario
		else
		{
			driver.findElement(By.id("city")).clear();
			driver.findElement(By.id("city")).sendKeys("hgdtsg$@");
			driver.findElement(By.id("city")).sendKeys(Keys.RETURN);
		}
		
	}


	//Verification of 5 day forecast
	@Then("^(\\d+) day weather forecast ([^\\\"]*) be displayed$")
	public void day_weather_forecast_should_be_displayed(int arg1, String scenario) throws Throwable {
		
		//Valid case
	    if(scenario.equals("should"))
	    {
	    	int size=driver.findElements(By.xpath("//*[@id='root']/div/div")).size();
	    	Assert.assertEquals(arg1, size);
	    	driver.quit();
	    }    
	    
	    //Invalid case
	    else
	    {
	    	String str=driver.findElement(By.xpath("//*[@id='root']/div/div")).getText();
	    	Assert.assertEquals("Error retrieving the forecast", str);
	    	driver.quit();
	    }
	    
	}
	
	
	//Click on day for all the five scenarios and verify that the each day have hourly forecast of 3 or more hours
	@And("^Clicks on the day$")
	public void clicks_on_the_day() throws Throwable {
		
		for(int i=1;i<6;i++)
		{
			driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();		
			int size=driver.findElements(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div")).size();
			Assert.assertTrue(size>2);		
		}
		
	}

	//Condition checked in above function. Function only present for mapping from feature to scenario
	@Then("^(\\d+) or more hourly forecasts should be displayed$")
	public void or_more_hourly_forecasts_should_be_displayed(int arg1) throws Throwable {
	    //This condition has been checked in the function above
		driver.quit();
	}
	
	//Validating the hiding of hourly forecasts
	@When("^User again clicks on the day$")
	public void user_again_clicks_on_the_day() throws Throwable {
		for(int i=1;i<6;i++)
		{
			driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();		
			driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();
		}
	}

	//Condition checked in above function. Function only present for mapping from feature to scenario
	@Then("^(\\d+) or more hourly forecasts should be disappeared$")
	public void or_more_hourly_forecasts_should_be_disappeared(int arg1) throws Throwable {
		driver.quit();
	}
	
	//Verification of temperature in the summary
	@Then("^([^\\\\\\\"]*) hourly temperature should be displayed in the summary$")
	public void hourly_temperature_should_be_displayed_in_the_summary(String scenario) throws Throwable {
	    
		//Maximum temperature case
		if(scenario.equals("maximum"))
		{
			//Outer loop to iterate through all the 5 days
			for(int i=1;i<6;i++)
			{
				//Array list for storing all the hourly temperatures
				ArrayList<Integer> temperature = new ArrayList<Integer>();
				
				//Getting the value displayed on the daily summary
				driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();		
				int size=driver.findElements(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div")).size();
				String str=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[3]/span[1]")).getText();		
				
				//Extracting the numeric value from the string and then converting it to integer for comparison purposes
				Matcher matcher = Pattern.compile("\\d+").matcher(str);
				matcher.find();
				Integer check = Integer.valueOf(matcher.group());
				
				//Preparing the array of hourly temperature forecast for comparison
				for (int j=1;j<size+1;j++)
				{
					//Extracting the numeric value from the string and then converting it to integer for comparison purposes
					String str2=driver.findElement(By.xpath(".//*[@id='root']/div/div["+i+"]/div[2]/div["+j+"]/span[3]/span[1]")).getText();
					Matcher matcher2 = Pattern.compile("\\d+").matcher(str2);
					matcher2.find();
					Integer val = Integer.valueOf(matcher2.group());
					
					//Adding it to dynamic array list
					temperature.add(val);
				}
				
				//Finding the maximum value
				Integer maximum = Collections.max(temperature);
				System.out.println(maximum);
				
				//Comparison for validation 
				Assert.assertEquals(maximum, check);
			}
			driver.quit();
		}
		
		//Minimum temperature case - Logic same as maximum but uses minimum function
		else
		{
			for(int i=1;i<6;i++)
			{
				ArrayList<Integer> temperature = new ArrayList<Integer>();
				
				driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();		
				int size=driver.findElements(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div")).size();
				String str=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[3]/span[2]")).getText();		
				
				Matcher matcher = Pattern.compile("\\d+").matcher(str);
				matcher.find();
				Integer check = Integer.valueOf(matcher.group());
				
				for (int j=1;j<size+1;j++)
				{
					
					String str2=driver.findElement(By.xpath(".//*[@id='root']/div/div["+i+"]/div[2]/div["+j+"]/span[3]/span[2]")).getText();
					Matcher matcher2 = Pattern.compile("\\d+").matcher(str2);
					matcher2.find();
					Integer val = Integer.valueOf(matcher2.group());
					
					temperature.add(val);
				}
				
				Integer minimum = Collections.min(temperature);
				System.out.println(minimum);
				Assert.assertEquals(minimum, check);
			}
			driver.quit();
		}
		
	}
	
	//Most current values in summary validations
	@Then("^Most current ([^\\\\\\\"]*) should be displayed in the summary$")
	public void most_current_scenario_should_be_displayed_in_the_summary(String scenario) throws Throwable {
	   
		//Validation for showing most current wind speed in summary
		if(scenario.equals("wind speed"))
		{
			//Iterating through each day(so that all the values can be validated)
			for(int i=1;i<6;i++)
			{
				//Checks the summary value with the most current value
				driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();
				String str1=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[4]/span[1]")).getText();
				String str2=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div[1]/span[4]/span[1]")).getText();
				Assert.assertEquals(str2, str1);
			}
		}
		
		//Most current condition in summary validation
		else if(scenario.equals("condition"))
		{
			//Iterating through each day(so that all the values can be validated)
			for(int i=1;i<6;i++)
			{
				//This code is using AShot library for image comparison of actual and expected screenshots of web elements
				WebElement element1=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[2]"));
				
					Screenshot screen1= new AShot().takeScreenshot(driver, element1);
					driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();
					BufferedImage actual = screen1.getImage();

					WebElement element2=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div[1]/span[2]"));
					Screenshot screen2= new AShot().takeScreenshot(driver,element2);
					BufferedImage expected = screen2.getImage();

					ImageDiffer difference = new ImageDiffer();
					ImageDiff diff=difference.makeDiff(expected, actual);
					
					Assert.assertFalse(diff.hasDiff());
				
				
					driver.quit();
				}
				
			}
		}
	
	//Validation for aggregate rainfall display in the summary
	@SuppressWarnings("deprecation")
	@Then("^Aggregate rainfall should be displayed in the summary$")
	public void aggregate_rainfall_should_be_displayed_in_the_summary() throws Throwable {
		
		//Iterating through each day(so that all the values can be validated)
		for(int i=1;i<6;i++)
		{
			//Array list for storing all the hourly temperatures
			ArrayList<Integer> temperature = new ArrayList<Integer>();
			
			//Getting the value displayed on the daily summary
			driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[1]/span[1]")).click();		
			int size=driver.findElements(By.xpath("//*[@id='root']/div/div["+i+"]/div[2]/div")).size();
			String str=driver.findElement(By.xpath("//*[@id='root']/div/div["+i+"]/div[1]/span[5]/span[1]")).getText();		
			
			//Extracting the numeric value from the string and then converting it to integer for comparison purposes
			Matcher matcher = Pattern.compile("\\d+").matcher(str);
			matcher.find();
			Integer check = Integer.valueOf(matcher.group());
			
			//Preparing the array of hourly rainfall forecast for comparison
			for (int j=1;j<size+1;j++)
			{
				//Extracting the numeric value from the string and then converting it to integer for comparison purposes
				String str2=driver.findElement(By.xpath(".//*[@id='root']/div/div["+i+"]/div[2]/div["+j+"]/span[5]/span[1]")).getText();
				Matcher matcher2 = Pattern.compile("\\d+").matcher(str2);
				matcher2.find();
				Integer val = Integer.valueOf(matcher2.group());
				
				//Adding it to dynamic array list
				temperature.add(val);
			}
			
			//Sum calculation of the hourly rainfall forecasts
			Integer sum = 0;
		    for(int k = 0; k < temperature.size(); k++)
		    {
		        sum += temperature.get(k);
		    }
		    
			//Performing verification that summary value and sum value both are same or not
			Assert.assertEquals(sum, check);
		}
		driver.quit();
	}
	
	}
