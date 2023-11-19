package com.ramseySolutions.pages;

import com.ramseySolutions.utils.BrowserUtils;
import com.ramseySolutions.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

    /**
     * Method takes item name from cart as parameter and returns a Webelement.
     *
     * @param itemName
     * @return
     */
    public WebElement getItemInCart(String itemName) {
        //BrowserUtils.waitForPageToLoad(5);
        return Driver.getDriver().findElement(By.xpath("//td[@class='rs-CartItem-detail rs-CartItem-title']//h2[contains(normalize-space(), '" + itemName + "')]"));
    }

    /**
     * Method takes item name as parameter and returns item quantity in cart.
     * @param itemName
     * @return
     */
    public int getQuantityOfItemInCart(String itemName) {
        WebElement item = Driver.getDriver().findElement(By.xpath("//td[@class='rs-CartItem-detail rs-CartItem-title']//h2[contains(normalize-space(), '" + itemName + "')]/../..//following-sibling::td//input"));
        return Integer.parseInt(item.getAttribute("value"));
    }

    /**
     * Methos takes item name as parameter and deletes specified item from cart. Waits until element is invisible.
     * @param itemName
     */
    public void deleteItemFromCart(String itemName, int quantityToBeDeleted){

        wait.until(ExpectedConditions.invisibilityOf(getViewCartButton()));

        WebElement element = Driver.getDriver().findElement(By.xpath("//h2[contains(normalize-space(), '"+itemName+"')]/../..//following-sibling::td//button/span[starts-with(., 'Decrease')]/.."));

        BrowserUtils.waitForClickablility(element, 10);

        for (int i = 0; i < quantityToBeDeleted; i++) {
            element.click();
        }

        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Methos takes item name as parameter and deletes specified item from cart.
     * @param itemName
     */
    public void deleteItemFromCart(String itemName){

        wait.until(ExpectedConditions.invisibilityOf(getViewCartButton()));

        WebElement deleteIcon = Driver.getDriver().findElement(By.xpath("//button[@aria-label='Remove "+itemName+" from cart']"));

        BrowserUtils.waitForClickablility(deleteIcon, 10).click();

        BrowserUtils.waitForClickablility(deleteItemConfirmation, 10).click();

        wait.until(ExpectedConditions.invisibilityOf(getItemInCart(itemName)));
    }

    /**
     * Method takes cart item name as parameter ands returns specified item as WebElement.
     * @param itemName
     * @return
     */
    public String getItemFromList(String itemName){
        for (WebElement eachItem : listOfItemsInCart) {
            if(eachItem.getText().equals(itemName)) {
                return eachItem.getText();
            }
        }
       return "Element is not present in the list";
    }

    @FindBy(xpath = "//td[@class='rs-CartItem-detail rs-CartItem-title']//h2[contains(normalize-space(), '')]")
    private List<WebElement> listOfItemsInCart;

    @FindBy(xpath = "//div[@class='button-container rs-Alert-buttonContainer']/button[.='OK']")
    private WebElement deleteItemConfirmation;

}
