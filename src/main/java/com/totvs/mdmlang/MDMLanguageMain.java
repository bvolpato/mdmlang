package com.totvs.mdmlang;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.totvs.mdmlang.processor.MDMProcessorContext;

public class MDMLanguageMain {
  public static void main(String[] args) throws IOException {
    Gson gson = new Gson();

    InputStream rules = MDMLanguageMain.class.getResourceAsStream("/mdmlang-rules.txt");
    InputStream json = MDMLanguageMain.class.getResourceAsStream("/source-data.json");

    MDMProcessorContext ctx = MDMProcessorContext.buildContext(rules);
    Type type = new TypeToken<Map<String, Object>>() {}.getType();

    Map<String, Object> values = gson.fromJson(new InputStreamReader(json), type);
    Map<String, Object> golden = ctx.process(values);

    System.out.println(gson.toJson(golden));
  }
}
