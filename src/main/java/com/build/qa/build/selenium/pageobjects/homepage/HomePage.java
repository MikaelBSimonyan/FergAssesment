package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.bathroomfaucets.BathroomFaucetsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

import java.util.List;

public class HomePage extends BasePage {

    private final By homePageWrapper;
    @FindBy(xpath = "//*[@class='text-input search react-search-input-normal js-reload-value']")
    private WebElement searchInput;
    @FindBy(xpath = "//h2[@class='product__brand']")
    private WebElement productBrand;
    @FindBy(xpath = "//span[.='Part #M6702BN']")
    private WebElement productId;
    @FindBy(css = ".js-add-to-cart ")
    private WebElement addToCartButton;
    @FindBy(xpath = "//*[@id=\"js-pls-select-container\"]/li[2]/a")
    private WebElement MoenGentaPartM6702BLMatteBlackColor;
    @FindBy(xpath = "//img[@title='Brushed Nickel']")
    private WebElement MoenGentaPartM6702BLBrushedNickelColor;
    @FindBy(xpath = "//a[text()='All Products']")
    private WebElement allProductsDropDown;
    @FindBy(css = "#all-products .mega-nav-secondary-dropdown .mega-nav-flyout ")
    private List<WebElement> bathroomOption;
    @FindBy(xpath = "//div[text()='Bathroom Sink Faucets']")
    private WebElement bathroomSinkFaucetsOptions;

    public HomePage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        homePageWrapper = By.cssSelector("#wrapper.homepage");
    }

    public boolean onHomePage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
    }

    public WebElement getMoenGentaPartM6702BLMatteBlackColor() {
        return MoenGentaPartM6702BLMatteBlackColor;
    }

    public WebElement getMoenGentaPartM6702BLBrushedNickelColor() {
        return MoenGentaPartM6702BLBrushedNickelColor;
    }

    public String getProductBrandText() {
        return productBrand.getText();
    }

    public String getProductIdText() {
        return productId.getText();
    }

    public void searchForAProduct(String productName) {
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(productBrand));
    }

    public void chooseAndAddFinish(WebElement productFinish) {
        productFinish.click();
        addToCartButton.click();
    }

    public void navigateToAllProducts() {
        wait.until(ExpectedConditions.visibilityOf(allProductsDropDown));
        focusElement(allProductsDropDown);
    }

    public void navigateToBathroom() {
        focusElement(bathroomOption.get(0));
    }

    public BathroomFaucetsPage navigateToBathroomFaucet() {
        wait.until(ExpectedConditions.visibilityOf(bathroomSinkFaucetsOptions));
        bathroomSinkFaucetsOptions.click();
        return new BathroomFaucetsPage(driver, wait);
    }

    public void focusElement(WebElement element) {
        String javaScript = "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "arguments[0].dispatchEvent(evObj);";

        ((JavascriptExecutor) getDriver()).executeScript(javaScript, element);
    }
}
