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
    @Description("Проверяем верстку заглавной страницы")
    @Severity(SeverityLevel.NORMAL)
    public void testTitlePageLayout() {
        // тестовые данные
        String actualScreensDir = ConfigReader.testDataConfig.folderWithActualScreenshots();
        String expectedScreensDir = ConfigReader.testDataConfig.folderWithExpectedScreenshots();

        String expectedContentValue = ConfigReader.testDataConfig.expectedContentValue();
        String expectedLogoValue = ConfigReader.testDataConfig.expectedLogoValue();

        String actualContentValue = ConfigReader.testDataConfig.actualContentValue();
        String actualLogoValue = ConfigReader.testDataConfig.actualLogoValue();

        String contentComparisonResultValue = ConfigReader.testDataConfig.contentComparisonResultValue();
        String logoComparisonResultValue = ConfigReader.testDataConfig.logoComparisonResultValue();

        logger.info("ШАГ1 - Проверяем на скриншоте верстку логотипа заглавной станицы");
        ImageComparisonResult imageComparisonResult = titlePage
//                .saveLogoPageScreenshot(expectedScreensDir, expectedLogoValue)
                .saveLogoPageScreenshot(actualScreensDir, actualLogoValue)
                .compareScreenshots(actualLogoValue, expectedLogoValue, logoComparisonResultValue);
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());

        logger.info("ШАГ2 - Проверяем на скриншоте верстку содержимого заглавной станицы");
        imageComparisonResult = titlePage
//                .saveContentPageScreenshot(expectedScreensDir, expectedContentValue)
                .saveContentPageScreenshot(actualScreensDir, actualContentValue)
                .compareScreenshots(actualContentValue, expectedContentValue, contentComparisonResultValue);
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }
}
