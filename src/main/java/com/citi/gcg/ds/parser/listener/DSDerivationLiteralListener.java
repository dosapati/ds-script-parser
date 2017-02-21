package com.citi.gcg.ds.parser.listener;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarBaseListener;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.rh.beans.RHExpression;

public class DSDerivationLiteralListener extends DSDerivationGrammarRootListener {
	
	
	
	@Override public void enterLiteral(@NotNull DSDerivationGrammarParser.LiteralContext ctx) { 
		LinkedList<RHExpression> localExprList = new LinkedList<>();
		RHExpression rootExpr = new RHExpression();
		rootExpr.setFuncArgType("VALUE");
		rootExpr.setType("Value");

		
		if(ctx.numberLiteral() != null){
			rootExpr.setText(ctx.getText());
			rootExpr.setTypeDet("Numeric_Value");
			rootExpr.setFuncArgDataType("N");
			System.out.println("number -->"+ctx.getText());
		}else if(ctx.stringLiteral() != null){
			rootExpr.setText(ctx.getText());
			rootExpr.setTypeDet("String_Value");
			rootExpr.setFuncArgDataType("S");
			System.out.println("string -->"+ctx.getText());
		}else if(ctx.booleanLiteral() != null){
			rootExpr.setText(ctx.getText());
			rootExpr.setTypeDet("String_Value");
			rootExpr.setFuncArgDataType("S");
			System.out.println("boolean -->"+ctx.getText());
		}else if(ctx.nullLiteral() != null){
			rootExpr.setText(ctx.getText());
			rootExpr.setTypeDet("String_Value");
			rootExpr.setFuncArgDataType("S");
			System.out.println("boolean -->"+ctx.getText());
		}else if(ctx.characterLiteral() != null){
			rootExpr.setText(ctx.getText());
			rootExpr.setTypeDet("String_Value");
			rootExpr.setFuncArgDataType("S");
			System.out.println("boolean -->"+ctx.getText());
		}
		localExprList.add(rootExpr);
		exprList.put(ctx.getRuleIndex(), localExprList);
		
	}


}
