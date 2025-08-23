package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

/*
Список тест кейсов находится в файле test -> resources -> docs -> TestCases.doc
*/
public class TitlePageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @AllureId("1")
    @Description("Проверяем, что основные элементы страницы отображаются на заглавной странице")
    @Severity(SeverityLevel.CRITICAL)
    public void testUserMainTitlePageElements() {
        // тестовые данные
        List<String> texts = List.of("Полная обработка",
                "Сценарий обработки для получения всех возможных данных документа",
                "Банковская карта",
                "Сценарий обработки для получения данных банковской карты",
                "MRZ",
                "Сценарий обработки для получения данных из машиносчитываемой зоны",
                "Штрихкод",
                "Сценарий обработки для получения данных из штрихкода",
                "Визуальная зона",
                "Сценарий обработки для получения данных из визуальной зоны");

        logger.info("Проверяем видимость основных элементов заглавной страницы");
        titlePage.waitPageContentDisplayed();
        var softAssert = new SoftAssert();
        softAssert.assertTrue(titlePage.isPageLogoDisplayed(), "НE отображается логотип заглавной страницы");
        texts.forEach( text -> softAssert.assertTrue(titlePage.isScenarioGridElementDisplayed(text),
                "В таблице сценариев НЕ отображается элемент с тектом: " + text));
        softAssert.assertTrue(titlePage.checkAllScenarioGridIconsAreDisplayed(),
                "В таблице результатов НЕ отображается часть иконок");
        softAssert.assertTrue(titlePage.isCameraButtonDisplayed(),
                "НЕ отображается кнопка камеры внизу страницы");
        softAssert.assertTrue(titlePage.isMenuButtonDisplayed(),
                "НЕ отображается кнопка меню внизу страницы");
        softAssert.assertTrue(titlePage.isGalleryButtonDisplayed(),
                "НЕ отображается кнопка галереи внизу страницы");
        softAssert.assertAll();
    }
}
