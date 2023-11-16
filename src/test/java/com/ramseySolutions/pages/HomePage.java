package com.ramseySolutions.pages;

import com.ramseySolutions.utils.Driver;
import com.ramseySolutions.utils.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    List<Map<String, String>> pagesInfoList;

    ExcelUtil excelUtil = new ExcelUtil("src/test/resources/testData/excelFiles/PagesInfo.xlsx", "PagesInfo");

    /**
     * Method takes page name as parameter and returns page title from sheet
     * @param pageName
     * @return
     */
    public String getPageTitleFromSheet(String pageName){
        pageName = pageName.toLowerCase();
        pagesInfoList = excelUtil.getDataList();
        for (Map<String, String> eachPageInfo : pagesInfoList) {
            if(pageName.equals(eachPageInfo.get("pageName"))){
                return eachPageInfo.get("pageTitle");
            }
        }
        return null;
    }

    /**
     * Clicks on Shop The Sale button
     */
    public void clickOnShopTheSaleButton(){
        if(shopTheSaleButton.isDisplayed()) {
            shopTheSaleButton.click();
        }else {
            throw new NoSuchElementException("Shop The Sale WebElement is not present");
        }
    }

    /**
     * Method takes page name as parameter and navigates to that specified page.
     * @param pageName
     */
    public void navigateToPage(String pageName){
        pageName = pageName.toLowerCase();
        pagesInfoList = excelUtil.getDataList();
        if(pageName.contains(" ")) {
            throw new RuntimeException("Please provide only one word in lower case for page name.");
        }
        for (Map<String, String> eachPageInfo : pagesInfoList) {
            if(pageName.equals(eachPageInfo.get("pageName"))){
               Driver.getDriver().get(eachPageInfo.get("pageUrl"));
            }
        }
    }

    @FindBy(xpath = "//a[contains(normalize-space(), 'Shop the Sale')]")
    private WebElement shopTheSaleButton;

}
