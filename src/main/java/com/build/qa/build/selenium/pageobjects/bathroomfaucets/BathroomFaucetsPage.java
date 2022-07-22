package com.build.qa.build.selenium.pageobjects.bathroomfaucets;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class BathroomFaucetsPage extends BasePage {

    @FindBy(xpath = "(//div[@data-dname='brand']//label)[1]")
    private WebElement firstBrandFilterOption;

    @FindBy(xpath = "//ul[@data-dname='Color_Finish_Category']/li")
    private List<WebElement> finishFilterOptions;

    @FindBy(className = "rc-fg-options-box")
    private WebElement searchResultTags;

    public void selectFilterOption(int number){
        wait.until(ExpectedConditions.visibilityOfAllElements(finishFilterOptions)); //temp
        finishFilterOptions.get(number).click();
    }

    @FindBy(id = "totalNumRecs")
    private WebElement resultsInput;

    public WebElement getSearchResultTags() {
        return searchResultTags;
    }

    public WebElement getResultsInput() {
        return resultsInput;
    }

    public BathroomFaucetsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
    public void selectFirstBrandFilterOption() {
        wait.until(ExpectedConditions.visibilityOf(firstBrandFilterOption));
        firstBrandFilterOption.click();
    }

       }

