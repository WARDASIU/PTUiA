import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class lampIIpage {
    protected WebDriver driver;

    public lampIIpage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement writeIntoName(){
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='Imię']"));
        return element;
    }
    public WebElement writeIntoSurname(){
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='Nazwisko']"));
        return element;
    }
    public WebElement writeIntoBirthdate(){
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='dd-mm-rrrr']"));
        return element;
    }
    public WebElement selectMedCertificate(){
        WebElement element = driver.findElement(By.xpath("//input[@id='lekarz']"));
        return element;
    }
    public WebElement sendForm(){
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Wyślij')]"));
        element.click();
        return element;
    }
    public String getDataFromSecondAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        waitThread(1000);

        alert = driver.switchTo().alert();
        String data = alert.getText();
        System.out.println(data);
        alert.accept();
        return data;
    }

    public void waitThread(int milsec){
        try {
            Thread.sleep(milsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
