package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	
	
	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://global.almosafer.com/en";
	Random rand = new Random();
    String ExpectedDefaultLanage = "en";
    
    
    
    @BeforeTest
    public void mySetup() {
    	driver.manage().window().maximize();
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	
    	driver.get(AlmosaferURL);
    	
    	driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
    		
    	
    	
    }
    
    @Test(priority = 1)
    public void CheckTheDefaultLangugeIsEnglish() {
    	
    	String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
    	Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);
    	
    }
    
	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		
		String ExpectedCurrency = "SAR";
		
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualCurrency = Currency.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	
	@Test(priority = 3)
	public void CheckContactNumber() {
		
		String ExpectedContactNumber = "+966554400000";
		
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
	}
	@Test(priority = 4)
	public void CheckQitagLogo() {
		
		boolean ExpectedResultsForTheLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		WebElement logo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		
		boolean ActualResultForThelogo = logo.isDisplayed();
		Assert.assertEquals(ActualResultForThelogo, ExpectedResultsForTheLogo);
	}
	
	
	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {
		String expectedValue = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, expectedValue);
	}
@Test(priority = 6)
	
	public void CheckDepatureDate() {
		
		LocalDate todayDate = LocalDate.now();
		
		
	int Today  =todayDate.getDayOfMonth();
	
	System.out.println(Today+22);
	
	int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	int ThedayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth(); 
	
	List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG")); 

 	String  ActualDepatureDate = depatureAndArrivalDates.get(0).getText(); 
 	String ActualReturnDate = depatureAndArrivalDates.get(1).getText(); 
 	
 	
 	int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate); 
 	int ActualreturnDateAsInt = Integer.parseInt(ActualReturnDate); 

 	Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
 	Assert.assertEquals(ActualreturnDateAsInt, ThedayAfterTomorrow);
	
		
	}

        @Test(priority = 7)

       public void RandomlyChangeTheLanguage() {
	   String [] URLs = {"https://www.almosafer.com/en","https://www.almosafer.com/ar",};
	   
	   int RandomIndex = rand.nextInt(URLs.length) ; 
			 
			 driver.get(URLs[RandomIndex]); 
			 	
}
   
        @Test(priority = 8)

    	public void fillHotelTab() {
    		
    		String [] EnglishCities = {"Dubbai","Jeddah","riyadh"};
    		int randomEnglishCity = rand.nextInt(EnglishCities.length);
    		String [] ArabicCities = {"دبي","جدة"}; 
    		int randomArabicCity = rand.nextInt(ArabicCities.length);


    		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

    		HotelTab.click();
    		WebElement HotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

    		String WebsiteURL = driver.getCurrentUrl();

    		if (WebsiteURL.contains("ar")) {

    			HotelInputField.sendKeys(ArabicCities[randomArabicCity]);
    		} else {
    			HotelInputField.sendKeys(EnglishCities[randomEnglishCity]);

    		}
    		
    		
    		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
    				
    				
    				
    			WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1); 
    			firstResult.click(); 

    	}
        
        @Test(priority = 9)
    	
    	public void RandomlySelectTheNumberOfVistor() {
        	
    	WebElement SelectorofTheVistor = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']")); 

    	Select select  = new Select(SelectorofTheVistor); 
    	
    	int randomIndex = rand.nextInt(2); 
		select.selectByIndex(randomIndex);
		
		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();
        	
        	
        }
        
        @Test(priority = 10)
    	
    	public void CheckThePageFullyLoaded() throws InterruptedException {
    		
    		boolean expectedResult = true ; 
    		Thread.sleep(10000);
    		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText(); 
    		
    		boolean finished = results.contains("وجدنا")||results.contains("found");
    		
    		Assert.assertEquals(finished, expectedResult);
    		
    	
    	}
        
        @Test(priority = 11 )
    	
    	public void SortItemsLowestToHighestPrice() {
    		
    		boolean expectedResults = true  ; 
    		WebElement LowestPriceSortButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
    		
    		LowestPriceSortButton.click();
    		
    		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
    		
    		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));
    		
    		
    		
    		String LowestPrice = AllPrices.get(0).getText(); 
    		String  HighestPrice = AllPrices.get(AllPrices.size()-1).getText();
    		
    		System.out.println(LowestPrice);
    		System.out.println(HighestPrice);

    		

    		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
    		int HighestPriceAsInt = Integer.parseInt(HighestPrice);
    		
    		
    		boolean ActualResults = LowestPriceAsInt< HighestPriceAsInt ;
    		
    	Assert.assertEquals(ActualResults, expectedResults);


    		
    	}


}
