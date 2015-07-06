package org.brunocvcunha.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;
import org.brunocvcunha.mdmlang.processor.constants.ProcessingSide;

/**
 * Processor for 'removeCharsOperation'. It removes chars from left or right according to specified
 * rule
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class RemoveCharsProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(RemoveCharsProcessor.class);

  private int quantity;
  private ProcessingSide processingSide;

  public RemoveCharsProcessor(MDMProcessorContext context, List<String> fields, int quantity,
      ProcessingSide processingSide) {
    super(context, fields);
    this.quantity = quantity;
    this.processingSide = processingSide;
  }

  @Override
  public void process() {
    log.info("Processing remove for " + getFields());

    for (String field : getFields()) {

      String value = getPrincipalWorkingValue(field).toString();
      if (processingSide == ProcessingSide.LEFT) {
        update(field, value.substring(quantity));
      } else {
        update(field, value.substring(0, value.length() - quantity));
      }

    }

  }

}
