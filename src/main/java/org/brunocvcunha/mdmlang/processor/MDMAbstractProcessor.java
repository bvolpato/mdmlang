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
package org.brunocvcunha.mdmlang.processor;

import java.util.List;

/**
 * Abstract class that contains generic structure for processors
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public abstract class MDMAbstractProcessor {

  private MDMProcessorContext context;
  private List<String> fields;

  public MDMAbstractProcessor(MDMProcessorContext context, List<String> fields) {
    this.context = context;
    this.fields = fields;
  }

  public abstract void process();

  public void update(String field, Object value) {
    this.getContext().getWorkingMap().put(field, value);
  }

  public MDMProcessorContext getContext() {
    return context;
  }

  public void setContext(MDMProcessorContext context) {
    this.context = context;
  }

  public Object getPrincipalValue(String field) {
    return this.getContext().getFieldValue(field);
  }

  public Object getPrincipalWorkingValue(String field) {
    return this.getContext().getWorkingValue(field);
  }

  public List<String> getFields() {
    return fields;
  }

  public void setFields(List<String> fields) {
    this.fields = fields;
  }

}
