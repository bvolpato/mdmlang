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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;
import org.brunocvcunha.mdmlang.processor.impl.OnlyNumbersProcessor;
import org.junit.Test;


/**
 * Tests if the processor is returning just the numbers
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class OnlyNumbersProcessorTest {

  @Test
  public void testSimpleOnlyNumbers() {
    MDMProcessorContext context = new MDMProcessorContext();

    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("it-codigo", "a1b2c3");
    context.setWorkingMap(sourceMap);

    MDMAbstractProcessor processor =
        new OnlyNumbersProcessor(context, Arrays.asList(new String[] {"it-codigo"}));
    processor.process();

    assertEquals("123", processor.getPrincipalWorkingValue("it-codigo"));
  }
}
