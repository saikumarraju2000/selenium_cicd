package tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class links extends BaseTest
{

	@Test
	public void countLinks() {

		driver.get("https://demoqa.com/links");

		List<WebElement> links =driver.findElements(By.xpath("//div[@id='linkWrapper']//a"));
		System.out.println("Total Links are:"+links.size());

		for(WebElement link:links) {
			System.out.println("Link text: "+link.getText()+"Link   "+link.getAttribute("href"));
		}	
	}

	@Test
	public void countBrokenLinks() {

		driver.get("https://demoqa.com/broken");

		List<WebElement> links =driver.findElements(By.tagName("a"));
		System.out.println("Total Links are:"+links.size());
		int brokenCount=0;

		for (WebElement link : links) {
			String url = link.getAttribute("href");

			// Skip empty or null links
			if (url == null || url.isEmpty()) {
				System.out.println("⚠️  Empty link found");
				continue;
			}

			// Skip mailto and tel links
			if (url.startsWith("mailto:") || url.startsWith("tel:")) {
				continue;
			}

			// Check the link
			int responseCode = getResponseCode(url);

			if (responseCode >= 400) {
				System.out.println("❌ Broken Link: " + url + " | Response: " + responseCode);
				brokenCount++;
			} else {
				System.out.println("✅ Valid Link: " + url + " | Response: " + responseCode);
			}
		}

		System.out.println("----------------------------");
		System.out.println("Total Broken Links: " + brokenCount);
		System.out.println("Total Valid Links: " + (links.size() - brokenCount));
	}


	@Test
	public void findBrokenImages() throws Exception {
		driver.get("https://demoqa.com/broken");

		List<WebElement> images = driver.findElements(By.tagName("img"));
		System.out.println("Total images found: " + images.size());

		for (WebElement img : images) {
			String src = img.getAttribute("src");

			if (src == null || src.isEmpty()) {
				System.out.println("❌ Image with no src found");
				continue;
			}

			int responseCode = getResponseCode(src);
			if (responseCode >= 400) {
				System.out.println("❌ Broken Image: " + src);
			} else {
				System.out.println("✅ Valid Image: " + src);
			}
		}
	}

	private int getResponseCode(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(5000);  // 5 seconds timeout
			connection.setReadTimeout(5000);
			connection.connect();
			return connection.getResponseCode();
		} catch (Exception e) {
			System.out.println("⚠️  Could not connect to: " + url);
			return 0;
		}
	}



}
