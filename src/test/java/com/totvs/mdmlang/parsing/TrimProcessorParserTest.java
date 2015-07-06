package com.totvs.mdmlang.parsing;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.totvs.mdmlang.processor.MDMProcessorContext;

/**
 * Test that parses trim commands
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class TrimProcessorParserTest {

  @Test
  public void trimParse() throws IOException {
    String testMapping =
        "itemCode:\n" + "	trim it-codigo, it-codigo\n" + "	return it-codigo, desc-item\n";

    MDMProcessorContext ctx =
        MDMProcessorContext.buildContext(new ByteArrayInputStream(testMapping.getBytes()));

    Map<String, Object> values = new HashMap<>();
    values.put("it-codigo", " Item Code 123");
    values.put("desc-item", "A");

    Map<String, Object> golden = ctx.process(values);

    assertEquals("Item Code 123A", golden.get("itemCode"));

  }
}
