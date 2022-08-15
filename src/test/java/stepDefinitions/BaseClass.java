package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


import pageObjects.ToDoMVCPage;
import utilities.WaitHelper;

public class BaseClass {

	public WebDriver driver;
	public ToDoMVCPage toDoMVCPage;

	public static Logger logger;
	public List<String> items= new ArrayList<>();
	public WaitHelper waitHelper = new WaitHelper(driver);

	
	
}
