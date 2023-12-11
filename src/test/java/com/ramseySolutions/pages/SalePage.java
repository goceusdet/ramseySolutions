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

    List<String> list;



    /**
     * Method returns a sale item from UI sales list. Takes item name as parameter.
     * @param itemName
     * @return
     */
    public WebElement getItem(String itemName){
        for (int i = 0; i < paginationList.size(); i++) {
            for (int j = 1; j <= Driver.getDriver().findElements(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li/article")).size(); j++) {

                BrowserUtils.waitFor(1);

                if(Driver.getDriver().findElement(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li/article[@data-position='" + j + "']")).getAttribute("data-name").equals(itemName)){
                    return Driver.getDriver().findElement(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li/article[@data-position='" + j + "']"));
                }else {
                    //clicking on next page numbers:
                    BrowserUtils.waitFor(1);
                    Driver.getDriver().findElement(By.xpath("//ul[@class = 'rs-Pagination-innerList']/li/a[@aria-label = 'Page " + (i + 1) + " of " + paginationList.size() + "']")).click();
                }
            }
        }
        return null;
    }

    /**
     * Method returns list of Sale items names.
     *
     * @return
     */
    public List<String> getSaleItemsNamesList() {

        list = new ArrayList<>();
        System.out.println("paginationList.size() = " + paginationList.size());

        try {
            for (int i = 0; i < paginationList.size(); i++) {

                System.out.println("Page: " + (i + 1));
                BrowserUtils.waitForPageToLoad(10);

                for (int j = 1; j <= Driver.getDriver().findElements(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li/article")).size(); j++) {

                    BrowserUtils.waitFor(1);

                    list.add(Driver.getDriver().findElement(By.xpath("//ul[@class='rds-Grid rs-ProductGrid']/li/article[@data-position='" + j + "']")).getAttribute("data-name"));
                }

                //clicking on next page numbers:
                BrowserUtils.waitFor(1);
                Driver.getDriver().findElement(By.xpath("//ul[@class = 'rs-Pagination-innerList']/li/a[@aria-label = 'Page " + (i + 1) + " of " + paginationList.size() + "']")).click();
            }
            System.out.println("saleItemListSize = " + list.size());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Clicks on Add To Cart Button. Throws No Such Element exception if Add to cart button, Out of Stock message and Sold Out message are displayed.
     */
    public void clickOnAddToCartButton() {
        try {
            if (!addToCartButton.isDisplayed() && (outOfStockMessage.isDisplayed() || soldOutMessage.isDisplayed())) {
                throw new NoSuchElementException("Item is out of stock or sold out. Please select a different item");
            } else {
                addToCartButton.click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * User adds Sale Item to cart by specifying item name.
     *
     * @param itemName
     */
    public void addItemToCart(String itemName) {
        try {
            getItem(itemName).click();
            clickOnAddToCartButton();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @FindBy(xpath = "//ul[@class = 'rs-Pagination-innerList']/li/a")
    private List<WebElement> paginationList;

    @FindBy(xpath = "//input[@id='form-action-addToCart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@aria-label='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//p[@class='alertBox-column alertBox-message']/span[.='Out of Stock']")
    private WebElement outOfStockMessage;

    @FindBy(xpath = "//span[.='Sold Out!']")
    private WebElement soldOutMessage;
}
