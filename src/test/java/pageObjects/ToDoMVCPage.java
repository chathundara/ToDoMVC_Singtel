package pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.Steps;
import utilities.LoggerHelper;
import utilities.WaitHelper;

public class ToDoMVCPage {
	private WebDriver driver;
	Actions action;
	Logger logger = LoggerHelper.getLogger(Steps.class);
	@FindBy(xpath = "//input[@class='new-todo']") // empty list
	WebElement insertToDo;

	@FindBy(xpath = "//ul[@class='todo-list']/li/div/label")
	public List<WebElement> allItemsList;

	@FindBy(xpath = "//li[@class='todo']/div/label")
	public List<WebElement> activeItemsList;

	@FindBy(xpath = "//li[@class='todo completed']/div/label")
	public List<WebElement> completedItemsList;

	@FindBy(className = "clear-completed")
	WebElement btnClearCompleted;

	@FindBy(xpath = "//a[text()='All']")
	WebElement allItemsTab;

	@FindBy(xpath = "//a[text()='Active']")
	WebElement activeItemsTab;

	@FindBy(xpath = "//a[text()='Completed']")
	WebElement completedItemsTab;

	@FindBy(xpath = "//span[@class='todo-count']/strong[text()]")
	WebElement itemsLeftCount;

	@FindBy(xpath = "//button[@class='clear-completed']")
	WebElement clearCompletedLink;

	WebElement deleteBox;
	WebElement hoverOnItem;

	WaitHelper waitHelper = new WaitHelper(driver);

	public ToDoMVCPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}

	public void clearTodoInput() {
		insertToDo.clear();
	}

	public void clearAllTodoItems() {
		allItemsList.clear();
	}

	public void addAnItem(String item) {

		insertToDo.click();
		insertToDo.sendKeys(item + Keys.ENTER);
		logger.info("Item Added: " + item);
	}

	public boolean verifyAllList(String item) {
		Boolean isActive = false;

		if (allItemsTab.isDisplayed()) {
			allItemsTab.click();
		}

		if (allItemsList.size() > 0) {

			for (WebElement i : allItemsList) {

				if (i.getText().equalsIgnoreCase(item)) {
					isActive = true;
					break;
				}
			}
		}
		logger.info("verifyAllList: " + item);
		return isActive;

	}

	public boolean verifyActiveList(String item) {
		Boolean isActive = false;

		if (activeItemsTab.isDisplayed()) {
			activeItemsTab.click();
		}

		if (activeItemsList.size() > 0) {

			for (WebElement i : activeItemsList) {

				if (i.getText().equalsIgnoreCase(item)) {
					isActive = true;
					break;
				}
			}
		}
		logger.info("verifyActiveList: " + item);
		return isActive;

	}

	public boolean verifyCompletedList(String item) {
		if (completedItemsTab.isDisplayed()) {
			completedItemsTab.click();
		}

		Boolean isCompleted = false;
		for (WebElement i : completedItemsList) {
			if (i.getText().equalsIgnoreCase(item)) {
				isCompleted = true;
				break;
			}
		}
		logger.info("verifyCompletedList: " + item);
		return isCompleted;

	}

	public String itemsLeftCount() {
		logger.info("itemsLeftCount: " + itemsLeftCount.getText());
		return itemsLeftCount.getText();

	}

	public boolean checkTabsDisplayed(String tabName) {
		Boolean TabStatus = false;
		switch (tabName.toLowerCase()) {
		case "all":
			TabStatus = allItemsTab.isDisplayed();
			break;
		case "active":
			TabStatus = activeItemsTab.isDisplayed();
			break;
		case "completed":
			TabStatus = completedItemsTab.isDisplayed();
			break;

		}
		logger.info("checkTabsDisplayed: " + tabName);
		return TabStatus;

	}

	public void completeAnItem(String item) {
		WebElement checkBox = driver
				.findElement(By.xpath("//label[text()='" + item + "']//preceding-sibling::input[@type='checkbox']"));

		checkBox.click();
		logger.info("completeAnItem: " + item);

	}

	public boolean clearCompletedIsVisible() {
		logger.info("clearCompletedIsVisible: " + clearCompletedLink.isDisplayed());
		return clearCompletedLink.isDisplayed();

	}

	public void tabsNavigation(String tabName) {

		switch (tabName.toLowerCase()) {
		case "all":
			allItemsTab.click();
			break;
		case "active":
			activeItemsTab.click();
			break;
		case "completed":
			completedItemsTab.click();
			break;
		case "Clear completed":
			clearCompletedLink.click();
			break;
		}
		logger.info("tabsNavigation: " + tabName);
	}

	public boolean veryfyClearCompletedLink() {
		activeItemsTab.click();
		logger.info("veryfyClearCompletedLink: " + clearCompletedLink.isDisplayed());
		return clearCompletedLink.isDisplayed();

	}

	public void editAndVerifyAnItem(String initialItem, String editedItem) throws InterruptedException {

		for (WebElement i : allItemsList) {
			if (i.getText().equalsIgnoreCase(initialItem)) {

				System.out.println(i.getText());

				action = new Actions(driver);

				action.doubleClick(i).perform();

				action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(editedItem + Keys.ENTER)
						.perform();
				logger.info("Edit Item to: " + initialItem + "-->" + editedItem);
			}
		}
	}

	public void deleteAnItem(String item) throws InterruptedException {

		hoverOnItem = driver.findElement(By.xpath("//label[text()='" + item + "']"));

		new Actions(driver).moveToElement(hoverOnItem).perform();

		deleteBox = driver
				.findElement(By.xpath("//label[text()='" + item + "']//following-sibling::button[@class='destroy']"));

		deleteBox.click();
		logger.info("deleteAnItem: " + item);

	}

}
