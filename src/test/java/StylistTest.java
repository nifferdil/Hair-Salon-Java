import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Maxine");
    Stylist secondStylist = new Stylist("Maxine");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.save();
    Client firstClient = new Client("Shawna", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Megan", myStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void delete_deleteStylist_true() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.delete();
    assertEquals(0, Stylist.all().size());
  }

  @Test
    public void update_updateStylistInDatabase() {
    Stylist myStylist = new Stylist("Maxine");
    myStylist.save();
    myStylist.update("Maxine H");
    assertEquals("Maxine H", Stylist.all().get(0).getName());
    }
}
