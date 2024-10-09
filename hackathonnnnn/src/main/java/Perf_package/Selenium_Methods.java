package Perf_package;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Selenium_Methods {
	private static final Logger logger = Logger.getLogger(Selenium_Methods.class);

    public static void isElementavailable(String Element,WebDriver driver) {
        int attempts = 0;
        do {
            try {
                // Check if the element is displayed
                boolean exists = driver.findElements(By.xpath(Element)).size() != 0;
                if (exists) {
                    System.out.println("element found");
                    break;
                }
            } catch (Exception e) {
                // Catch any exceptions and ignore them
            }

            // Sleep for a short period of time before trying again
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Increment the attempts counter
            attempts++;
        } while (attempts < 50);
        System.out.println("element found");
    }

	public static void performanceTiming(WebDriver driver,String action,int iteration,int action_num){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            ArrayList requestsList = (ArrayList) js.executeScript("return window.performance.getEntriesByType(\"resource\")");
            String timingList= js.executeScript("return window.performance.timing").toString();
            Steps.theMain(action,requestsList,timingList,iteration,action_num);

        }catch(Exception ex) {
            logger.error("Error couldn't get the performance timings)", ex);
        }
    }
	
	public static void performanceTimingnoreloadline(WebDriver driver,String action,int iteration,int action_num){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            ArrayList requestsList = (ArrayList) js.executeScript("return window.performance.getEntriesByType(\"resource\")");
            Steps.theMain(action,requestsList,null,iteration,action_num);
        }catch(Exception ex) {
            logger.error("Error couldn't get the performance timings withnoreload)", ex);
        }
    }
	
	public void clearresources(WebDriver driver){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("return window.performance.clearResourceTimings()");
            ArrayList requestsList = (ArrayList) js.executeScript("return window.performance.getEntriesByType(\"resource\")");
            if (requestsList == null) {
                System.out.println("performance Object is cleared");
            }
        }catch(Exception ex) {
            logger.error("Error couldn't clear the performance)", ex);
        }
    }
}
