/**
 * 
 */
package com.citi.gcg.ds.parser.visitor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarBaseVisitor;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.rh.beans.Value;

/**
 * @author dosapati
 *
 */
public class DSDerivationCustomVisitor extends DSDerivationGrammarBaseVisitor<Value> {

	public LinkedHashMap<String, String> visitorStack = new LinkedHashMap<>();

	@Override
	public Value visitIf_then_else(@NotNull DSDerivationGrammarParser.If_then_elseContext ctx) {
		// ctx.expression()
		StringBuilder sb = new StringBuilder();
		sb.append("<if_then_else>");
		int childCount = ctx.getChildCount();
		for (int i = 0; i < childCount; i++) {
			if (i == 1 || i == 3 || i == 5) {
				System.out.println(ctx.getChild(i).getClass().getName());
				if (i == 1) {
					sb.append("<if>").append(visit(ctx.getChild(i))).append("</if>");
				} else if (i == 3) {
					sb.append("<then>").append(visit(ctx.getChild(i))).append("</then>");
				} else if (i == 5) {
					sb.append("<else>").append(visit(ctx.getChild(i))).append("</else>");
				}
				// visitorStack.put("if_expr_"+i,
				// visit(ctx.getChild(i)).asString());
			} else {
				System.out.println("Terminal node -->" + ctx.getText());
			}

		}
		sb.append("</if_then_else>");
		visitorStack.put("if_then_else", sb.toString());
		return new Value(sb.toString());
		// return visitChildren(ctx);
	}

	int exprCounter = 1;

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
		for (int i = 0; i < childCount; i++) {

			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				System.out.println(ctx.getChild(i).getClass().getName());
				sb.append(visit(ctx.getChild(i)).asString());
				// visitorStack.put("expr_"+(exprCounter++),
				// visit(ctx.getChild(i)).asString());
			} else {
				System.out.println(ctx.getText());
			}

			// visitorStack.put("expr_"+1, visit(ctx.getChild(i)).asString());

		}
		sb.append("</expression>");
		System.out.println("expression --->" + sb.toString());
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

		// Value inputLinkName = (Map)visit(ctx.subStringVarName());
		// System.out.println("subs -- >
		// "+ctx.getChild(0).getClass().getName());

		visitorStack.put("start", visit(ctx.getChild(2)).asString());
		visitorStack.put("length", visit(ctx.getChild(4)).asString());

		// Value columnName = visit(ctx.());

		// System.out.println("varName -->"+m11.get("inputLinkName")+" ...
		// "+m11.get("columnName"));

		System.out.println("visitor ~~ " + ctx.getRuleIndex() + "~~ depth ->" + ctx.depth() + ",  ->" + ctx.getText()
				+ " ->" + ctx.getChild(0).getText() + " child 3 -->" + ctx.getChild(2).getText() + " child 5 -->"
				+ ctx.getChild(4).getText());
		return new Value(ctx.getText());
	}

	@Override
	public Value visitSubStringVarName(@NotNull DSDerivationGrammarParser.SubStringVarNameContext ctx) {

		// Value varName = visit(ctx.subStringVarName());

		// System.out.println("varName -->"+varName);

		Value v = new Value(ctx.getText());

		// System.out.println("start -->"+visit(ctx.getChild(2))+" length ~~~
		// >>> "+visit(ctx.getChild(4)));
		// System.out.println("visitor ~~ "+ctx.getRuleIndex()+"~~ depth
		// ->"+ctx.depth()+", ->"+ctx.getText()+" ->"
		// +ctx.getChild(0).getText()+" child 3 -->"+ctx.getChild(2).getText()+"
		// child 5 -->"+ctx.getChild(4).getText());
		return v;
	}

	@Override
	public Value visitInputLinkName(@NotNull DSDerivationGrammarParser.InputLinkNameContext ctx) {
		System.out.println("visitInputLinkName " + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());

		LinkedHashMap<String, String> output = new LinkedHashMap<>();
		output.put("inputLinkName", ctx.getText());
		visitorStack.put("inputLinkName", ctx.getText());
		Value v = new Value("<inputLinkName>" + ctx.getText() + "</inputLinkName>");
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

		System.out.println("visitColumnName " + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		visitorStack.put("columnName", ctx.getText());

		Value v = new Value("<columnName>" + ctx.getText() + "</columnName>");
		return v;
	}

	@Override
	public Value visitLiteral(@NotNull DSDerivationGrammarParser.LiteralContext ctx) {
		System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		// Literal is exception and not adding any root tag here.. since only
		// interested in sub nodes..
		StringBuilder sb = new StringBuilder();
		visitChilds(ctx, sb);
		Value v = new Value(sb.toString());
		return v;
	}

	@Override
	public Value visitStringLiteral(@NotNull DSDerivationGrammarParser.StringLiteralContext ctx) {
		System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value("<stringLiteral>" + ctx.getText() + "</stringLiteral>");
		return v;
	}

	@Override
	public Value visitNumberLiteral(@NotNull DSDerivationGrammarParser.NumberLiteralContext ctx) {
		System.out.println("visitLiteral" + " ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value("<numberLiteral>" + ctx.getText() + "</numberLiteral>");
		return v;
	}

	@Override
	public Value visitPrimary(@NotNull DSDerivationGrammarParser.PrimaryContext ctx) {
		System.out.println("visitPrimary ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value(ctx.getText());
		return v;
	}

	@Override
	public Value visitFuncname(@NotNull DSDerivationGrammarParser.FuncnameContext ctx) {
		System.out.println("visitFuncname ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		Value v = new Value("<funcName>" + ctx.getText() + "</funcName>");
		return v;
	}

	public void visitChilds(ParserRuleContext ctx, StringBuilder sb) {

		int childCount = ctx.getChildCount();
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				sb.append(visit(ctx.getChild(i)));
				System.out.println(("visitPrimaryExpr - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
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
		System.out.println("visitPrimaryExpr ~~ " + ctx.getRuleIndex() + "~~~~ " + ctx.getText());
		StringBuilder sb = new StringBuilder();
		sb.append("<primaryExpr>");
		int childCount = ctx.getChildCount();
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				sb.append(visit(ctx.getChild(i)));
				System.out.println(("visitPrimaryExpr - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
			} else {

			}
		}
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
		// System.out.println("visitArguments ~~ "+ctx.getRuleIndex()+"~~~~
		// "+ctx.getText());
		int childCount = ctx.getChildCount();
		StringBuilder sb = new StringBuilder();

		sb.append("<arguments>");
		for (int i = 0; i < childCount; i++) {
			if (!(ctx.getChild(i) instanceof TerminalNodeImpl)) {
				sb.append(visit(ctx.getChild(i)));
				System.out.println(("visitPrimaryExpr - " + childCount) + " ~~~~ " + visit(ctx.getChild(i)));
			} else {

			}
			System.out.println(("visitArguments -" + childCount) + "~~~~" + visit(ctx.getChild(i)));
		}
		sb.append("</arguments>");
		Value v = new Value(sb.toString());
		return v;

	}

}
