package cn.leancloud.demo.todo;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.AVException;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;

public class Cloud {

  @EngineFunction("hello")
  public static String hello(@EngineFunctionParam("name") String name) {
    if (name == null) {
      return "What is your name?";
    }
    return String.format("Hello %s!", name);
  }
  @EngineFunction("createTodo")
  public static void createTodo(@EngineFunctionParam("content") String content)
      throws AVException {
    AVObject todo = new AVObject("Todo");
    todo.put("content", content);
    todo.save();   
  }
  
  @EngineFunction("averageStars")
  public static float getAverageStars(@EngineFunctionParam("movie") String movie)
      throws AVException {
    AVQuery<AVObject> query = new AVQuery("Review");
    query.whereEqualTo("movie", movie);
    List<AVObject> reviews = query.find();
    int sum = 0;
    if (reviews == null && reviews.isEmpty()) {
      return 0;
    }
    for (AVObject review : reviews) {
      sum += review.getInt("star");
    }
    return sum / reviews.size();
  }
}
