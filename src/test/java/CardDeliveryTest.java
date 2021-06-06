import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void testInit() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestWithCorrectForm() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(" [data-test-id=notification]").waitUntil(visible, 1500000)
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + date));
    }

    @Test
        void shouldTestWithInCorrectCity() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Роттердам");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=city].input_invalid .input__sub").exists();
    }
    @Test
    void shouldTestWithInCorrectDate() {
        String date = LocalDate.now().minusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=date].input_invalid .input__sub").exists();
    }

    @Test
    void shouldTestWithIncorrectName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Max Pain");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=name].input_invalid .input__sub").exists();
    }

    @Test
    void shouldTestWithoutName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=name].input_invalid .input__sub").exists();
    }

    @Test
    void shouldTestWithoutPhone() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=phone].input_invalid .input__sub").exists();
    }

    @Test
    void shouldTestWithoutAgreement() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id=phone] input").setValue("+79513574532");
        $$("button").find(exactText("Забронировать")).click();
        boolean exists = $("[data-test-id=agreement].input_invalid .checkbox__text").exists();
    }



}
