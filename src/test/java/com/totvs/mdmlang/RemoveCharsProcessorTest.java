package com.totvs.mdmlang;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;
import com.totvs.mdmlang.processor.constants.ProcessingSide;
import com.totvs.mdmlang.processor.impl.RemoveCharsProcessor;


public class RemoveCharsProcessorTest {

  @Test
  public void testLeft() {
    MDMProcessorContext context = new MDMProcessorContext();

    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("it-codigo", "aaa1bbb");
    context.setWorkingMap(sourceMap);

    MDMAbstractProcessor processor =
        new RemoveCharsProcessor(context, Arrays.asList(new String[] {"it-codigo"}), 3, ProcessingSide.LEFT);
    processor.process();

    assertEquals("1bbb", processor.getPrincipalWorkingValue("it-codigo"));
  }

  @Test
  public void testRight() {
    MDMProcessorContext context = new MDMProcessorContext();

    Map<String, Object> sourceMap = new HashMap<>();
    sourceMap.put("it-codigo", "aaa1bbb");
    context.setWorkingMap(sourceMap);

    MDMAbstractProcessor processor =
        new RemoveCharsProcessor(context, Arrays.asList(new String[] {"it-codigo"}), 3, ProcessingSide.RIGHT);
    processor.process();

    assertEquals("aaa1", processor.getPrincipalWorkingValue("it-codigo"));
  }

}
