import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Client {
  private int id;
  private int stylistId;
  private String clientName;

  public int getId() {
    return id;
  }

  public String getClientName() {
    return clientName;
  }

  public int getStylistId() {
    return stylistId;
  }

  public Client(String clientName, int stylistId) {
    this.clientName = clientName;
    this.stylistId = stylistId;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      System.out.println(this.getStylistId());
      System.out.println(newClient.getStylistId());
      return this.getClientName().equals(newClient.getClientName()) &&
             this.getId() == newClient.getId() &&
             this.getStylistId() == newClient.getStylistId();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, clientName, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(clientName, stylistId) VALUES (:clientName, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("clientName", clientName)
        .addParameter("stylistId", stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String clientName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET clientName = :clientName WHERE id = :id";
      con.createQuery(sql)
        .addParameter("clientName", clientName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
