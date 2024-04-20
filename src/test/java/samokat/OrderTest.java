package samokat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private final String name;
    private final String lastName;
    private final String address;
    private final String subwayTitle;
    private final String phone;

    public OrderTest(String name, String lastName, String address, String subwayTitle, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subwayTitle = subwayTitle;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Анна", "Иванова", "Красноармейская, 15", "Аэропорт", "89177099999"},
                {"Ян", "Ли", "пл.Ленина, 1", "Спортивная", "88888888888"},
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createOrderWithUpperButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderUpperButton();
        commonOrderLogic();
    }

    @Test
    public void createOrderWithLowerButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderLowerButton();
        commonOrderLogic();
    }

    private void commonOrderLogic() {
        CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(webDriver);
        customerDetailsPage.fillCustomerInfo(name, lastName, address, subwayTitle, phone);
        customerDetailsPage.clickNextButton();

        ScooterDetailsPage scooterDetailsPage = new ScooterDetailsPage(webDriver);
        scooterDetailsPage.fillScooterInfo("01.01.2025", "Во дворе добрая собака!");

        scooterDetailsPage.clickMakeOrder();
        scooterDetailsPage.clickYesButton();

        SuccessfulOrderMessagePage successfulOrderMessagePage = new SuccessfulOrderMessagePage(webDriver);

        MatcherAssert.assertThat(successfulOrderMessagePage.getSuccessfulOrderText(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
