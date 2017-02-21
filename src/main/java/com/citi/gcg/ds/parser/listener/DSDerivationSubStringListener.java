package com.citi.gcg.ds.parser.listener;

import org.antlr.v4.runtime.misc.NotNull;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;

public class DSDerivationSubStringListener extends DSDerivationGrammarRootListener {
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterSubstring(@NotNull DSDerivationGrammarParser.SubstringContext ctx) { 
		
		
		System.out.println("(("+ctx.getRuleIndex()+"~~~"+ctx.getChild(0).getText());

	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitSubstring(@NotNull DSDerivationGrammarParser.SubstringContext ctx) { 
		
		
		System.out.println(ctx.getRuleIndex()+"~~~"+"))");

	}
	
	

}
