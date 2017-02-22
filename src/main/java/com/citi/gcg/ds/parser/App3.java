/**
 * 
 */
package com.citi.gcg.ds.parser;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.ds.parser.listener.DSDerivationSubStringListener;
import com.citi.gcg.ds.parser.listener.DescriptiveErrorListener;
import com.citi.gcg.ds.parser.visitor.DSDerivationCustomVisitor;
import com.citi.gcg.ds.util.FilesUtil;
import com.citi.gcg.rh.beans.RHExpression;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author dosapati
 *
 */
public class App3 {
	
	final static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			System.out.println("ABC... !");

			String s = "If IsNull(Tfm_Standardize.CHRG_OFF_DT) then '' else UpCase(DateToString(Tfm_Standardize.CHRG_OFF_DT,'%dd%mmm%yyyy'))";
			String s2 = "If IsNull(Tfm_Standardize.CHRG_OFF_DT) then '' ";
			String s3 = "Tfm_Standardize.REPORTING_PERIOD [1,6]";
			String s4 = "Tfm_Standardize.ACCOUNT_NUMBER";
			String s5 = "'0.0'";
			ANTLRInputStream input = new ANTLRInputStream(s);
			DSDerivationGrammarLexer lexer = new DSDerivationGrammarLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			DSDerivationGrammarParser parser = new DSDerivationGrammarParser(tokens);
			parser.setBuildParseTree(true);
			parser.setErrorHandler(new BailErrorStrategy());
			parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

			ParseTree tree = parser.statement();

			System.out.println("tree " + tree.toStringTree(parser));

			// ParseTreeWalker walker=new ParseTreeWalker();

			// walker.walk(new DSDerivationSubStringListener(), tree);

			DSDerivationCustomVisitor visitor = new DSDerivationCustomVisitor();
			visitor.visit(tree);

			LinkedHashMap<String, String> outStack = visitor.visitorStack;
			
			LinkedList<RHExpression> visitorRHExprList = visitor.visitorRHExprList;
			

			/*Set<Entry<String, String>> outEntrySet = outStack.entrySet();
			// System.out.println(tree);
			for (Entry<String, String> entry : outEntrySet) {
				System.out.println(entry.getKey() + " #### " + entry.getValue());
			}*/
			
			FilesUtil.createFile("output/if_else_if.json", gson.toJson(visitorRHExprList));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			System.out.println("QQQ1 " + e.getMessage());
		}

		/**
		 * while​ ( expr!=​null​ ) { ​// while we have more expressions​ ​ ​//
		 * create new lexer and token stream for each line (expression)​ ​
		 * ANTLRInputStream input = ​new​ ANTLRInputStream(expr+​"\n"​); ​ ExprLexer
		 * lexer = ​new​ ExprLexer(input); ​ lexer.setLine(line); ​// notify lexer
		 * of input position​ ​ lexer.setCharPositionInLine(0); ​ CommonTokenStream
		 * tokens = ​new​ CommonTokenStream(lexer); ​ parser.setInputStream(tokens);
		 * ​// notify parser of new token stream​ ​ parser.stat(); ​// start the
		 * parser​ ​ expr = br.readLine(); ​// see if there's another line​ ​
		 * line++; ​ }
		 */

	}

}
