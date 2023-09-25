package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.google.common.io.RecursiveDeleteOption;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.DELETE;

public class CardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
      return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Томск");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").doubleClick().sendKeys(DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79300000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на  " + planningDate));
    }

}


