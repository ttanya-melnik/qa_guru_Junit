package guru.qa.data;


// Перечисляет поддерживаемые языки сайта
//Для каждого языка хранит ожидаемый текст заголовка <h3> после переключения языка.
public enum Language {
  RU("ЧТО ТАКОЕ SELENIDE?"),
  EN("WHAT IS SELENIDE?");

  public final String description;

  Language(String description) {
    this.description = description;
  }
}
