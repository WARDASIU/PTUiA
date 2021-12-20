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
        element.clear();
        return element;
    }
    public WebElement writeIntoSurname(){
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='Nazwisko']"));
        element.clear();
        return element;
    }
    public WebElement writeIntoBirthdate(){
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='dd-mm-rrrr']"));
        element.clear();
        return element;
    }
    public WebElement selectMedCertificate(boolean checked){
        WebElement element = driver.findElement(By.xpath("//input[@id='lekarz']"));
        if (checked && !element.isSelected()) element.click();
        if (checked && element.isSelected()) return element;
        if (!checked && element.isSelected()) element.click();
        if (!checked && !element.isSelected()) return element;
        return element;
    }
    public WebElement selectParentControl(boolean checked){
        WebElement element = driver.findElement(By.xpath("//input[@id='rodzice']"));
        if (checked && !element.isSelected()) element.click();
        if (checked && element.isSelected()) return element;
        if (!checked && element.isSelected()) element.click();
        if (!checked && !element.isSelected()) return element;
        return element;
    }
    public WebElement sendForm(){
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Wyślij')]"));
        element.click();
        return element;
    }
    public String getDataFromFirstAlert(){
        Alert alert = driver.switchTo().alert();
        String data = alert.getText();
        alert.accept();
        waitThread(1000);
        return data;
    }
    public String getDataFromSecondAlert(){
        Alert alert = driver.switchTo().alert();
        String data = alert.getText();
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
