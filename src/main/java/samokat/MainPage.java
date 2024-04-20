package samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;


public class MainPage {
    public final WebDriver webDriver;
    //кнопка "Заказать" вверху страницы
    private final By createOrderUpperButtonLocator = xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //кнопка "Заказать" в середине страницы
    private final By createOrderLowerButtonLocator = xpath("//div[contains(@class, 'FinishButton')]/button[text()='Заказать']");
    //кнопка "Да все привыкли" в сообщении про Cookie

    private final By cookiesButtonLocator = xpath("//button[text()='да все привыкли']");
    //выпадающее поле с вопросом о важном
    private final String questionLocator = "accordion__heading-%s";
    //выпадающее поле с ответом о важном
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickCreateOrderUpperButton() {
        WebElement CreateOrderButton = webDriver.findElement(createOrderUpperButtonLocator);
        CreateOrderButton.click();
    }

    public void clickCreateOrderLowerButton() {
        WebElement CreateOrderButton = webDriver.findElement(createOrderLowerButtonLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", CreateOrderButton);
        new WebDriverWait(webDriver, ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(CreateOrderButton));
        CreateOrderButton.click();
    }

    public void closeCookieWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
