package com.totvs.mdmlang;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;
import com.totvs.mdmlang.processor.impl.TrimProcessor;


public class TrimProcessorTest {

  @Test
  public void testSimpleTrim() {
    MDMProcessorContext context = new MDMProcessorContext();

    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("it-codigo", " a1b2c3 ");
    context.setWorkingMap(sourceMap);

    MDMAbstractProcessor processor =
        new TrimProcessor(context, Arrays.asList(new String[] {"it-codigo"}));
    processor.process();

    assertEquals("a1b2c3", processor.getPrincipalWorkingValue("it-codigo"));
  }
}
