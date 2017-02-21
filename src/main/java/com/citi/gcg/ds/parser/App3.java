/**
 * 
 */
package com.citi.gcg.ds.parser;

import java.util.LinkedHashMap;
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

/**
 * @author dosapati
 *
 */
public class App3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
		
System.out.println("ABC... !");
		
		String s = "If IsNull(Tfm_Standardize.CHRG_OFF_DT) then '' else UpCase(DateToString(Tfm_Standardize.CHRG_OFF_DT,'%dd%mmm%yyyy'))";
		ANTLRInputStream input = new ANTLRInputStream(s);
        DSDerivationGrammarLexer lexer = new DSDerivationGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DSDerivationGrammarParser parser = new DSDerivationGrammarParser(tokens);
        parser.setBuildParseTree(true);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

        ParseTree tree = parser.if_then_else();
        
        
        
        System.out.println("tree "+tree.toStringTree(parser));

        //ParseTreeWalker walker=new ParseTreeWalker();
        
        //walker.walk(new DSDerivationSubStringListener(), tree);
        
        DSDerivationCustomVisitor visitor = new DSDerivationCustomVisitor();
        visitor.visit(tree);
        
        LinkedHashMap<String,String> outStack = visitor.visitorStack;
        
        Set<Entry<String, String>> outEntrySet = outStack.entrySet();
       // System.out.println(tree);
        for (Entry<String, String> entry : outEntrySet) {
			System.out.println(entry.getKey()+" #### "+entry.getValue());
		}
        
        
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
			System.out.println("QQQ1 "+e.getMessage());
		}
        
        
		

		
/**
 * while​ ( expr!=​null​ ) {             ​// while we have more expressions​
​ 	    ​// create new lexer and token stream for each line (expression)​
​ 	    ANTLRInputStream input = ​new​ ANTLRInputStream(expr+​"\n"​);
​ 	    ExprLexer lexer = ​new​ ExprLexer(input);
​ 	    lexer.setLine(line);           ​// notify lexer of input position​
​ 	    lexer.setCharPositionInLine(0);
​ 	    CommonTokenStream tokens = ​new​ CommonTokenStream(lexer);
​ 	    parser.setInputStream(tokens); ​// notify parser of new token stream​
​ 	    parser.stat();                 ​// start the parser​
​ 	    expr = br.readLine();          ​// see if there's another line​
​ 	    line++;
​ 	}
 */

	}

}
