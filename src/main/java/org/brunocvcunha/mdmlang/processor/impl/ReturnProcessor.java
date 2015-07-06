package org.brunocvcunha.mdmlang.processor.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.brunocvcunha.mdmlang.processor.MDMAbstractProcessor;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;

/**
 * Processor for 'returnOperation'. It puts the processed value at return stack
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class ReturnProcessor extends MDMAbstractProcessor {

  private static final Logger log = Logger.getLogger(ReturnProcessor.class);

  private Object returnValue;

  public ReturnProcessor(MDMProcessorContext context, List<String> fields) {
    super(context, fields);
  }

  @Override
  public void process() {
    log.info("Processing return for " + getFields());

    if (getFields().size() == 0) {
      this.returnValue = getPrincipalWorkingValue(getFields().get(0));
    } else {

      StringBuffer sb = new StringBuffer();

      for (String field : getFields()) {
        Object objectAppend = getPrincipalWorkingValue(field);
        if (objectAppend instanceof Double) {
          Double numAppend = (Double) objectAppend;
          if (numAppend == numAppend.longValue()) {
            sb.append(numAppend.longValue());
          } else {
            sb.append(numAppend.doubleValue());
          }
        } else {
          sb.append(getPrincipalWorkingValue(field));
        }
      }

      this.returnValue = sb.toString();
    }

  }

  public Object getReturnValue() {
    return returnValue;
  }


}
