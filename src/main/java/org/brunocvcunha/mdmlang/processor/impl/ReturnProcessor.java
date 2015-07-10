/**
 * Copyright (C) 2015 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
