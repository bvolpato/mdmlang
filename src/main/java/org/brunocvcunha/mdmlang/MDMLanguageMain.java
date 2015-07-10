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
package org.brunocvcunha.mdmlang;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
