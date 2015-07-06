package org.brunocvcunha.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'truncateOperation'. It truncates data at a specified length
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class TruncateProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(TruncateProcessor.class);

  private int maxLength;

  public TruncateProcessor(MDMProcessorContext context, List<String> fields, int maxLength) {
    super(context, fields);
    this.maxLength = maxLength;
  }

  @Override
  public void process() {
    log.info("Processing truncate for " + getFields());

    for (String field : getFields()) {

      String value = getPrincipalWorkingValue(field).toString();
      if (value.length() > this.maxLength) {
        update(field, value.substring(0, this.maxLength));
      }

    }

  }

}
