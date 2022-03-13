package com.Ashish_Mar22.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.Ashish_Mar22.utility.CommonDriverUtils;

public class IndexPage extends CommonDriverUtils {

	By gameApp = By.cssSelector("game-app");
	By gameHelp = By.cssSelector("game-help");
	By instruction = By.cssSelector(".instructions");
	By gameModel = By.cssSelector("game-modal");
	By iconClose = By.cssSelector("game-icon[icon='close']");
	By iconMenu = By.cssSelector("#nav-button");
	By iconHelp = By.cssSelector("#help-button");
	By lblWordle = By.cssSelector(".title");
	By btnStatistic = By.cssSelector("#statistics-button");
	By btnSettings = By.cssSelector("#settings-button");
	By boardRow = By.cssSelector("#board>game-row");
	By boardTile = By.cssSelector("div:nth-child(2) > game-tile");
	By keyboard = By.cssSelector("game-keyboard");
	By keyboardRow = By.cssSelector("div#keyboard>div.row");
	By enterKey = By.cssSelector("button[data-key='↵']");

	String key = "button[data-key='%s']";

	HashMap<String, String> tileValue = new HashMap<String, String>();

	public void navigateToURL() {
		getDriver().get("https://www.nytimes.com/games/wordle/index.html");
		getDriver().manage().window().maximize();
	}

	public void verifyInstructionPrompt() {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		SearchContext shGameHelp = shGameApp.findElement(gameHelp).getShadowRoot();
		Assert.assertTrue("Instruction is visible: ",
				CommonDriverUtils.isElementDisplay(getDriver(), shGameHelp.findElement(instruction)));
	}

	public void closeInstruction() {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		SearchContext shGameModel = shGameApp.findElement(gameModel).getShadowRoot();
		SearchContext shIconClose = shGameModel.findElement(iconClose).getShadowRoot();
		shIconClose.findElement(By.cssSelector("svg")).click();
	}

	public void verifyHeaderSection() {

		SearchContext shGameApp = $(gameApp).getShadowRoot();
		Assert.assertTrue("Menu icon is visible: ",
				CommonDriverUtils.isElementDisplay(getDriver(), shGameApp.findElement(iconMenu)));
		Assert.assertTrue("Help icon is visible: ",
				CommonDriverUtils.isElementDisplay(getDriver(), shGameApp.findElement(iconHelp)));
		Assert.assertTrue("Wordle text is visible: ", shGameApp.findElement(lblWordle).getText().contains("Wordle"));
		Assert.assertTrue("Help icon is visible: ",
				CommonDriverUtils.isElementDisplay(getDriver(), shGameApp.findElement(btnStatistic)));
		Assert.assertTrue("Help icon is visible: ",
				CommonDriverUtils.isElementDisplay(getDriver(), shGameApp.findElement(btnSettings)));
	}

	public void verifyBoardRowAndTileCount(int rowSize, String cellSize) {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		List<WebElement> listRow = shGameApp.findElements(boardRow);
		Assert.assertTrue("Game total row size must be 6", listRow.size() == rowSize);

		for (int size = 0; size < listRow.size(); size++) {
			Assert.assertTrue("Game each row length must be 5",
					listRow.get(size).getAttribute("length").equals(cellSize));
			SearchContext shGameRow = listRow.get(size).getShadowRoot();
			List<WebElement> listTile = shGameRow.findElements(boardTile);
			Assert.assertTrue("Game total row size must be 5", listTile.size() == Integer.parseInt(cellSize));
		}
	}

	public void verifyKeyboardElement(int rowSize) {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		SearchContext keyboardElement = shGameApp.findElement(keyboard).getShadowRoot();
		List<WebElement> listRow = keyboardElement.findElements(keyboardRow);
		Assert.assertTrue("Keyboard total row size must be 3", listRow.size() == rowSize);
	}

	public void enterKeyWord(String word) {
		char[] wordle = word.toLowerCase().toCharArray();
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		SearchContext keyboardElement = shGameApp.findElement(keyboard).getShadowRoot();
		for (int size = 0; size < wordle.length; size++) {
			keyboardElement.findElement(By.cssSelector(String.format(key, wordle[size]))).click();
		}
	}

	public void hitEnter() {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		SearchContext keyboardElement = shGameApp.findElement(keyboard).getShadowRoot();
		keyboardElement.findElement(enterKey).click();
		CommonDriverUtils.staticWaitForFewSeconds();
	}

	public void verifyDataState() {
		SearchContext shGameApp = $(gameApp).getShadowRoot();
		List<WebElement> listRow = shGameApp.findElements(boardRow);
		SearchContext keyboardElement = shGameApp.findElement(keyboard).getShadowRoot();

		for (int size = 0; size < listRow.size(); size++) {
			if (listRow.get(size).getAttribute("letters").length() > 0) {
				SearchContext shGameRow = listRow.get(size).getShadowRoot();
				List<WebElement> listTile = shGameRow.findElements(boardTile);
				for (int tile = 0; tile < listTile.size(); tile++) {
					if (!tileValue.containsKey(listTile.get(tile).getAttribute("letter")))
						tileValue.put(listTile.get(tile).getAttribute("letter"),
								listTile.get(tile).getAttribute("evaluation"));
				}
			}
		}

		for (Entry<String, String> mapElement : tileValue.entrySet()) {
			String mapKey = mapElement.getKey();
			String mapValue = mapElement.getValue();
			Assert.assertTrue("Tile data set value is match with Keyboard",
					keyboardElement.findElement(By.cssSelector(String.format(key, mapKey))).getAttribute("data-state")
							.equalsIgnoreCase(mapValue));
		}
	}
}
