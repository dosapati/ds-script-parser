package com.citi.gcg.ds.parser.listener;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.rh.beans.RHExpression;

public class DSDerivationExpressionListener extends DSDerivationGrammarRootListener {
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExpression(@NotNull DSDerivationGrammarParser.ExpressionContext ctx) {
		
//		ruil
		
		//ctx.;
		
		System.out.println(ctx.getRuleIndex()+"~~~"+ctx.getChild(0).getText());
		
	}

}
