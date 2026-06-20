package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class alerts extends BaseTest
{

	@Test
	public void handleAlerts() {

		driver.get("https://demoqa.com/alerts");

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("https://demoqa.com/alerts"));

		driver.findElement(By.id("timerAlertButton")).click();
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert=driver.switchTo().alert();
		System.out.println("Alert text is:"+alert.getText());
		alert.accept();		
	}

	@Test
	public void sendTextToAlert() {

		driver.get("https://demoqa.com/alerts");

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("https://demoqa.com/alerts"));

		driver.findElement(By.id("promtButton")).click();
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert=driver.switchTo().alert();
		System.out.println("Alert text is:"+alert.getText());
		alert.sendKeys("This is my text");
		alert.accept();		
	}

	

}
