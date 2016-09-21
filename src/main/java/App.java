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

    get("/technologies/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":id"));
      model.put("tech", Technology.findById(category));
      model.put("links", Resource.allByTech(category));
      model.put("template", "templates/technology.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/technologies/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int category = Integer.parseInt(request.params(":id"));
      Resource resource = new Resource(request.queryParams("title"),
                                      request.queryParams("url"),
                                      request.queryParams("description"),
                                      category);
      model.put("tech", Technology.findById(category));
      model.put("links", Resource.allByTech(category));
      model.put("template", "templates/technology.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
