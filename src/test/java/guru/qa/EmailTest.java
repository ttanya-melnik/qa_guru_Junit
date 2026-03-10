package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты на email рассылку")
public class EmailTest {



  @Test
  @DisplayName("Email должен быть отправлен новому пользователю")
  void emailShouldBeSentForNewUser() {
    System.out.println("Hello World");
  }

  @Test
  @Tag("SMOKE")
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
