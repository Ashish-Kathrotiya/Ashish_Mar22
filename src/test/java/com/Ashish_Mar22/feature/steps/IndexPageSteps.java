package com.Ashish_Mar22.feature.steps;

import com.Ashish_Mar22.pages.IndexPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IndexPageSteps {
	
	IndexPage indexpage = new IndexPage();
	
	
	@Given("^user navigates to nytimes url$")
    public void navigateToURL() throws Throwable {
		indexpage.navigateToURL();
    }
	
	@Then("^user verifies if instruction content visible$")
    public void verifyInstructionElement() throws Throwable {
		indexpage.verifyInstructionPrompt();
    }

    @When("^user dismiss instruction prompt$")
    public void closeInstructionromot() throws Throwable {
    	indexpage.closeInstruction();
    }

    @Then("^user verifies header section of index page$")
    public void verifyHeader() throws Throwable {
    	indexpage.verifyHeaderSection();
    }
    
    @And("^verifies game board section contains \"([^\"]*)\" rows having length of \"([^\"]*)\"$")
    public void verifyBoardSizeAndLength(String noOfRow, String length) throws Throwable {
    	indexpage.verifyBoardRowAndTileCount(Integer.parseInt(noOfRow), length);
    }
    
    @Then("^verifies keyboard section contains \"([^\"]*)\" rows$")
    public void verifyKeyboardElement(String noOfRow) throws Throwable {
    	indexpage.verifyKeyboardElement(Integer.parseInt(noOfRow));
    }
    
    @When("^user enters \"([^\"]*)\" keyword$")
    public void enterKeyword(String keyword) throws Throwable {
    	indexpage.enterKeyWord(keyword);
    }
    
    @And("^user hit enter key$")
    public void hitEnter() throws Throwable {
    	indexpage.hitEnter();
    }
    
    @Then("^user verifies data states of keyboad with respect to board for entered character$")
    public void verifyDataState() throws Throwable {
        indexpage.verifyDataState();
    }

    


}
