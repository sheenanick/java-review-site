import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ReviewTest {
  private Review review;
  private Review review2;
  private Resource resource;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/programmer_resource_test", null, null);
    resource = new Resource("Java");
    review = new Review("Oracle Documentation",
                        "https://docs.oracle.com/javase/7/docs/api/",
                        "everything you never wanted to know about Java", resource.getId());
    review2 = new Review("Epicodus Java Course",
                        "https://www.learnhowtoprogram.com/java",
                        "the best way to learn Java",
                        resource.getId());
  }

  @Test
  public void Review_instantiates_true() {
    assertEquals(true, review instanceof Review);
  }

  @Test
  public void Review_instantiatesWithId_true() {
    assertEquals(true, review.getId() >0);
  }

  @Test
  public void Review_instantiatesWithTitle_true() {
    assertEquals("Oracle Documentation", review.getTitle());
  }

  @Test
  public void Review_instantiatesWithUrl_true() {
    assertEquals("https://docs.oracle.com/javase/7/docs/api/", review.getUrl());
  }

  @Test
  public void Review_instantiatesWithDescription_true() {
    assertEquals("everything you never wanted to know about Java", review.getDescription());
  }

  @Test
  public void Review_instantiatesWithTechId_true() {
    assertEquals(resource.getId(), review.getTechId());
  }

  @Test
  public void Review_returnsAllInstances_true() {
    assertTrue(Review.all().size()>1);
  }

  @Test
  public void Review_returnsAllTechInstances_true() {
    assertTrue(Review.allByTech(resource.getId()).size()>1);
  }

}
