/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.automation;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Administrador
 */
public class AutomationTest extends TestCase {

    public void testAutomation() {

        System.setProperty("webdriver.chrome.driver", "./src/test/java/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //Navigate to...
        driver.manage().window().maximize();
        driver.get("https://www.espncricinfo.com/");

        //Activate search input
        By lupa = By.className("search");
        driver.findElement(lupa).click();

        //Searching...
        By searchBoxInput = By.name("search");
        driver.findElement(searchBoxInput).sendKeys("Sachin Tendulkar");
        driver.findElement(searchBoxInput).submit();

        //Go to result
        By resultSearch = By.linkText("Tendulkar, SR");
        driver.findElement(resultSearch).click();

        //Looking for his card and statistics
        driver.findElement(By.xpath(".//div[@class='playerpage_overview-dropdown-grid']//div//button//span[text()='ODI']")).click();
        driver.findElement(By.xpath(".//div[@class='ci-dd__menu']//ul//li//span[text()='ODI match list']")).click();

        //Switching to statistics view
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));

        //Getting the tables of statistics
        List<WebElement> tables = driver.findElementsByXPath(".//table[@class='engineTable']");
        List<WebElement> rows = tables.get(2).findElements(By.tagName("td"));
        String matches = rows.get(2).getText().trim();
        
        //ODI Matches
        assertEquals("463", matches);
    }

}
