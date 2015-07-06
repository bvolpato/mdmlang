package com.totvs.mdmlang;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.totvs.mdmlang.antlr.MDMRuleBaseListener;
import com.totvs.mdmlang.antlr.MDMRuleParser;
import com.totvs.mdmlang.antlr.MDMRuleParser.FieldContext;
import com.totvs.mdmlang.antlr.MDMRuleParser.OperationContext;
import com.totvs.mdmlang.antlr.MDMRuleParser.TransformationSequenceContext;
import com.totvs.mdmlang.processor.MDMAbstractProcessor;
import com.totvs.mdmlang.processor.MDMProcessorContext;
import com.totvs.mdmlang.processor.constants.ProcessingSide;
import com.totvs.mdmlang.processor.impl.OnlyNumbersProcessor;
import com.totvs.mdmlang.processor.impl.RemoveCharsProcessor;
import com.totvs.mdmlang.processor.impl.RemoveLeadingZerosProcessor;
import com.totvs.mdmlang.processor.impl.ReturnProcessor;
import com.totvs.mdmlang.processor.impl.SubstringProcessor;
import com.totvs.mdmlang.processor.impl.TrimProcessor;
import com.totvs.mdmlang.processor.impl.TruncateProcessor;

/**
 * Rule Listener that creates {@link MDMProcessorContext} contexts.
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class MDMRuleWalkerListener extends MDMRuleBaseListener {

  private static final Logger log = Logger.getLogger(MDMRuleWalkerListener.class);

  private MDMProcessorContext context;

  public MDMRuleWalkerListener(MDMProcessorContext context) {
    super();
    this.context = context;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.totvs.mdmlang.antlr.MDMRuleBaseListener#enterFieldRules(com.totvs
   * .mdmlang.antlr.MDMRuleParser.FieldRulesContext)
   */
  @Override
  public void enterFieldRules(MDMRuleParser.FieldRulesContext ctx) {

    List<MDMAbstractProcessor> chain = new ArrayList<>();

    for (TransformationSequenceContext ctxSeq : ctx.transformationSequence()) {

      List<String> fields = new ArrayList<>();
      List<FieldContext> fieldContexts = ctxSeq.field();
      for (FieldContext contextField : fieldContexts) {
        fields.add(contextField.getText());
      }

      chain.add(buildProcessor(ctxSeq.operation(), fields));
    }

    context.getTransformationChains().put(ctx.CAMPOMASTER().getText(), chain);
  }

  /**
   * Builds processor for given operation
   * 
   * @param ctx Operation Parser Context
   * @param fields Fields that are object from the operation
   * @return THe processor for the operation
   */
  private MDMAbstractProcessor buildProcessor(OperationContext ctx, List<String> fields) {
    if (ctx.trimOperation() != null) {

      log.info("Adding Processor - Trim  - " + fields + " - " + ctx.trimOperation().toStringTree());

      return new TrimProcessor(context, fields);

    } else if (ctx.onlyNumbersOperation() != null) {

      log.info("Adding Processor - OnlyNumbers  - " + fields + " - "
          + ctx.onlyNumbersOperation().toStringTree());

      return new OnlyNumbersProcessor(context, fields);

    } else if (ctx.removeNCharsFromSideOperation() != null) {

      log.info("Adding Processor - RemoveChars  - " + fields + " - "
          + ctx.removeNCharsFromSideOperation().toStringTree());

      int quantity = Integer.valueOf(ctx.removeNCharsFromSideOperation().QUANTITY().getText());

      ProcessingSide processingSide =
          ctx.removeNCharsFromSideOperation().SIDE().getText().equalsIgnoreCase("left") ? ProcessingSide.LEFT
              : ProcessingSide.RIGHT;

      return new RemoveCharsProcessor(context, fields, quantity, processingSide);

    } else if (ctx.substringOperation() != null) {

      log.info("Adding Processor - Substring  - " + fields + " - "
          + ctx.substringOperation().toStringTree());

      int quantity = Integer.valueOf(ctx.substringOperation().QUANTITY().get(0).getText());
      int quantity2 = Integer.valueOf(ctx.substringOperation().QUANTITY().get(1).getText());

      return new SubstringProcessor(context, fields, quantity, quantity2);

    } else if (ctx.truncateOperation() != null) {

      log.info("Adding Processor - Truncate  - " + fields + " - "
          + ctx.truncateOperation().toStringTree());

      int maxLength = Integer.valueOf(ctx.truncateOperation().QUANTITY().getText());

      return new TruncateProcessor(context, fields, maxLength);

    } else if (ctx.returnOperation() != null) {

      log.info("Adding Processor - Return " + fields + " - " + ctx.returnOperation().toStringTree());
      return new ReturnProcessor(context, fields);

    } else if (ctx.removeLeadingZerosOperation() != null) {

      log.info("Adding Processor - RemoveLeadingZeros - " + fields + " - "
          + ctx.removeLeadingZerosOperation().toStringTree());
      return new RemoveLeadingZerosProcessor(context, fields);

    }

    throw new IllegalArgumentException("Not found processor for operation " + ctx.toStringTree());

  }

}
