package guru.qa;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

public class DuckDuckGoWebTest {

  @BeforeAll
  static void beforeAll() {
    Configuration.browserSize = "1920x1080"; //  Делаем окно большим, чтобы ничего не съезжало
    Configuration.pageLoadStrategy = "eager"; // Тесты запускаются быстрее
    Configuration.timeout = 5000; // Если элемент не появится за 5 секунд, то тест упадёт
    Configuration.holdBrowserOpen = true;  // После выполнения теста, браузер не закрывается автоматически.

  }
  // открытие страницы перед каждым тестом
  @BeforeEach
  void setUp() {
    open("https://duckduckgo.com/");
  }

// "ValueSource" - один тест - много данных
  @ValueSource(strings = {
      "Selenide", "JUnit 5", "Allure"
  })

  // тесты
  // "ParameterizedTest" - один тест - много данных
  @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
  @Tag("BLOCKER") // выразили серьёзность теста через тег
  void searchResultsShouldNotBeEmpty(String searchQuery) {

    $("#searchbox_input").setValue(searchQuery).pressEnter(); // 'searchQuery' - текст который в параметре
    $$("li[data-layout='organic']")
        .shouldBe(sizeGreaterThan(0)); // проверка, что коллекция не пустая
  }


// "CsvSource" - принимает массив значений, разделённых запятыми,
  // @CsvSource(value = {
  //    "Selenide, selenide.org",
  //    "JUnit 5, junit.org"
  //})


// данные можно хранить в таблице или в файле "CsvFileSource"
  @CsvFileSource(resources = "/testData/searchResultsShouldContainExpectedUrl.csv")
  @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
  @Tag("BLOCKER") // высокий приоритет
  void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
    $("#searchbox_input").setValue(searchQuery).pressEnter();
    $$("li[data-layout='organic']").first().shouldHave(text(expectedLink));


    // проверка, что коллекция не пустая
  }


  @Test
  @Tag("BLOCKER") // высокий приоритет
 // "@DisplayName" - позволяет задать уникальное имя для класса и методов.
  @DisplayName("Для поискового запроса 'junit 5' должен отдавать не пустой список карточек")
  void successfulSearchJUnitTest() {
    $("#searchbox_input").setValue("junit 5").pressEnter();
    $$("li[data-layout='organic']")
        .shouldBe(sizeGreaterThan(0)); // проверка, что коллекция не пустая
  }


  @Test
  @Tag("BLOCKER") // высокий приоритет
  // "@DisplayName" - позволяет задать уникальное имя для класса и методов.
  @DisplayName("Для поискового запроса 'selenide' должен показываться не пустой список фото")
  void successfulSearchPhotoTest() {
    $("#searchbox_input").setValue("selenide").pressEnter();
    $(byTagAndText("a", "Изображения")).shouldBe(visible).click();
    $$("img")
        .shouldHave(sizeGreaterThan(0)); // проверка, что коллекция не пустая
  }
}



