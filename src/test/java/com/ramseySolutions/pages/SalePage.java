package com.ramseySolutions.pages;

import com.ramseySolutions.utils.BrowserUtils;
import com.ramseySolutions.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SalePage extends BasePage {


    /**
     * Method takes sale item name as parameter and returns sale item as Webelement.
     *
     * @param saleItemName
     * @return
     */
    public WebElement getItem(String saleItemName) {
        BrowserUtils.waitForPageToLoad(5);
        return Driver.getDriver().findElement(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li//h3[contains(normalize-space(), '" + saleItemName + "')]/../.."));
    }

    /**
     * Method takes sale item name as parameter and returns sale item as Webelement.
     *
     * @param itemName
     * @return
     */
    public WebElement getSaleItem(String itemName) {
        for (String eachSaleItemName : getSaleItemsNamesList()) {
            if (!eachSaleItemName.equals(itemName)) {
                BrowserUtils.hover(nextButton);
                nextButton.click();
            }
            return getItem(itemName);
        }
        return null;
    }

    /**
     * Method returns a list of sale items as WebElements.
     *
     * @return
     */
    public List<WebElement> getSaleItemsList() {
        return saleItemsList;
    }

    /**
     * Method returns list of Sale items names.
     *
     * @return
     */
    public List<String> getSaleItemsNamesList() {
        BrowserUtils.waitForPageToLoad(10);
        List<String> list = new ArrayList<>();
        for (WebElement eachSaleItem : saleItemsList) {
            list.add(eachSaleItem.getText());
        }
        return list;
    }

    /**
     * Clicks on Add To Cart Button
     */
    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    /**
     * User adds Sale Item to cart by specifying item name.
     *
     * @param itemName
     */
    public void addItemToCart(String itemName) {
        BrowserUtils.waitForVisibility(getSaleItem(itemName), 5);
        getSaleItem(itemName).click();
        clickOnAddToCartButton();
    }

    @FindBy(xpath = "//ul[@class='rds-Grid rs-ProductGrid']/li//h3/a[contains(normalize-space(), '')]")
    private List<WebElement> saleItemsList;

    @FindBy(xpath = "//input[@id='form-action-addToCart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@aria-label='Next']")
    private WebElement nextButton;


}
