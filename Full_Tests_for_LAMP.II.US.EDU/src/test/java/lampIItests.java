import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class lampIItests {
    private static WebDriver driver;
    private static lampIIpage fb;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        fb = new lampIIpage(driver);
    }

    @Test
    public void checkCorrectDataAge20() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for adult correct data, parents No, MedCert Yes");
        writeInputs("Pawel", "Student", "20-12-2001");
        fb.selectMedCertificate(true);
        fb.selectParentControl(false);
        fb.sendForm();
        System.out.println("Pawel Student lat 20, otrzymał grupe:");
        fb.getDataFromFirstAlert(); //Skip first alert
        System.out.println(fb.getDataFromSecondAlert());
    }

    @Test
    public void checkForNumericNameAndSurname() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for numeric name and surname, YES allow,NO medcert");
        writeInputs("123", "456", "20-10-2010");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        fb.getDataFromSecondAlert(); //Skip first alert
        System.out.println(fb.getDataFromSecondAlert());
    }
    @Test
    public void checkForNumericName() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for numeric name, YES allow,NO medcert");
        writeInputs("123", "Student", "20-10-2010");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        fb.getDataFromSecondAlert(); //Skip first alert
        System.out.println(fb.getDataFromSecondAlert());
    }
    @Test
    public void checkForNumericSurname() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for numeric surname, YES allow,NO medcert");
        writeInputs("Pawel", "456", "20-10-2010");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        fb.getDataFromSecondAlert(); //Skip first alert
        System.out.println(fb.getDataFromSecondAlert());
    }

    @Test
    public void checkForWrongYear() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for wrong year");
        writeInputs("123", "456", "20-20-2030");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        System.out.println(fb.getDataFromSecondAlert());
        System.out.println(fb.getDataFromSecondAlert());
    }

    @Test
    public void checkForWrongMonth() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for wrong month");
        writeInputs("123", "456", "20-20-2021");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        System.out.println(fb.getDataFromSecondAlert());
        System.out.println(fb.getDataFromSecondAlert());
    }

    @Test
    public void checkForWrongDay() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for wrong day");
        writeInputs("123", "456", "32-10-2010");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        fb.sendForm();
        System.out.println(fb.getDataFromSecondAlert());
        System.out.println(fb.getDataFromSecondAlert());
    }

    @Test
    public void whichGroupHavePeopleAgedFrom1to21() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for ppl aged from 1 to 21yo, both Checkboxes checked");
        fb.selectMedCertificate(true);
        fb.selectParentControl(true);
        writeDiffPeople(71);
    }
    @Test
    public void manyPeopleWithDifferentAgeNoMedCertificate() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for ppl aged from 1 to 21yo, NO med certificate");
        fb.selectParentControl(true);
        fb.selectMedCertificate(false);
        writeDiffPeople(71);
    }
    @Test
    public void manyPeopleWithDifferentAgeNoParentsAllow() {
        iniDriverAndMaximalizeWindow();
        System.out.println("Test for ppl aged from 1 to 21yo, NO parentsAllow");
        fb.selectParentControl(false);
        fb.selectMedCertificate(true);
        writeDiffPeople(71);
    }

    public void writeDiffPeople(int endIndex){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int stop = year - endIndex;
        while(year >= stop) {
            String date = "01-01-";

            date += String.valueOf(year);
            System.out.println(date);

            writeInputs("Ranged", "Aged", date);
            fb.sendForm();
            fb.getDataFromSecondAlert(); //Skip first alert
            System.out.println(fb.getDataFromSecondAlert() + "\n");
            year--;
        }
    }

    public void iniDriverAndMaximalizeWindow() {
        driver.get("https://lamp.ii.us.edu.pl/~mtdyd/zawody/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void writeInputs(String name, String surname, String birthday) {
        fb.writeIntoName().sendKeys(name);
        fb.writeIntoSurname().sendKeys(surname);
        fb.writeIntoBirthdate().sendKeys(birthday);
    }

    @AfterClass
    public static void exitSetup() {
        //comment line below to the see process
        driver.quit();
    }
}
