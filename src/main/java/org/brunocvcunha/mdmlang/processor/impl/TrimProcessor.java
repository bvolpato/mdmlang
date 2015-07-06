package org.brunocvcunha.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'trimOperation'. It trims the String (removes unnecessary whitespaces)
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class TrimProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(TrimProcessor.class);

  public TrimProcessor(MDMProcessorContext context, List<String> fields) {
    super(context, fields);
  }

  @Override
  public void process() {
    log.info("Processing trim for " + getFields());

    for (String field : getFields()) {
      update(field, getPrincipalWorkingValue(field).toString().trim());
    }

  }

}
