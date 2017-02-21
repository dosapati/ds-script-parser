package com.citi.gcg.ds.parser;

import java.io.File;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarParser;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser;
import com.citi.gcg.ds.parser.listener.DSDerivationAssignListener;
import com.citi.gcg.ds.parser.listener.DSDerivationExpressionListener;
import com.citi.gcg.ds.parser.listener.DSDerivationLiteralListener;
import com.citi.gcg.ds.parser.listener.DSDerivationSubStringListener;
import com.citi.gcg.ds.parser.listener.DSScriptGrammarInitRuleCustomListener;
import com.citi.gcg.ds.parser.listener.DescriptiveErrorListener;
import com.citi.gcg.ds.parser.visitor.DSDerivationCustomVisitor;


public class App2 {
	
	public static void main(String[] args) {
		try{
		System.out.println("ABC... !");
		
		String s = "Tfm_Standardize.REPORTING_PERIOD [1,6]";
		ANTLRInputStream input = new ANTLRInputStream(s);
        DSDerivationGrammarLexer lexer = new DSDerivationGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DSDerivationGrammarParser parser = new DSDerivationGrammarParser(tokens);
        parser.setBuildParseTree(true);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);

        ParseTree tree = parser.substring();
        
        
        
        System.out.println("tree "+tree.toStringTree(parser));

        ParseTreeWalker walker=new ParseTreeWalker();
        
        walker.walk(new DSDerivationSubStringListener(), tree);
        
        DSDerivationCustomVisitor visitor = new DSDerivationCustomVisitor();
        visitor.visit(tree);
        
        LinkedHashMap<String,String> outStack = visitor.visitorStack;
        
        Set<Entry<String, String>> outEntrySet = outStack.entrySet();
        
        for (Entry<String, String> entry : outEntrySet) {
			System.out.println(entry.getKey()+" ~~~ "+entry.getValue());
		}
        
        
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
			System.out.println("QQQ1 "+e.getMessage());
		}
        
        
		
		
	}

}
