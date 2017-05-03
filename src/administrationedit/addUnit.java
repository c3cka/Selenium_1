package administrationedit;


import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class addUnit {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String loginError = "Invalid username or password";

        Random r = new Random();

        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/");
        driver.findElement(By.id("username")).sendKeys("demo");
        driver.findElement(By.id("password")).sendKeys("demo");
        driver.findElement(By.xpath("/html/body/div[4]/div/form/div[4]/input")).click();

//	String errorMsg = driver.findElement(By.className("form-error-message")).getText();
//	if(errorMsg.equals(loginError)) {
//		System.out.println("Non-existent user!");
//	}
        //Select "Accommodations" in menu
        driver.findElement(By.xpath("//*[@id='side-menu']/li[7]/a/span")).click();
        if (driver.getTitle().equals("Accommodation administration")) {

            //Click on button "Add new accommodation"
            driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[2]/div/div/div/div[1]/div/div/a")).click();
            if (driver.getTitle().equals("Accommodation add")) {

                //Input data in all required fields	
                //1. Enter titles and descriptions in different languages
                driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("TitleEN");
                driver.findElement(By.xpath("//*[@id='redactor-uuid-0']")).sendKeys("Description EN");

                driver.findElement(By.xpath("//*[@id='accForm']/div[1]/div[2]/div/a[2]")).click();

                driver.findElement(By.xpath("//*[@id='title__hr']")).sendKeys("TitleHR");
                driver.findElement(By.xpath("//*[@id='redactor-uuid-1']")).sendKeys("Description HR");

                driver.findElement(By.xpath("//*[@id='accForm']/div[1]/div[2]/div/a[3]")).click();

                driver.findElement(By.xpath("//*[@id='title__de']")).sendKeys("TitleDE");
                driver.findElement(By.xpath("//*[@id='redactor-uuid-2']")).sendKeys("Description DE");

                driver.findElement(By.xpath("//*[@id='accForm']/div[1]/div[2]/div/a[4]")).click();

                driver.findElement(By.xpath("//*[@id='title__it']")).sendKeys("TitleIT");
                driver.findElement(By.xpath("//*[@id='redactor-uuid-3']")).sendKeys("Description IT");

                //driver.findElement(By.xpath("//*[@id='tab-basic']/div[9]/div[1]/div/ins")).click();
                //2. Choose type of accommodation
                Select accType = new Select(driver.findElement(By.xpath("//*[@id='type']")));
				int rn = r.nextInt(accType.getOptions().size());
                accType.selectByIndex(rn);

                //3. Uncheck advanced payment
                driver.findElement(By.xpath("//*[@id='tab-basic']/div[12]/div[1]/div/ins")).click();

                //4. Enter address
                driver.findElement(By.xpath("//*[@id='address']")).sendKeys("Zagrebačka 7");

                //5. Select location
				driver.findElement(By.xpath("//*[@id=\'location\']")).sendKeys(Keys.DOWN);
				
				driver.findElement(By.xpath("//*[@id=\'tab-basic\']/div[16]/div[1]/div[1]/input")).sendKeys("Rijeka");
				driver.findElement(By.xpath("//*[@id=\'tab-basic\']/div[16]/div[1]/div[1]/a")).click();
				
				//6. Rating
				Select rating = new Select(driver.findElement(By.id("rating")));
				int rat = r.nextInt(rating.getOptions().size() - 1) + 1;
				rating.selectByIndex(rat);
				
				//7. Select Office
				Select office = new Select(driver.findElement(By.id("office")));
				int ofcn = r.nextInt(office.getOptions().size() - 1) + 1;
				office.selectByIndex(ofcn);

                //7. Select owner
//              driver.findElement(By.xpath("//*[@id='ownerId']")).click();
//              driver.findElement(By.xpath("//*[@id='ownerId']")).sendKeys("Igor Kos");
//              driver.findElement(By.xpath("//*[@id='ownerId']")).sendKeys(Keys.TAB);
                WebElement pictureUpload = driver.findElement(By.xpath("//*[@id='featuredImage.choose']"));
				pictureUpload.sendKeys("C:\\Users\\Igor\\Pictures\\Wallpaper\403519.jpg");
                driver.findElement(By.xpath("//*[@id='tab-seasons']/div[1]/div[1]/div/div[2]/a[3]")).click();
                driver.findElement(By.xpath("//*[@id='accForm']/div[2]/div/div/div/div[3]/div/button[1]")).click();
                if (driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[1]")).getText().contains("created")) {
                    System.out.println("Accommodation created!!");
                }
            }
            System.out.println("No adding accommodation");
        } else {
            System.out.println("No accommodation administration!");
        }

        driver.close();
    }
}
