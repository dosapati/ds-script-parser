/**
 * 
 */
package com.citi.gcg.ds.parser.visitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarBaseVisitor;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.rh.beans.RHExpression;
import com.citi.gcg.rh.beans.Value;

/**
 * @author dosapati
 *
 */
public class DSDerivationCustomVisitor extends DSDerivationGrammarBaseVisitor<Value> {

	public LinkedHashMap<String, String> visitorStack = new LinkedHashMap<>();

	public LinkedList<RHExpression> visitorRHExprList = new LinkedList<>();

	static Stack<String> idStack = new Stack<>();
	
	static{
		idStack.push("root");
	}
	
	String idPrefix = "ext-";
			
	private String generateIdPrefix(){
		StringBuilder sb = new StringBuilder("ext").append(RandomStringUtils.randomNumeric(4)).append("-");
		idPrefix = sb.toString();
		return sb.toString();
	}
	
	@Override
	public Value visitStatement(@NotNull DSDerivationGrammarParser.StatementContext ctx) {
		//generate id prefix ... first ..
		
		generateIdPrefix();
		StringBuilder sb = new StringBuilder();
		sb.append("<Statement>");
		visitChilds(ctx, sb);
		sb.append("</Statement>");
		visitorStack.put("statement", sb.toString());
		return new Value(sb.toString());
	}
 
