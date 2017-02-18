package com.citi.gcg.ds.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.citi.gcg.ds.parser.grammar.DSScriptGrammarLexer;
import com.citi.gcg.ds.parser.grammar.DSScriptGrammarParser;
import com.citi.gcg.ds.parser.listener.DSScriptGrammarInitRuleCustomListener;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ANTLRInputStream input = new ANTLRInputStream("initialize {\n" + 
        		"       // define our control variables\n" + 
        		"       int8 RowRejected0;\n" + 
        		"       int8 NullSetVar0;\n" + 
        		"\n" + 
        		"       // declare our intermediate variables for this section\n" + 
        		"       string InterVar0_0=\"A\";\n" + 
        		"       string InterVar0_1;\n" + 
        		"       string InterVar0_2;\n" + 
        		"       ustring InterVar0_3;\n" + 
        		"       ustring InterVar0_4;\n" + 
        		"       string InterVar0_5;\n" + 
        		"       ustring InterVar0_6;\n" + 
        		"\n" + 
        		"       // initialise constant values which require conversion\n" + 
        		"       InterVar0_0 = \"%yyyy%mm%dd\";\n" + 
        		"       InterVar0_1 = \"\";\n" + 
        		"       InterVar0_2 = \"%dd%mmm%yyyy\";\n" + 
        		"       InterVar0_3 = \"1\";\n" + 
        		"       InterVar0_4 = \"\";\n" + 
        		"       InterVar0_5 = \"0.0\";\n" + 
        		"       InterVar0_6 = \"2\";\n" + 
        		"}");
        DSScriptGrammarLexer lexer = new DSScriptGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DSScriptGrammarParser parser = new DSScriptGrammarParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.initializeRule();
        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker=new ParseTreeWalker();
        
        DSScriptGrammarInitRuleCustomListener a1 = new DSScriptGrammarInitRuleCustomListener();
        walker.walk(a1, tree);

        System.out.println("");
        
                
    }
}
