package com.build.qa.build.selenium.pageobjects.shoppingcart;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;


public class ShoppingCartPage extends BasePage {

    @FindBy(css = ".cl-info > p")
    private WebElement productModelNumber;
    @FindBy(xpath = "//input[@name='updateQuantity']")
    private List<WebElement> updateQuantityItem;
    @FindBy(xpath = "(//span[@class='f-bold'])[1]")
    private WebElement Item1Price;
    @FindBy(xpath = "(//span[@class='f-bold'])[2]")
    private WebElement Item2Price;
    @FindBy(xpath = "(//span[@class='f-bold'])[3]")
    public WebElement updatedItem2Price;

    public WebElement getItem1Price() {
        return Item1Price;
    }
    public WebElement getItem2Price() {
        return Item2Price;
    }
    public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
    public String getProductModuleNumber() {
        return productModelNumber.getText();
    }
    public void updateQuantityByIndex(int productIndex) {
        updateQuantityItem.get(productIndex).clear();
        updateQuantityItem.get(productIndex).sendKeys("2");
    }
    public String productQuantityInputValueByIndex(int index) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value;", updateQuantityItem.get(index));
    }
}

