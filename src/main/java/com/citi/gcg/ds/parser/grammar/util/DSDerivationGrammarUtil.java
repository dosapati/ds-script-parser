/**
 * 
 */
package com.citi.gcg.ds.parser.grammar.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
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
public class DSDerivationGrammarUtil {
	
	private static Logger log = LoggerFactory.getLogger(DSDerivationGrammarUtil.class);
	
	final static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

	
	public static void main(String[] args) throws Exception {
		
		String s1 = "If IsNull(Tfm_Standardize.CHRG_OFF_DT) then '' else UpCase(DateToString(Tfm_Standardize.CHRG_OFF_DT,'%dd%mmm%yyyy'))";
		String s2 = "If IsNull(Tfm_Standardize.CHRG_OFF_DT) then '' ";
		String s3 = "Tfm_Standardize.REPORTING_PERIOD [1,6]";
		String s4 = "Tfm_Standardize.ACCOUNT_NUMBER";
		String s = "'0.0'";
		
		String drStmt = "If IsNull(Lnk_Nxg_Data.ORA633) then '' else If Index(DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\"), '.',1)=0 THEN \n" + 
				"DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\") : '.0' ELSE DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\")";
		
		parseDerivation(drStmt);
		
		

	}

	/**
	 * @param args
	 * @return 
	 */
	public static LinkedList<RHExpression> parseDerivation(String derivationStmt) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(derivationStmt);
		DSDerivationGrammarLexer lexer = new DSDerivationGrammarLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DSDerivationGrammarParser parser = new DSDerivationGrammarParser(tokens);
		parser.setBuildParseTree(true);
		parser.setErrorHandler(new BailErrorStrategy());
		parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

		ParseTree tree = parser.statement();

		log.info("AST tree -->>>  " + tree.toStringTree(parser));

		// ParseTreeWalker walker=new ParseTreeWalker();

		// walker.walk(new DSDerivationSubStringListener(), tree);

		DSDerivationCustomVisitor visitor = new DSDerivationCustomVisitor();
		visitor.visit(tree);

		//LinkedHashMap<String, String> outStack = visitor.visitorStack;
		
		LinkedList<RHExpression> visitorRHExprList = visitor.visitorRHExprList;
		
		//FilesUtil.createFile("output/if_else_if.json", gson.toJson(visitorRHExprList));

		return visitorRHExprList;

	}

}
