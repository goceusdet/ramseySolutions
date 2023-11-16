package com.ramseySolutions.pages;

import com.ramseySolutions.utils.BrowserUtils;
import com.ramseySolutions.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    /**
     * Method takes String word/words as parameters and returns a new version of the parameter where each first char of each word is capital character.
     *
     * @param input
     * @return String
     */
    public String eachFirstCharToUpperCase(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equalsIgnoreCase("and")) {
                result.append("and");
            } else {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());
            }

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }


    /**
     * Method takes main menu element name and returns its webelement
     *
     * @param mainMenuName
     * @return
     */
    public WebElement getMainMenuElement(String mainMenuName) {
        mainMenuName = eachFirstCharToUpperCase(mainMenuName);
        return Driver.getDriver().findElement(By.xpath("//ul[@class='rs-PrimaryNav-menu']/li/a[contains(normalize-space(), '" + mainMenuName + "')]"));
    }

    /**
     * Waits for View Cart button to be visible and clicks it.
     */
    public void clickOnViewCartButton(){
        if (!viewCartButton.isDisplayed()) {
            BrowserUtils.waitForVisibility(viewCartButton, 5);
            viewCartButton.click();
        }else{
            viewCartButton.click();
        }
    }

    /**
     * Method clicks on cart icon
     */
    public void clickOnCartIcon(){
        cartIcon.click();
    }

    /**
     * Returns View Cart button as WebElement.
     * @return
     */
    public WebElement getViewCartButton(){
        return viewCartButton;
    }

    @FindBy(xpath = "//a[contains(normalize-space(), 'View Cart')]")
    private WebElement viewCartButton;

    @FindBy(xpath = "//a[starts-with(@aria-label, 'Cart')]")
    private WebElement cartIcon;

}
