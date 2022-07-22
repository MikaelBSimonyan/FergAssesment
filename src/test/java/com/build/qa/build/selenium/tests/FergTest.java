package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.bathroomfaucets.BathroomFaucetsPage;
import com.build.qa.build.selenium.pageobjects.bathroomsinkfaucets.BathroomSinkFaucetsPage;
import com.build.qa.build.selenium.pageobjects.shoppingcart.ShoppingCartPage;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;

public class FergTest extends BaseFramework {
    private final String faucetModel = "Moen m6702bn";

    /**
     * Extremely basic test that outlines some basic
     * functionality and page objects as well as assertJ
     */
    @Test
    public void navigateToHomePage() {
        driver.get(getConfiguration("HOMEPAGE"));
        HomePage homePage = new HomePage(driver, wait);

        softly.assertThat(homePage.onHomePage())
                .as("The website should load up with the Build.com desktop theme.")
                .isTrue();
    }

    /**
     * Search for the Moen m6702bn from the search bar
     *
     * @assert: That the product page we land on is what is expected by checking the product brand and product id
     * @difficulty Easy
     */
    @Test
    public void searchForProductLandsOnCorrectProduct() {
        // TODO: Implement this test
        driver.get(getConfiguration("HOMEPAGE"));
        HomePage homePage = new HomePage(driver, wait);
        homePage.searchForAProduct(faucetModel);

        //verify product brand
        assertEquals(homePage.getProductBrandText(), "Moen");
        // verify product ID
        assertEquals(homePage.getProductIdText(), "Part #M6702BN");
    }

    /**
     * Go to the Bathroom Sinks category directly
     * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
     * and add the second product on the search results (Category Drop) page to the cart.
     *
     * @assert: the product that is added to the cart is what is expected
     * @difficulty Easy-Medium
     */
    @Test
    public void addProductToCartFromCategoryDrop() {
        // TODO: Implement this test
        driver.get(getConfiguration("BATHROOMSINKSPAGE"));
        BathroomSinkFaucetsPage bathroomSinkFaucetsPage = new BathroomSinkFaucetsPage(driver, wait);
        bathroomSinkFaucetsPage.addSinkFaucetToTheCartByIndex(1);
        String expectedProductNumber = bathroomSinkFaucetsPage.getSinkFaucetResultsModelNumberByIndex(1);
        ShoppingCartPage shoppingCartPage = bathroomSinkFaucetsPage.navigateToTheShoppingCart();
        assertEquals(expectedProductNumber, shoppingCartPage.getProductModuleNumber());
    }

    /**
     * Add two different finishes of a product (such as Moen m6702bn) to cart,
     * change the quantity of each finish on the cart page
     *
     * @assert that the product and cart total update as expected when the quantity is changed
     * @difficulty Medium-Hard
     */
    @Test
    public void addMultipleCartItemsAndChangeQuantity() {
        // TODO: Implement this test
        driver.get(getConfiguration("HOMEPAGE"));
        HomePage homePage = new HomePage(driver, wait);
        homePage.searchForAProduct(faucetModel);
        homePage.chooseAndAddFinish(homePage.getMoenGentaPartM6702BLMatteBlackColor());
        homePage.chooseAndAddFinish(homePage.getMoenGentaPartM6702BLBrushedNickelColor());

        ShoppingCartPage shoppingCartPage = homePage.navigateToTheShoppingCart();

        String initial1stItemPrice = shoppingCartPage.getItem1Price().getText().replace("$", "");
        String initial2ndItemPrice = shoppingCartPage.getItem2Price().getText().replace("$", "");

        double itemPrice1 = Double.parseDouble(initial1stItemPrice);
        double itemPrice2 = Double.parseDouble(initial2ndItemPrice);

        shoppingCartPage.updateQuantityByIndex(0);
        shoppingCartPage.updateQuantityByIndex(1);

        assertEquals("2", shoppingCartPage.productQuantityInputValueByIndex(0));
        assertEquals("2", shoppingCartPage.productQuantityInputValueByIndex(1));

        shoppingCartPage.genericExplicitWait(shoppingCartPage.getItem1Price());
        shoppingCartPage.genericExplicitWait(shoppingCartPage.getUpdatedItem2Price());

        String updated1stItemPrice = shoppingCartPage.getItem1Price().getText().replace("$", "");
        String updated2ndItemPrice = shoppingCartPage.getUpdatedItem2Price().getText().replace("$", "");
        double updatedItem1 = Double.parseDouble(updated1stItemPrice);
        double updatedItem2 = Double.parseDouble(updated2ndItemPrice);

        // assert price has been changed
        assertEquals(itemPrice1 * 2, updatedItem1, 0);
        assertEquals(itemPrice2 * 2, updatedItem2, 0);
    }


    /**
     * Go to a category drop page (such as Bathroom Faucets) and narrow by
     * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
     *
     * @assert that the correct filters are being narrowed, and the result count
     * is correct, such that each facet selection is narrowing the product count.
     * @difficulty Hard
     */
    @Test
    public void facetNarrowBysResultInCorrectProductCounts() {
        // TODO: Implement this test
        driver.get(getConfiguration("HOMEPAGE"));
        HomePage homePage = new HomePage(driver, wait);
        homePage.navigateToAllProducts();
        homePage.navigateToBathroom();
        BathroomFaucetsPage bathroomFaucetsPage = homePage.navigateToBathroomFaucet();

        int resultsBeforeFilter = Integer.parseInt(bathroomFaucetsPage.getResultsInput().getAttribute("value").replaceAll(",", ""));
        bathroomFaucetsPage.selectFirstBrandFilterOption();
        driver.navigate().refresh();
        bathroomFaucetsPage.selectFilterOption(0);

        wait.until(ExpectedConditions.urlContains("Color_Finish_Category"));
        wait.until(ExpectedConditions.textToBePresentInElement(bathroomFaucetsPage.getSearchResultTags(), "Color/Finish Category:"));
        int resultsAfterApplyingFilters = Integer.parseInt(bathroomFaucetsPage.getResultsInput().getAttribute("value").replaceAll(",", ""));


        assertTrue(resultsBeforeFilter > resultsAfterApplyingFilters);
    }
}
