import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ResourceTest {
  private Resource link;
  private Resource link2;
  private Technology tech;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/programmer_resource_test", null, null);
    tech = new Technology("Java");
    link = new Resource("Oracle Documentation",
                        "https://docs.oracle.com/javase/7/docs/api/",
                        "everything you never wanted to know about Java", tech.getId());
    link2 = new Resource("Epicodus Java Course",
                        "https://www.learnhowtoprogram.com/java",
                        "the best way to learn Java",
                        tech.getId());
    // otherTech = Technology.findById(tech.getId());
    // otherTech2 = Technology.findByName(tech2.getName());
  }

  @Test
  public void Resource_instantiates_true() {
    assertEquals(true, link instanceof Resource);
  }

  @Test
  public void Resource_instantiatesWithId_true() {
    assertEquals(true, link.getId() >0);
  }

  @Test
  public void Resource_instantiatesWithTitle_true() {
    assertEquals("Oracle Documentation", link.getTitle());
  }

  @Test
  public void Resource_instantiatesWithUrl_true() {
    assertEquals("https://docs.oracle.com/javase/7/docs/api/", link.getUrl());
  }

  @Test
  public void Resource_instantiatesWithDescription_true() {
    assertEquals("everything you never wanted to know about Java", link.getDescription());
  }

  @Test
  public void Resource_instantiatesWithTechId_true() {
    assertEquals(tech.getId(), link.getTechId());
  }

  @Test
  public void Resource_returnsAllInstances_true() {
    assertTrue(Resource.all().size()>1);
  }

  @Test
  public void Resource_returnsAllTechInstances_true() {
    assertTrue(Resource.allByTech(tech.getId()).size()>1);
  }

}
