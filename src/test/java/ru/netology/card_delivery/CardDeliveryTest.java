package ru.netology.card_delivery;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public LocalDate changeDay() {
        LocalDate date = LocalDate.now();
        return LocalDate.now().plusDays(3);
    }

    String date = String.valueOf(changeDay());

    @Test
    public void shouldTest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79300000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));

    }

}

