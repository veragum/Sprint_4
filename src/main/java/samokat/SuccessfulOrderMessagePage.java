package samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class SuccessfulOrderMessagePage {
    private final WebDriver webDriver;
    //сообщение о заказе
    private final By orderCreatedMessageLocator = xpath("//div[text()='Заказ оформлен']");

    public SuccessfulOrderMessagePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getSuccessfulOrderText() {
        return webDriver.findElement(orderCreatedMessageLocator).getText();

    }
}
