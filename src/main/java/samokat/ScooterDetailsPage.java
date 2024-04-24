package samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class ScooterDetailsPage {
    private final WebDriver webDriver;
    //поле "Когда привезти самокат"
    private final By dateInputLocator = xpath("//input[@placeholder='* Когда привезти самокат']");
    //поле "Срок аренды"
    private final By rentPeriodInputLocator = xpath("//div[text()='* Срок аренды']");
    // выпадающий список с количеством дней
    private final By rentPeriodMenuItemLocator = xpath("//div[text()='трое суток']");
    //чек-бокс "Цвет самоката"
    private final By colorLocator = xpath("//input[@id='black']");
    //поле "Комментарий"
    private final By commentsLocator = xpath("//input[@placeholder='Комментарий для курьера']");
    //кнопка "Заказать"
    private final By makeOrderButtonLocator = xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    // кнопка "Да"
    private final By yesButtonLocator = xpath("//button[text()='Да']");

    public ScooterDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillScooterInfo(String date, String comment) {

        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        WebElement rentPeriodInput = webDriver.findElement(rentPeriodInputLocator);
        rentPeriodInput.click();
        WebElement rentPeriodMenuItem = webDriver.findElement(rentPeriodMenuItemLocator);
        rentPeriodMenuItem.click();

        WebElement color = webDriver.findElement(colorLocator);
        color.click();

        WebElement comments = webDriver.findElement(commentsLocator);
        comments.sendKeys(comment);

    }

    public void clickMakeOrder() {
        WebElement makeOrderButton = webDriver.findElement(makeOrderButtonLocator);
        makeOrderButton.click();
    }

    public void clickYesButton() {
        WebElement yesButton = webDriver.findElement(yesButtonLocator);
        yesButton.click();
    }
}