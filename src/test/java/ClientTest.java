import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientNamesAretheSame() {
    Client firstClient = new Client("Derrick", 1);
    Client secondClient = new Client("Derrick", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfClientNamesAretheSame() {
    Client newClient = new Client("Derrick", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client newClient = new Client("Derrick", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }


  @Test
  public void all_savesIntoDatabase_true() {
    Client newClient = new Client("Derrick", 1);
    newClient.save();
    assertEquals(Client.all().get(0).getClientName(),"Derrick");
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client newClient = new Client("Derrick", 1);
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertEquals(savedClient.getClientName(), "Derrick");
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist= new Stylist("Maxine");
    myStylist.save();
    Client newClient = new Client("Derrick", myStylist.getId());
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }







}
