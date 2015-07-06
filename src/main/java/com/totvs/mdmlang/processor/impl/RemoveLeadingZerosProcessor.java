package com.totvs.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'removeLeadingZerosOperation'. It removes zeros from the beginning of the data
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class RemoveLeadingZerosProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(RemoveLeadingZerosProcessor.class);

  public RemoveLeadingZerosProcessor(MDMProcessorContext context, List<String> fields) {
    super(context, fields);
  }

  @Override
  public void process() {
    log.info("Processing removeLeadingZeros for " + getFields());

    for (String field : getFields()) {
      String val = getPrincipalWorkingValue(field).toString();
      if (val.startsWith("0")) {
        while (val.startsWith("0")) {
          val = val.substring(1);
        }
        
        update(field, val);
      }
      
    }

  }

}
