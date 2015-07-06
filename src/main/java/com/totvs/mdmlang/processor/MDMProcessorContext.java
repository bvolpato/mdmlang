package com.totvs.mdmlang.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Logger;

import com.totvs.mdmlang.MDMRuleWalkerListener;
import com.totvs.mdmlang.antlr.MDMRuleLexer;
import com.totvs.mdmlang.antlr.MDMRuleParser;
import com.totvs.mdmlang.antlr.MDMRuleParser.MdmModelContext;
import com.totvs.mdmlang.processor.impl.ReturnProcessor;

/**
 * MDM Context - use it to process from source to golden record
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class MDMProcessorContext {

  private static final Logger log = Logger.getLogger(MDMProcessorContext.class);

  private Map<String, Object> sourceMap;
  private Map<String, Object> workingMap;

  private Map<String, List<MDMAbstractProcessor>> transformationChains = new HashMap<>();

  public static MDMProcessorContext buildContext(InputStream is) throws IOException {
    //Builds the lexer
    MDMRuleLexer lexer = new MDMRuleLexer(new ANTLRInputStream(is));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MDMRuleParser parser = new MDMRuleParser(tokens);
    MdmModelContext transfSentenceContext = parser.mdmModel();

    //Creates the context
    MDMProcessorContext context = new MDMProcessorContext();

    // Start the walker
    ParseTreeWalker walker = new ParseTreeWalker();
    MDMRuleWalkerListener listener = new MDMRuleWalkerListener(context);
    walker.walk(listener, transfSentenceContext);

    return context;

  }

  /**
   * Passes the sourceMap into the transformation chain, returning the golden record's Map
   * @param sourceMap Source Data
   * @return Golden Record
   */
  public Map<String, Object> process(Map<String, Object> sourceMap) {
    this.workingMap = sourceMap;

    log.info("Processing " + sourceMap);
    Map<String, Object> goldenRecord = new HashMap<>();

    for (String modelField : getTransformationChains().keySet()) {
      try {
        for (MDMAbstractProcessor processor : getTransformationChains().get(modelField)) {
          log.info("Processor: " + processor.getClass() + " for " + processor.getFields());
          processor.process();

          if (processor instanceof ReturnProcessor) {
            goldenRecord.put(modelField, ((ReturnProcessor) processor).getReturnValue());
          }
        }

      } catch (Exception e) {
        log.error("Error occurred while processing model " + modelField, e);
      }
    }

    return goldenRecord;
  }

  public Object getFieldValue(String field) {
    if (sourceMap == null || !sourceMap.containsKey(field)) {
      return null;
    }

    return sourceMap.get(field);
  }

  public Object getWorkingValue(String field) {
    if (workingMap == null || !workingMap.containsKey(field)) {
      return null;
    }

    return workingMap.get(field);
  }

  public Map<String, Object> getSourceMap() {
    return sourceMap;
  }

  public void setSourceMap(Map<String, Object> sourceMap) {
    this.sourceMap = sourceMap;
  }


  public Map<String, Object> getWorkingMap() {
    return workingMap;
  }

  public void setWorkingMap(Map<String, Object> workingMap) {
    this.workingMap = workingMap;
  }

  public Map<String, List<MDMAbstractProcessor>> getTransformationChains() {
    return transformationChains;
  }

  public void setTransformationChains(Map<String, List<MDMAbstractProcessor>> transformationChains) {
    this.transformationChains = transformationChains;
  }

}
