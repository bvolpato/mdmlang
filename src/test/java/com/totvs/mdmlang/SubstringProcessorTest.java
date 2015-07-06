package com.totvs.mdmlang;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;
import com.totvs.mdmlang.processor.impl.SubstringProcessor;


public class SubstringProcessorTest {

  @Test
  public void testSubstring() {
    MDMProcessorContext context = new MDMProcessorContext();

    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("it-codigo", "aaa1bbb");
    context.setWorkingMap(sourceMap);

    MDMAbstractProcessor processor =
        new SubstringProcessor(context, Arrays.asList(new String[] {"it-codigo"}), 2, 4);
    processor.process();

    assertEquals("a1", processor.getPrincipalWorkingValue("it-codigo"));
  }


}
