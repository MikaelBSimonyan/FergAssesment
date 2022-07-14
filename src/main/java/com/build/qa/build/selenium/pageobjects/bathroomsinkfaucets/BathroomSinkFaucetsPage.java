package com.build.qa.build.selenium.pageobjects.bathroomsinkfaucets;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class BathroomSinkFaucetsPage extends BasePage {

    @FindBy(xpath = "//div[@class='js-grid-colorItem sku-item ']//p[contains(text(),'Add to Cart')]")
    private List<WebElement> sinkResultsAddToCardButton;
    @FindBy(xpath = "//div[@class='js-grid-colorItem sku-item ']//a[@class='fz-13 link-black sr-fg-content-fastcode']/span")
    private List<WebElement> sinkFaucetResultsModalNumber;
    @FindBy(css = ".jq-quick-view-add-to-cart")
    private WebElement addToCartButton;
    @FindBy(css = ".modal-header")
    private WebElement popUpModuleHeader;

    public BathroomSinkFaucetsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public void addSinkFaucetToTheCartByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(sinkResultsAddToCardButton));
        sinkResultsAddToCardButton.get(index).click();
        wait.until(ExpectedConditions.visibilityOf(popUpModuleHeader));
        addToCartButton.click();
    }

    public String getSinkFaucetResultsModelNumberByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(sinkFaucetResultsModalNumber));
        return sinkFaucetResultsModalNumber.get(index).getText();
    }
}
