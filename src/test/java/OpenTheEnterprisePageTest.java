import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OpenTheEnterprisePageTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void openPageAndFoundHeaderTest() {
        open(Configuration.baseUrl);
        // найти в меню Solutions -> Enterprise
        $(byTagAndText("button", "Solutions")).hover();
        // перейти на страницу Enterprise
        $(byText("Enterprise")).click();
        // проверить, что открылась нужная страница
        $(".application-main").shouldHave(text("Build like the best"));
    }
}