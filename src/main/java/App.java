import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("technologies", Technology.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Technology tech = new Technology(request.queryParams("name"));
      //response.redirect("/");
      model.put("technologies", Technology.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/edit", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Technology tech = new Technology(request.queryParams("name"));
    //   //update
    //   response.redirect("/");
    //   model.put("technologies", Technology.all());
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/technologies/:tid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":tid"));
      model.put("tech", Technology.findById(category));
      model.put("links", Resource.allByTech(category));
      model.put("template", "templates/technology.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/technologies/:tid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":tid"));
      Resource resource = new Resource(request.queryParams("title"),
                                      request.queryParams("url"),
                                      request.queryParams("desc"),
                                      category);
      model.put("tech", Technology.findById(category));
      model.put("links", Resource.allByTech(category));
      model.put("template", "templates/technology.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/technologies/:tid/resources/:rid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":rid"));
      model.put("ext", ".png");
      model.put("resource", Resource.findById(category));
      model.put("tech", Technology.findById(Integer.parseInt(request.params(":tid"))));
      model.put("reviews", Review.allByResource(category));
      model.put("template", "templates/resource.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/technologies/:tid/resources/:rid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":rid"));
      Review review = new Review(request.queryParams("title"),
                                request.queryParams("review"),
                                Integer.parseInt(request.queryParams("rating")),
                                request.queryParams("reviewer"),
                                request.queryParams("email"),
                                category);
      model.put("resource", Resource.findById(category));
      model.put("tech", Technology.findById(Integer.parseInt(request.params(":tid"))));
      model.put("reviews", Review.allByResource(category));
      model.put("ext", ".png");
      model.put("template", "templates/resource.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
