package com.citi.gcg.ds.parser.listener;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.citi.gcg.ds.parser.grammar.DSDerivationGrammarBaseListener;
import com.citi.gcg.rh.beans.RHExpression;

public class DSDerivationGrammarRootListener extends DSDerivationGrammarBaseListener {
	
	LinkedHashMap<Integer,LinkedList<RHExpression>> exprList = new LinkedHashMap<>();

}
