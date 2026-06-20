package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class uploadTest extends BaseTest {


	@Test
	public void uploadFile() {

		driver.get("https://demoqa.com/upload-download");

		driver.findElement(By.id("uploadFile")).sendKeys("C:\\Users\\91995\\Downloads\\sampleFile.jpeg");
		driver.findElement(By.id("uploadedFilePath")).isDisplayed();
	}

}
