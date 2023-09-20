import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

    public class SearchSoftAssertionsTest {
        @BeforeAll
        static void beforeAll() {
            Configuration.pageLoadStrategy = "eager";
            Configuration.holdBrowserOpen = false;
            Configuration.baseUrl = "https://github.com/";
            Configuration.browserSize = "1920x1080";
        }

        @Test
        void searchJunitText() {
            open("/selenide/selenide");
            //Перейдите в раздел Wiki проекта
            $("#wiki-tab").click();
            //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
            $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
            $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
            //Откройте страницу SoftAssertions
            $("#wiki-pages-box").$(byText("SoftAssertions")).click();
            //Проверьте что внутри есть пример кода для JUnit5
            $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
            $(".markdown-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                    "class Tests {\n" +
                    "  @Test\n" +
                    "  void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}"));
            $(".markdown-body").shouldHave(text("class Tests {\n" +
                    "  @RegisterExtension \n" +
                    "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                    "\n" +
                    "  @Test\n" +
                    "  void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}"));
        }
    }

