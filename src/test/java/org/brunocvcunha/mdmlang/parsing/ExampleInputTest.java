/**
 * Copyright (C) 2015 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.mdmlang.parsing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Test that parses remove chars characters
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class ExampleInputTest {

  @Test
  public void trimParseSimple() throws IOException {

    Gson gson = new Gson();

    InputStream rules = getClass().getResourceAsStream("/example-input-data.txt");
    InputStream json = getClass().getResourceAsStream("/example-input-data.json");
    assertNotNull(rules);
    assertNotNull(json);

    MDMProcessorContext ctx = MDMProcessorContext.buildContext(rules);
    Type type = new TypeToken<Map<String, Object>>() {}.getType();

    Map<String, Object> values = gson.fromJson(new InputStreamReader(json), type);
    System.out.println(values);

    Map<String, Object> golden = ctx.process(values);

    System.out.println(gson.toJson(golden));
    assertEquals("my item", golden.get("itemCode"));
    assertEquals("large description th", golden.get("description"));
    assertEquals("4792326009", golden.get("phoneNumber"));

  }
  
  @Test
  public void trimParseSimple2() throws IOException {

    Gson gson = new Gson();

    InputStream rules = getClass().getResourceAsStream("/example-input-data-test2.txt");
    InputStream json = getClass().getResourceAsStream("/example-input-data.json");
    assertNotNull(rules);
    assertNotNull(json);

    MDMProcessorContext ctx = MDMProcessorContext.buildContext(rules);
    Type type = new TypeToken<Map<String, Object>>() {}.getType();

    Map<String, Object> values = gson.fromJson(new InputStreamReader(json), type);
    System.out.println(values);

    Map<String, Object> golden = ctx.process(values);

    System.out.println(gson.toJson(golden));
    assertEquals("", golden.get("itemCode"));
    assertEquals("20", golden.get("description"));
    assertEquals("92", golden.get("phoneNumber"));

  }


}
