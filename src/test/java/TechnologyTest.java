import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TechnologyTest {
  private Technology tech;
  private Technology tech2;
  private Technology otherTech;
  private Technology otherTech2;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/programmer_resource_test", null, null);
    tech = new Technology("HTML");
    tech2 = new Technology("CSS");
    otherTech = Technology.findById(tech.getId());
    otherTech2 = Technology.findByName(tech2.getName());
  }

  @Test
  public void Technology_instantiates_true() {
    assertEquals(true, tech instanceof Technology);
  }

  @Test
  public void Technology_instantiatesWithId_true() {
    assertEquals(true, tech.getId() >0);
  }

  @Test
  public void Technology_returnsAllInstances_true() {
    assertTrue(Technology.all().size()>1);
  }

  @Test
  public void findById_returnsCorrectTech_true() {
    assertTrue(tech.equals(otherTech));
  }

  @Test
  public void findByName_returnsCorrectTech_true() {
    assertTrue(tech.equals(otherTech));
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM technologies *;";
      con.createQuery(sql).executeUpdate();
    }
  }
}
