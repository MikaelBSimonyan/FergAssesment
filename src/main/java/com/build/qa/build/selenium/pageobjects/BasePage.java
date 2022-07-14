package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.pageobjects.shoppingcart.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.framework.BaseFramework;

public abstract class BasePage extends BaseFramework {

    @FindBy(css = ".cartitem-count")
    public WebElement shoppingCart;

    public BasePage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage navigateToTheShoppingCart() {
        shoppingCart.click();
        return new ShoppingCartPage(driver, wait);
    }
}
