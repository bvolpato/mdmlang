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
