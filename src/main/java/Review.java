import org.sql2o.*;
import java.util.List;

public class Review {
  private int id=0;
  private String title;
  private String review;
  private int rating;
  private String reviewer;
  private String email;
  private int resourceid;
  private String date="";

  public Review(String title, String review, int rating, String reviewer, String email, int resourceid) {
    this.title = title;
    this.review = review;
    this.rating = rating;
    this.reviewer = reviewer;
    this.email = email;
    this.resourceid = resourceid;

    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews (title, review, rating, reviewer, email, resourceid) VALUES (:title, :review, :rating, :reviewer, :email, :resourceid)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("review", this.review)
        .addParameter("rating", this.rating)
        .addParameter("reviewer", this.reviewer)
        .addParameter("email", this.email)
        .addParameter("resourceid", this.resourceid)
        .executeUpdate()
        .getKey();
    }

    // TODO: get date from database to complete object properties
    // TODO: update average rating for resource
    // Resource.findById(resourceid).setAverage(getAverageByResource(id));
    // TODO: update review count for resource
    // Resource.findById(resourceid).setCount(getCountByResource(id));
  }

  public int getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public String getTitle() {
    return title;
  }

  public String getReview() {
    return review;
  }

  public int getRating() {
    return rating;
  }

  public String getReviewer() {
    return reviewer;
  }

  public String getEmail() {
    return email;
  }

  public int getResourceId() {
    return resourceid;
  }

  public static int getCountByResource(int id) {
    try (Connection cn = DB.sql2o.open()) {
      String sql = "SELECT COUNT(id) FROM reviews WHERE resourceid = :id";
      return cn.createQuery(sql).addParameter("id", id).executeScalar(Integer.class);
    }
  }

  public static int getAverageByResource(int id) {
    try (Connection cn = DB.sql2o.open()) {
      String sql = "SELECT AVG(rating) FROM reviews WHERE resourceid = :id";
      return cn.createQuery(sql).addParameter("id", id).executeScalar(Integer.class);
    }
  }

  public static List<Review> all() {
    String sql = "SELECT * FROM reviews ORDER BY date";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  public static List<Review> allByResource(int id) {
    String sql = "SELECT * FROM reviews WHERE resourceid = :id ORDER BY date";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Review.class);
    }
  }

  public static Review findById(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id=:id";
      Review link = cn.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
      return link;
    }
  }

  public void update(String title, String review, int rating, String reviewer, String email) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET title = :title, review = :review, rating = :rating, reviewer = :reviewer, email = :email WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("title", title)
        .addParameter("review", review)
        .addParameter("rating", rating)
        .addParameter("reviewer", reviewer)
        .addParameter("email", email)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM reviews WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
