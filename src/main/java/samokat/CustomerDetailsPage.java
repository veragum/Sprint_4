package samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class CustomerDetailsPage {
    private final WebDriver webDriver;
    //поле "Имя"
    private final By nameInputLocator = xpath("//input[@placeholder='* Имя']");
    //поле "Фамилия"
    private final By lastNameInputLocator = xpath("//input[@placeholder='* Фамилия']");
    //поле "Адрес"
    private final By addressInputLocator = xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле "Станция метро"
    private final By subwayInputLocator = xpath("//input[@placeholder='* Станция метро']");
    //выпадающий список станций метро
    private final String stationMenuItemLocator = "//div[text()='%s']";
    // поле "Телефон"
    private final By phoneInputLocator = xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private final By nextButtonLocator = xpath("//button[text()='Далее']");

    public CustomerDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillCustomerInfo(String name, String lastName, String address, String subwayTitle, String phone) {

        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement lastNameInput = webDriver.findElement(lastNameInputLocator);
        lastNameInput.sendKeys(lastName);

        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);

        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();
        WebElement StationMenu = webDriver.findElement(By.xpath(String.format(stationMenuItemLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", StationMenu);
        StationMenu.click();

        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }

    public void clickNextButton() {

        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }

}
