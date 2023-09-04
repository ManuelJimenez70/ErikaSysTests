package pages;

public class GooglePage extends BasePage{

    private String searchBox = "//textarea[@id='APjFqb']";
    private String searchButton = "//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[4]/center[1]/input[1]";
    private String firstResult = "";
    public GooglePage() {
        super(driver);
    }

    public void navigateToGoogle(){
        navigateTo("https://www.google.com/");
    }

    public void clickSearchBox(){
        clickElement(this.searchButton);
    }

    public void writeSearchCriteria(String textToWrite){
        write(this.searchBox, textToWrite);
    }

    public void clickSearchButton(){
        clickElement(this.searchButton);
    }

    public void closeGoogleDriver(){
        closeDriver();
    }

    public String firstResult(){
        return getTextFromElement(this.firstResult);
    }
}
