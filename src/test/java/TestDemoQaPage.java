import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoQaPage {
    @BeforeAll
    static void beforeAll(){
        Configuration.holdBrowserOpen = false;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy="eager";
    }
    @Test
    void practiceFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        String userName = "TestUserName";
        String userLastName = "TestLastName";
        String userEmail = "Test@test.com";
        String userPhone = "9123456789";
        $("#firstName").setValue(userName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);
        $(".custom-control-label").click();
        $("#userNumber").setValue(userPhone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--003").click();
        $("#subjectsInput").setValue("p").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.png"));
        $("#currentAddress").setValue("TEST Address - 123");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(userName+' '+userLastName),
                text(userEmail),
                text("Male"),
                text(userPhone),
                text("Physics"),
                text("1.png"),
                text("TEST Address - 123"),
                text("NCR Delhi"));
        $("#closeLargeModal").click();
    }
}
