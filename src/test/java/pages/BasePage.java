package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    static {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized"); // Establecer el argumento para iniciar en pantalla completa
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void closeDriver() {
        driver.quit();
    }

    public WebElement find(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        find(locator).click();
    }

    private WebElement findByLinkText(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
    }


    public void clickElementByLinkText(String searchButton) {
        findByLinkText(searchButton);
    }

    public void write(String locator, String textToWrite) {
        find(locator).clear();
        find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropDownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropDownByIndex(String locator, int index) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(index);
    }

    public void selectFromDropDownByTexx(String locator, String text) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(text);
    }

    public void hoverOverElement(String locator) {
        actions.moveToElement(find(locator));
    }

    public void doubleClick(String locator) {
        actions.doubleClick(find(locator));
    }

    public void rightClick(String locator) {
        actions.contextClick(find(locator));
    }

    public void switchToiFrame(int iFrameIndex) {
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    public String getTextFromElement(String locator) {
        return find(locator).getText();
    }

    public List<WebElement> getSubElemetsByClassName(String locator, String className) {
        return find(locator).findElements(By.className(className));
    }

    public boolean elementIsDisplay(String locator) {
        return find(locator).isDisplayed();
    }

    public boolean elementIsEnabled(String locator) {
        return find(locator).isEnabled();
    }

    public boolean elementIsDisplaySelected(String locator) {
        return find(locator).isSelected();
    }
}
