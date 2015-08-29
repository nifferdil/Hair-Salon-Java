import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void stylistIsDisplayedWhenCreated() {
    goTo("http://localhost:4567/");
    fill("#name").with("Maxine");
    submit(".btn");
    assertThat(pageSource()).contains("Maxine");
  }

  @Test
   public void stylistIsDeleted() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.save();
    goTo("http://localhost:4567/");
    click("a", withText("Delete stylist"));
    assertThat(pageSource()).doesNotContain("Maxine");
   }

   @Test
   public void stylistIsUpdated() {
     Stylist myStylist = new Stylist("Maxine");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("a", withText("Edit Stylist"));
     fill("#name").with("Maxine H");
     submit(".btn");
     assertThat(pageSource()).contains("Maxine H");
   }

   @Test
   public void clientIsDisplayedWhenCreated() {
     Stylist myStylist = new Stylist("Claudia");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("a", withText("Claudia"));
     fill("#clientName").with("Derrick");
     submit(".btn");
     assertThat(pageSource()).contains("Derrick");
   }

   @Test
   public void allclientsDisplayOnStylistPage() {
     Stylist myStylist = new Stylist("Maxine");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("a", withText("Maxine"));
     fill("#clientName").with("Derrick");
     submit(".btn");
     fill("#clientName").with("Marty");
     submit(".btn");
     assertThat(pageSource()).contains("Derrick");
     assertThat(pageSource()).contains("Marty");
   }

   @Test
   public void clientIsDeleted() {
     Stylist myStylist = new Stylist("Maxine");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("a", withText("Maxine"));
     fill("#clientName").with("Derrick");
     submit(".btn");
     click("a", withText("Delete client"));
     assertThat(pageSource()).doesNotContain("Derrick");
   }

   @Test
   public void clientIsUpdated(){
     Stylist myStylist = new Stylist("Maxine");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("a", withText("Maxine"));
     fill("#clientName").with("Derrick");
     submit(".btn");
     click("a", withText("Edit client"));
     fill("#clientName").with("Derrick H");
     assertThat(pageSource()).contains("Derrick H");
   }
}
