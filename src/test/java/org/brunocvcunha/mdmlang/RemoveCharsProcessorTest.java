package org.brunocvcunha.mdmlang;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;
import org.brunocvcunha.mdmlang.processor.constants.ProcessingSide;
import org.brunocvcunha.mdmlang.processor.impl.RemoveCharsProcessor;
import org.junit.Test;


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
