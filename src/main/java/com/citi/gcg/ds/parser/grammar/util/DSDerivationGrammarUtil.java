/**
 * 
 */
package com.citi.gcg.ds.parser.grammar.util;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;


/**
 * @author dosapati
 *
 */
public class DSDerivationGrammarUtil {
	
	public static void main(String[] args) {
		
		String drStmt = "If IsNull(Lnk_Nxg_Data.ORA633) then '' else If Index(DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\"), '.',1)=0 THEN \n" + 
				"DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\") : '.0' ELSE DecimalToString(Lnk_Nxg_Data.ORA633,\"suppress_zero\")";
		
		parseDerivation(drStmt);
		
		

	}

	/**
	 * @param args
	 */
	public static void parseDerivation(String derivationStmt) {
		// TODO Auto-generated method stub
		try{
		ANTLRInputStream input = new ANTLRInputStream(derivationStmt);
		
		 DSDerivationGrammarLexer lexer = new DSDerivationGrammarLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        DSDerivationGrammarParser parser = new DSDerivationGrammarParser(tokens);
	        parser.setBuildParseTree(true);
	        ParseTree tree = parser.statement();
	        System.out.println(tree.toStringTree(parser));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("derivationStmt --> "+derivationStmt+" , error message ->"+e.getMessage());
		}

	}

}
