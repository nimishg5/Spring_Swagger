package guru.springframework.process;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.codehaus.groovy.transform.AutoCloneASTTransformation;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="globalSession", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SeleniumLogin {
	public WebDriver driver=null;
	static String driverPath = "ChromeDriver"+File.separator+"chromedriver.exe";
	int tab=1;
	private String id;
	private String password;
	boolean OTPVerified = false;
	
	public void setCredentials(String id, String password){
		this.id = id;
		this.password = password;
	}
	
	public boolean testLogin() throws Exception {
		System.out.println("In test method : start");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://globalcommon.uat.citibank.com.sg/SGGCB/JSO/signon/");
		driver.findElement(By.xpath("//*[@id=\"table14\"]/tbody/tr[2]/td[2]/b/input")).sendKeys("aa99098");
		driver.findElement(By.xpath("//*[@id=\"table14\"]/tbody/tr[3]/td[2]/input")).sendKeys("amit@123");//"senden"
		driver.findElement(By.xpath("//*[@id=\"table14\"]/tbody/tr[4]/td[2]/input[6]")).click();
		driver.navigate().to("https://globalcommon.uat.citibank.com.sg/SGGCB/JSO/signon/DisplayUsernameSignon.do");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(id);//new9556
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);//qwerty01
		driver.findElement(By.xpath("//*[@id=\"link_lkSignOn\"]/span")).click();
		//cS-structAccountsPanelContainer class Name
		boolean loginPage = false;
		try{
			loginPage = driver.findElement(By.xpath("//*[@id=\"errorPage\"]/div[1]/div[1]/span/a[2]")).isDisplayed();
		}
		catch(NoSuchElementException e){
			loginPage = false;
		}
		return loginPage;
	}
	
	public StringBuilder fetchAccounts(){
		StringBuilder accountDetails = new StringBuilder();
		try{
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> tableAccPannelContainers =  driver.findElements(By.className("cT-tableAccountsPanelContainer"));
		for (WebElement tabAccPannelContainer : tableAccPannelContainers) {
			System.out.println(tabAccPannelContainer.getText());
			accountDetails.append(tabAccPannelContainer.getText());
		}
		System.out.println(driver.findElements(By.className("cT-categoryTypeHeaderText")).size());
		return accountDetails;
		}
		catch(WebDriverException wde){
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Chrome Driver is closed. Please Relogin and try again.");
			return errorMessage;
		}
	}
	
	public StringBuilder getLastTransaction(String accountNumber, String otp) throws Exception{
		StringBuilder lastTransaction = new StringBuilder();
		final String accountUniqueId = "cmlink_AccountNameLink";
		StringBuilder errorMessage = new StringBuilder();
		int index =1;
		try{
			List<WebElement> accounts =  driver.findElements(By.id(accountUniqueId));
			for(WebElement account : accounts){
				System.out.println(account.getText());
				if(account.getText().contains(accountNumber)){
					WebElement recentTrans = driver.findElement(By.xpath(".//table[@class='cT-tableBase cT-tableAccountsPanel']/tbody["+index+"]/tr[1]/td[1]/div[2]/a"));
					Thread.sleep(5000);
					if(recentTrans.isEnabled()){
						recentTrans.click();
						System.out.println("recent transaction is clicked");
					}else {
						throw new Exception();
					}
					driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
					if(!OTPVerified){
						driver.findElement(By.id("softwareTokenPin")).sendKeys(otp);
						driver.findElement(By.xpath("//div[@class='cA-otpSTC-softwareContentImg cS-clearfix']/div/div/a")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					}
					try{
						Thread.sleep(10000);
						WebElement details = driver.findElement(By.xpath(".//*[@class='cT-fourColumnTransactionTable']"));
						System.out.println(details.getText());
						lastTransaction.append(details.getText());
						driver.findElement(By.xpath("//*[@id='snapshotOverlay-close']")).click();
						OTPVerified = true;
					}
					catch(WebDriverException wde){
						System.out.println("Some exception came");
						errorMessage.append("OTP is invalid!");
						driver.findElement(By.xpath("//*[@id='COACommonOverlay-parent']/span")).click();
						return errorMessage;
					}
					break;
				}
				index++;
			}
			if(lastTransaction.length()<10){
				lastTransaction = new StringBuilder();
				lastTransaction.append("Invalid Account Number for this User ID.");
			}
		return lastTransaction; 
		}
		catch(WebDriverException wde){
			errorMessage.append("Chrome Driver is closed. Please Relogin and try again.");
			return errorMessage;
		}
	}
	
	public void closeBrowser(){
		driver.quit();
	}
}
