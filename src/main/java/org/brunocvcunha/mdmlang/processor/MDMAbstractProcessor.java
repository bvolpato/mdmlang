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
