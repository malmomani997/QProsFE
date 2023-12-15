package QProsFETask.TestHelpers;

import QProsFETask.PagesHelper.CartPageHelper;
import QProsFETask.PagesHelper.HomePageHelper;
import QProsFETask.PagesHelper.OrderDetailsPageHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class BaseTest {

    private static WebDriver driver;
    private HomePageHelper homePageHelper;
    private CartPageHelper cartPageHelper;
    private OrderDetailsPageHelper orderDetailsPageHelper;

    private static final String GLOBAL_DATA_PATH = "//src/main/java/QProsFETask/Resources/GlobalData.properties";
    private static final String EXTENSIONS_PATH = "//src/Extensions/";
    private static final String REPORTS_PATH = "/reports/";
    private static final String SCREENSHOTS_PATH = "/screenshots/";

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws IOException {
        setDriver(initializeDriver(browser));
        getDriver().get("https://practice.automationtesting.in/");
        closeExtraTabs(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getMethod().getMethodName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver initializeDriver(String browser) throws IOException {
        Properties prop = loadProperties();
        String browserName = browser != null ? browser : prop.getProperty("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(getEdgeOptions());
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        configureDriver();
        return driver;
    }

    private Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + GLOBAL_DATA_PATH);
        prop.load(fis);
        return prop;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addExtensions(new File(System.getProperty("user.dir") + EXTENSIONS_PATH + "AdBlock.crx"));
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(new File(System.getProperty("user.dir") + EXTENSIONS_PATH + "adblockFireFox.xpi"));
        options.setProfile(profile);
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        return new EdgeOptions();
    }

    private void configureDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePageHelper = new HomePageHelper(driver);
        cartPageHelper = new CartPageHelper(driver);
        orderDetailsPageHelper = new OrderDetailsPageHelper(driver);
    }

    @Parameters("browser")
    private void closeExtraTabs(String browser) {
        String browserName = getBrowserName();
        if (browser.equalsIgnoreCase(browserName)) {
            Set<String> windowHandles = driver.getWindowHandles();
            if (windowHandles.size() > 1) {
                driver.switchTo().window(windowHandles.toArray(new String[0])[1]);
                driver.close();
                driver.switchTo().window(windowHandles.toArray(new String[0])[0]);
            }
        }
    }

    private String getBrowserName() {
        Properties prop;
        try {
            prop = loadProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties: " + e.getMessage());
        }
        return prop.getProperty("browser");
    }

    @BeforeSuite
    public void cleanUp() {
        cleanupExtentReport();
    }

    public void cleanupExtentReport() {
        File reportsDirectory = new File(System.getProperty("user.dir") + REPORTS_PATH);
        if (reportsDirectory.exists() && deleteDirectory(reportsDirectory)) {
            System.out.println("Reports directory and its contents deleted successfully.");
        } else {
            System.err.println("Failed to delete the reports directory or directory does not exist.");
        }
    }

    private boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
        }
        return directory.delete();
    }

    public String captureScreenshot(String testCaseName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotFileName = testCaseName + "_" + timestamp + ".png";
            String screenshotPath = System.getProperty("user.dir") + REPORTS_PATH + SCREENSHOTS_PATH + screenshotFileName;
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot captured: " + destination.getAbsolutePath());
            return screenshotPath;
        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            return null;
        }
    }

    // Getter methods for helper classes if needed
    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public CartPageHelper getCartPageHelper() {
        return cartPageHelper;
    }

    public OrderDetailsPageHelper getOrderDetailsPageHelper() {
        return orderDetailsPageHelper;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}