	@Override
	public Value visitIf_then_else(@NotNull DSDerivationGrammarParser.If_then_elseContext ctx) {
		// ctx.expression()
		StringBuilder sb = new StringBuilder();
		sb.append("<if_then_else>");
		int childCount = ctx.getChildCount();
		
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				//System.out.println(ctx.getChild(i).getClass().getName());

				if (i == 1) {
					RHExpression expr = new RHExpression();
					expr.setFuncArgType("FUN");
					expr.setType("Condition");
					expr.setTypeDet("IF");
					expr.setText("IF");
					expr.setLeaf(false);
					expr.setParentId(idStack.get(idStack.size()-2));
					expr.setId(idStack.peek());
					visitorRHExprList.add(expr);
					sb.append("<if>").append(visit(ctx.getChild(i))).append("</if>");
				} else if (i == 3) {

					
					sb.append("<then>").append(visit(ctx.getChild(i))).append("</then>");
				} else if (i == 5) {


					sb.append("<else>").append(visit(ctx.getChild(i))).append("</else>");
				}
				//System.out.println("%%%%%%% \n\n" + ctx.getChild(i).getChild(0).getClass().getName() + "\n\n %%%%%%%");
				// visitorStack.put("if_expr_"+i,
				// visit(ctx.getChild(i)).asString());
			} else {
				if(StringUtils.equalsIgnoreCase(ctx.getChild(i).getText(), "if")){
					//System.out.println(ctx.getChild(i).getText());
				}else if(StringUtils.equalsIgnoreCase(ctx.getChild(i).getText(), "then")){
					
				}else if(StringUtils.equalsIgnoreCase(ctx.getChild(i).getText(), "else")){
					
				}
				//System.out.println(ctx.getChild(i).getText());

				//System.out.println("Terminal node -->" + ctx.getText());
			}

		}
		idStack.pop();
		sb.append("</if_then_else>");
		visitorStack.put("if_then_else", sb.toString());
		return new Value(sb.toString());
		// return visitChildren(ctx);
	}

	
	public String generateTreeId(){
		StringBuilder sb = new StringBuilder(this.idPrefix).append(RandomStringUtils.randomNumeric(5));
		return sb.toString();
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Value visitExpression(@NotNull DSDerivationGrammarParser.ExpressionContext ctx) {
		int childCount = ctx.getChildCount();
		StringBuilder sb = new StringBuilder();
		sb.append("<expression>");
		String parentId = idStack.peek();
		String id = generateTreeId();
		idStack.push(id);
		System.out.println(String.format("expr  parentId - %s, id - %s", parentId,id));
		for (int i = 0; i < childCount; i++) {
			//System.out.println(" - expression -->["+i+" - "+ctx.getChild(i).getClass().getName()+"]"+ctx.getChild(i).getText());

			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				//System.out.println(ctx.getChild(i).getClass().getName());
				
				sb.append(visit(ctx.getChild(i)).asString());
				
				// visitorStack.put("expr_"+(exprCounter++),
				// visit(ctx.getChild(i)).asString());
			} else {
				//System.out.println(ctx.getChild(i).getText());
			}

			// visitorStack.put("expr_"+1, visit(ctx.getChild(i)).asString());

		}
		idStack.pop();
		sb.append("</expression>");
		//System.out.println("expression --->" + sb.toString());
		return new Value(sb.toString());
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Value visitSubstring(@NotNull DSDerivationGrammarParser.SubstringContext ctx) {

		/**
		 * funcArgType": "FUN",
		"expanded": true,
		"text": "SUBSTRING",
		"type": "Function",
		"typeDet": "SUBSTRING",
		 */
		RHExpression expr = new RHExpression();
		expr.setFuncArgType("Fun");
		expr.setType("Function");
		expr.setTypeDet("SUBSTRING");
		expr.setText("SUBSTRING");
		/*String id = generateTreeId();
		idStack.push(id);*/
		System.out.println("idStack --> "+idStack.size());
		expr.setParentId(idStack.get(idStack.size()-2));
		expr.setId(idStack.peek());
		visitorRHExprList.add(expr);
		
		StringBuilder sb = new StringBuilder();
		visitChilds(ctx, sb);
		Value v = new Value(sb.toString());
		return v;
	}

	@Override
	public Value visitSubStringVarName(@NotNull DSDerivationGrammarParser.SubStringVarNameContext ctx) {

		// Value varName = visit(ctx.subStringVarName());

		// //System.out.println("varName -->"+varName);

		Value v = new Value(ctx.getText());

		// //System.out.println("start -->"+visit(ctx.getChild(2))+" length ~~~
		// >>> "+visit(ctx.getChild(4)));
		// //System.out.println("visitor ~~ "+ctx.getRuleIndex()+"~~ depth
		// ->"+ctx.depth()+", ->"+ctx.getText()+" ->"
		// +ctx.getChild(0).getText()+" child 3 -->"+ctx.getChild(2).getText()+"
		// child 5 -->"+ctx.getChild(4).getText());
		return v;
	}

	@Override
	public Value visitInputLinkName(@NotNull DSDerivationGrammarParser.InputLinkNameContext ctx) {
		//System.out.println("visitInputLinkName " + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());

		LinkedHashMap<String, String> output = new LinkedHashMap<>();
		output.put("inputLinkName", ctx.getText());
		visitorStack.put("inputLinkName", ctx.getText());
		Value v = new Value(ctx.getText());
		return v;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Value visitColumnName(@NotNull DSDerivationGrammarParser.ColumnNameContext ctx) {

		//System.out.println("visitColumnName " + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		visitorStack.put("columnName", ctx.getText());

		Value v = new Value(ctx.getText() );
		return v;
	}

	@Override
	public Value visitLiteral(@NotNull DSDerivationGrammarParser.LiteralContext ctx) {
		//System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		// Literal is exception and not adding any root tag here.. since only
		// interested in sub nodes..
		StringBuilder sb = new StringBuilder();
		visitChilds(ctx, sb);
		Value v = new Value(sb.toString());
		return v;
	}

	@Override
	public Value visitStringLiteral(@NotNull DSDerivationGrammarParser.StringLiteralContext ctx) {
		/**
		 * "text": "MMDDYYYY",
				"typeDet": "String_Value",
				"funcArgType": "VALUE",
				"funcArgDataType": "S",
		 */
		RHExpression expr = new RHExpression();
		expr.setFuncArgType("S");
		expr.setType("VALUE");
		expr.setTypeDet("String_Value");
		expr.setText(cleanUpString(ctx.getText()));
		expr.setParentId(idStack.get(idStack.size()-2));
		expr.setId(idStack.peek());
		visitorRHExprList.add(expr);
		
		//System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value("<stringLiteral>" + ctx.getText() + "</stringLiteral>");
		return v;
	}
	
	static Map<String,String> replaceStrs = new LinkedHashMap<>();
	
	static{
		replaceStrs.put("%dd%mmm%yyyy", "DDMONYYYY");
	}
	
	
	
	public String cleanUpString(String s){
		if(StringUtils.startsWith(s, "'")){
			s = StringUtils.removeFirst(s, "'");
			s = StringUtils.removeEnd(s, "'");
		}else if(StringUtils.startsWith(s, "\"")){
			s = StringUtils.removeFirst(s, "\"");
			s = StringUtils.removeEnd(s, "\"");
		}
		if(replaceStrs.containsKey(s)){
			s = replaceStrs.get(s);
		}
		return s;
		
	}

	@Override
	public Value visitNumberLiteral(@NotNull DSDerivationGrammarParser.NumberLiteralContext ctx) {
		//System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		RHExpression expr = new RHExpression();
		expr.setFuncArgType("N");
		expr.setType("VALUE");
		expr.setTypeDet("Numeric_Value");
		expr.setText(ctx.getText());
		expr.setParentId(idStack.get(idStack.size()-2));
		expr.setId(idStack.peek());
		visitorRHExprList.add(expr);
		Value v = new Value("<numberLiteral>" + ctx.getText() + "</numberLiteral>");
		return v;
	}

	@Override
	public Value visitPrimary(@NotNull DSDerivationGrammarParser.PrimaryContext ctx) {
		//System.out.println("visitPrimary ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value(ctx.getText());
		return v;
	}
	static Map<String,String> funcMapping = new LinkedHashMap<>();
	static{
		funcMapping.put("IsNull", "IS_NULL");
		funcMapping.put("IsNotNull", "IS_NOT_NULL");
		funcMapping.put("UpCase", "UPPER");
		funcMapping.put("DateToString", "DATE_TO_STR");
	}

	@Override
	public Value visitFuncname(@NotNull DSDerivationGrammarParser.FuncnameContext ctx) {
		//System.out.println("visitFuncname ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		/**
		 * "text": "IS_NOT_NULL", "typeDet": "IS_NOT_NULL", "funcArgType": "FUN",
		 * "leaf": false, "type": "Common",
		 */
		RHExpression expr = new RHExpression();
		expr.setFuncArgType("FUN");
		expr.setType("Common");

		expr.setTypeDet(funcMapping.get(ctx.getText()));
		expr.setText(funcMapping.get(ctx.getText()));
		expr.setLeaf(false);
		expr.setParentId(idStack.get(idStack.size()-2));
		expr.setId(idStack.peek());
		visitorRHExprList.add(expr);
		Value v = new Value("<funcName>" + ctx.getText() + "</funcName>");
		return v;
	}

	public void visitChilds(ParserRuleContext ctx, StringBuilder sb) {

		int childCount = ctx.getChildCount();
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				sb.append(visit(ctx.getChild(i)));
				////System.out.println(("visitPrimaryExpr - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
			} else {

			}
		}

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Value visitPrimaryExpr(@NotNull DSDerivationGrammarParser.PrimaryExprContext ctx) {
		//System.out.println("visitPrimaryExpr ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		StringBuilder sb = new StringBuilder();
		sb.append("<primaryExpr>");
		
		/*RHExpression expr = new RHExpression();
		expr.setFuncArgType("FUN");
		expr.setType("Common");

		expr.setTypeDet(funcMapping.get(ctx.getText()));
		expr.setText(funcMapping.get(ctx.getText()));
		expr.setLeaf(false);
		visitorRHExprList.add(expr);*/
		
		/*"text": "CRD_ACCT_NBR",
		"typeDet": "Attribute",
		"funcArgType": "ATTRIBUTE",
		"leaf": true,
		"type": "Parameter",*/
		int childCount = ctx.getChildCount();
		RHExpression expr = new RHExpression();
		expr.setFuncArgType("ATTRIBUTE");
		expr.setType("Parameter");
		expr.setTypeDet("Attribute");
		expr.setParentId(idStack.get(idStack.size()-2));
		expr.setId(idStack.peek());
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				Value v = visit(ctx.getChild(i));
				if(i == 0){
					expr.get_otherAttrMap().put("_inputLinkName", v.asString());
				}else if (i ==2){
					expr.setText(v.asString());
				}
				sb.append(v.asString());
				////System.out.println(("visitPrimaryExpr ["+i+"]  - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
			} else {

			}
		}
		visitorRHExprList.add(expr);

		sb.append("</primaryExpr>");
		Value v = new Value(sb);
		return v;

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Value visitArguments(@NotNull DSDerivationGrammarParser.ArgumentsContext ctx) {
		// //System.out.println("visitArguments ~~ "+ctx.getRuleIndex()+"~~~~
		// "+ctx.getText());
		int childCount = ctx.getChildCount();
		StringBuilder sb = new StringBuilder();

		sb.append("<arguments>");
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				sb.append(visit(ctx.getChild(i)));
				////System.out.println(("visitPrimaryExpr - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
			} else {

			}
			////System.out.println(("visitArguments -" + childCount) + "~~~~" + visit(ctx.getChild(i)));
		}
		sb.append("</arguments>");
		Value v = new Value(sb.toString());
		return v;

	}

}
