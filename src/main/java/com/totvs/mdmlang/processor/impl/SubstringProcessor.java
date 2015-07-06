package com.totvs.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'substringOperation'. It is intended to get a defined part of a String
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class SubstringProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(SubstringProcessor.class);

  private int startIndex;
  private int endIndex;

  public SubstringProcessor(MDMProcessorContext context, List<String> fields, int startIndex,
      int endIndex) {
    super(context, fields);
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void process() {
    log.info("Processing remove for " + getFields());

    for (String field : getFields()) {

      String value = getPrincipalWorkingValue(field).toString();
      update(field, value.substring(startIndex, endIndex));

    }

  }

}
