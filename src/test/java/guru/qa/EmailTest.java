package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;


// Это учебный / демонстрационный набор тестов, который показывает:

//Как выглядят разные варианты аннотаций в JUnit 5
//Как называть тесты по-русски красиво (@DisplayName)
//Как ставить теги (@Tag, @Tags)
//Как выглядит падающий тест (throw AssertionError)
//Что даже пустой тест с println считается успешным



// @DisplayName - меняет название теста в отчете
@DisplayName("Тесты на email рассылку")
public class EmailTest {



  @Test
  @DisplayName("Email должен быть отправлен новому пользователю")
  void emailShouldBeSentForNewUser() {
    System.out.println("Hello World");
  }

  @Test
  @Tag("SMOKE") // запускает только смок тесты
  @DisplayName("Email должен быть отправлен заблокированному пользователю")
  void emailShouldBeSentForBannedUser() {
    System.out.println("Hello World");
  }

  @Test
  @Tags({
      @Tag("WEB"),
      @Tag("SMOKE"),
  }
  )
  @DisplayName("Email должен быть отправлен в случае изменения PaymentMethod")
  void emailShouldBeSentAfterChangePaymentMethod() {
    throw new AssertionError("Падаем!");
  }
}
