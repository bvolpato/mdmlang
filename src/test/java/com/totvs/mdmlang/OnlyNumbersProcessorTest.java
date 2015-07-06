package com.totvs.mdmlang;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;
import com.totvs.mdmlang.processor.impl.OnlyNumbersProcessor;


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
