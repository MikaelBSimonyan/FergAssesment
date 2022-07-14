package com.build.qa.build.selenium.pageobjects.bathroomfaucets;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class BathroomFaucetsPage extends BasePage {

    @FindBy(xpath = "(//div[@data-dname='brand']//label)[1]")
    private WebElement firstBrandFilterOption;

//previous locator "//*[@id=\"wrapper\"]/main/div/div/div[1]/div/div[6]/div[2]/ul/li[1]/label/div"
    @FindBy(xpath = "//*[@id=\"wrapper\"]/main/div/div/div[1]/div/div[6]/div[2]/ul/li[1]/label/label")
    private WebElement firstFinishFilterOption;

    @FindBy(xpath = "//div[@class='word total-record']")
    private WebElement resultsTotalNumber;

    public BathroomFaucetsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
    public void selectFirstBrandFilterOption() {
        wait.until(ExpectedConditions.visibilityOf(firstBrandFilterOption));
        firstBrandFilterOption.click();
    }
    public void selectFirstFinishOption() {
        wait.until(ExpectedConditions.visibilityOf(firstFinishFilterOption));

        firstFinishFilterOption.click();
    }
    public String getResultsTotalNumber() {
        return resultsTotalNumber.getText();
    }

    public WebElement getFirstFinishFilterOption() {
        return firstFinishFilterOption;
    }
}
