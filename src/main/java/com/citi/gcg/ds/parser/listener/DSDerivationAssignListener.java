package com.citi.gcg.ds.parser.listener;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.rh.beans.RHExpression;

public class DSDerivationAssignListener extends DSDerivationLiteralListener {
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAssignExpr(@NotNull DSDerivationGrammarParser.AssignExprContext ctx) {
		LinkedList<RHExpression> localExprList = new LinkedList<>();
		/**
		 * "funcArgType": "FUN",
		"expanded": true,
		"text": "ASSIGN",
		"type": "Function",
		"typeDet": "ASSIGN",
		 */
		
		RHExpression rootExpr = new RHExpression();
		rootExpr.setFuncArgType("FUN");
		rootExpr.setType("Function");
		
		rootExpr.setTypeDet("ASSIGN");
		rootExpr.setText("ASSIGN");
		
		/**
		 * "funcArgType": "ATTRIBUTE",
		"editable": true,
		"rec": {
			"outputMappedToDataset": false,
			"attributeName": "ACCT_ORG_CDE",
			"attributeType": "S",
			"ruleConflictFlag": false
		},
		"text": "ACCT_ORG_CDE",
		"type": "Parameter",
		"typeDet": "Attribute",
		 */
		
		RHExpression expr1 = new RHExpression();
		expr1.setFuncArgType("ATTRIBUTE");
		expr1.setType("Parameter");
		
		expr1.setTypeDet("Attribute");
		expr1.setText(ctx.columnName().getText());
		expr1.set_inputLinkName(ctx.inputLinkName().getText());
		
		localExprList.add(rootExpr);
		localExprList.add(expr1);

		exprList.put(ctx.getRuleIndex(), localExprList);
		
		//System.out.println(ctx.getRuleIndex()+"~~~"+ctx.inputLinkName().getText()+"  ~~~ "+ctx.columnName().getText());
		
	}

}
