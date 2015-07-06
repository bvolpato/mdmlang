package com.totvs.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'onlyNumbersOperation'. It keeps only the numbers of a string
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class OnlyNumbersProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(OnlyNumbersProcessor.class);

  public OnlyNumbersProcessor(MDMProcessorContext context, List<String> fields) {
    super(context, fields);
  }

  @Override
  public void process() {
    log.info("Processing only numbers for " + getFields());

    for (String field : getFields()) {
      update(field, getPrincipalWorkingValue(field).toString().replaceAll("[^0-9]", ""));
    }

  }

}
