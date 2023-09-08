import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeForm {
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
        String UserName = new String("Ivan ");
        String UserLastName = new String("Ivanov");
        String UserEmail = new String("Ivanovich@ya.ru");
        String UserPhone = new String("9712345674");

        $("#firstName").setValue(UserName);
        $("#lastName").setValue(UserLastName);
        $("#userEmail").setValue(UserEmail);
        $(".custom-control-label").click();
        $("#userNumber").setValue(UserPhone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("p").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.jpg"));
        $("#currentAddress").setValue("Current Address- address");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(UserName+UserLastName),
                text(UserEmail),
                text("Male"),
                text(UserPhone),
                text("Physics"),
                text("1.jpg"),
                text("Current Address- address"),
                text("NCR Delhi"));
        $("#closeLargeModal").click();
    }
}