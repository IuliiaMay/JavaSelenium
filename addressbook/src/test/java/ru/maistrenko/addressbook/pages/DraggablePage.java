package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maistrenko on 28.07.2017.
 * WebElement element = driver.findElement(By.name("source"));
 WebElement target = driver.findElement(By.name("target"));

 new Actions(driver).dragAndDrop(element, target).perform();
 */
public class DraggablePage extends AbstractPage {
    public DraggablePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void dragAndDropBy(String source, int xOffset, int yOffset){
        WebElement draggable = driver.findElement(By.id(source));
        new Actions(driver).dragAndDropBy(draggable, xOffset, yOffset).build().perform();
    }

    public int getDraddableSectionsCount(){
        return driver.findElements(By.className("ui-tabs-anchor")).size();
    }


    public List<Button> getDraggableButtonCount() {
        List<Button> buttons = new ArrayList<Button>();
        List<WebElement> elements = driver.findElements(By.className("ui-tabs-anchor"));
        for(WebElement element: elements){
            String name = element.getText();
            Button button = new Button(name);
        }
        return buttons;

    }

    public class Button {
        private String name;

        public Button(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
