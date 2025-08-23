package tests;

import base.BaseTest;
import config.ConfigReader;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

///*
//Список тест кейсов находится в файле test -> resources -> docs -> TestCases.doc
//*/
public class TitlePageScreenshotTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @AllureId("2")
    @Description("Проверяем верстку заглавной станицы")
    @Severity(SeverityLevel.NORMAL)
    public void testTitlePageLayout() {
        // тестовые данные
        String actualScreensDir = ConfigReader.testDataConfig.folderWithActualScreenshots();
        String expectedScreensDir = ConfigReader.testDataConfig.folderWithExpectedScreenshots();

        String expectedValue = ConfigReader.testDataConfig.expectedValue();
        String actualValue = ConfigReader.testDataConfig.actualValue();
        String imageComparisonResultValue = ConfigReader.testDataConfig.imageComparisonResultValue();

        logger.info("Проверяем на скриншоте верстку заглавной станицы");
        titlePage.waitPageContentDisplayed();
        ImageComparisonResult imageComparisonResult = titlePage
//                .savePageScreenshot(expectedScreensDir, expectedValue)
                .savePageScreenshot(actualScreensDir, actualValue)
                .compareScreenshots(actualValue, expectedValue, imageComparisonResultValue);
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }
}
