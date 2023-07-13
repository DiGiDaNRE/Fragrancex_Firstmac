package utility;

import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class WebElementContainer {

    private List<WebElement> webElementList;

    public WebElementContainer() {
        webElementList = new ArrayList<>();
    }

    public List<WebElement> getWebElementList() {
        return webElementList;
    }

    public void setWebElementList(List<WebElement> webElementList) {
        this.webElementList = webElementList;
    }
}