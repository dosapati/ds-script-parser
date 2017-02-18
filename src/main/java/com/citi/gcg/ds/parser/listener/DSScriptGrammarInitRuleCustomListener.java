package com.citi.gcg.ds.parser.listener;

import com.citi.gcg.ds.parser.grammar.DSScriptGrammarBaseListener;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser.InitializeRuleContext;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser.InputnameRuleContext;

public class DSScriptGrammarInitRuleCustomListener extends DSScriptGrammarBaseListener {

	@Override
	public void enterInputnameRule(InputnameRuleContext ctx) {
		System.out.println(ctx.getText());
	}

	/* (non-Javadoc)
	 * @see com.citi.gcg.ds.parser.grammar.DSScriptGrammarBaseListener#enterInitializeRule(com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser.InitializeRuleContext)
	 */
	@Override
	public void enterInitializeRule(InitializeRuleContext ctx) {
		System.out.println("11 ~~~ "+ctx.getText());
		
	}
	
	

}
