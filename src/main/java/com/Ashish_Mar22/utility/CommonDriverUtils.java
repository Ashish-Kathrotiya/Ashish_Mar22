package com.Ashish_Mar22.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;

public class CommonDriverUtils extends PageObject{
	
	public static boolean isElementDisplay(WebDriver driver, WebElement element) {

		try {
			element.isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}

	}
	
	public static void staticWaitForFewSeconds() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
