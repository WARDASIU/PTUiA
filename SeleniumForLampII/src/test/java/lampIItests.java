import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class lampIItests {
    private static WebDriver driver;
    @BeforeClass
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void test() {
        driver = new ChromeDriver();
        driver.get("https://lamp.ii.us.edu.pl/~mtdyd/zawody/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        lampIIpage fb = new lampIIpage(driver);
        fb.writeIntoName().sendKeys("Pawel");
        fb.writeIntoSurname().sendKeys("Student");
        fb.writeIntoBirthdate().sendKeys("01-01-2000");
        fb.selectMedCertificate().click();
        fb.sendForm();
        System.out.println("Pawel Student lat 21, otrzymał grupe:");
        String exp = "Dorosly";
        Assert.assertEquals(exp,fb.getDataFromSecondAlert());
    }
    @AfterClass
    public static void exitSetup(){
        //comment line below to see results
        driver.quit();
    }
}
