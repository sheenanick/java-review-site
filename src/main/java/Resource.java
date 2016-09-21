import org.sql2o.*;
import java.util.List;

public class Resource {
  private int id;
  private String title;
  private String url;
  private String description;
  private int techid;

  public Resource(String title, String url, String description, int techid) {
    this.title = title;
    this.url = url;
    this.description = description;
    this.techid = techid;

    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO resources (title, url, description, techid) VALUES (:title, :url, :description, :techid)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("description", this.description)
        .addParameter("url", this.url)
        .addParameter("techid", this.techid)
        .executeUpdate()
        .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public String getDescription() {
    return description;
  }

  public int getTechId() {
    return techid;
  }

  public static List<Resource> all() {
    String sql = "SELECT * FROM resources ORDER BY title";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql).executeAndFetch(Resource.class);
    }
  }

  public static List<Resource> allByTech(int id) {
    String sql = "SELECT * FROM resources WHERE techid = :id ORDER BY title";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Resource.class);
    }
  }

}
