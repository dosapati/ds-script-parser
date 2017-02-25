package com.citi.gcg.rh.beans;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RHExpression {
	
	/**
	 * @return the rec
	 */
	public final RHEntityAttributeObject getRec() {
		return rec;
	}
	/**
	 * @param rec the rec to set
	 */
	public final void setRec(RHEntityAttributeObject rec) {
		this.rec = rec;
	}
	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the root
	 */
	public final boolean isRoot() {
		return root;
	}
	/**
	 * @param root the root to set
	 */
	public final void setRoot(boolean root) {
		this.root = root;
	}
	/**
	 * @return the isFirst
	 */
	public final boolean isFirst() {
		return isFirst;
	}
	/**
	 * @param isFirst the isFirst to set
	 */
	public final void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	/**
	 * @return the isLast
	 */
	public final boolean isLast() {
		return isLast;
	}
	/**
	 * @param isLast the isLast to set
	 */
	public final void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	/**
	 * @return the depth
	 */
	public final int getDepth() {
		return depth;
	}
	/**
	 * @param depth the depth to set
	 */
	public final void setDepth(int depth) {
		this.depth = depth;
	}
	/**
	 * @return the index
	 */
	public final int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public final void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * @return the visible
	 */
	public final boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public final void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the leaf
	 */
	public final boolean isLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public final void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	/**
	 * @return the typeDet
	 */
	public final String getTypeDet() {
		return typeDet;
	}
	/**
	 * @param typeDet the typeDet to set
	 */
	public final void setTypeDet(String typeDet) {
		this.typeDet = typeDet;
	}
	/**
	 * @return the funcArgType
	 */
	public final String getFuncArgType() {
		return funcArgType;
	}
	/**
	 * @param funcArgType the funcArgType to set
	 */
	public final void setFuncArgType(String funcArgType) {
		this.funcArgType = funcArgType;
	}
	/**
	 * @return the children
	 */
	public List<RHExpression> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<RHExpression> children) {
		this.children = children;
	}
	private String text =  "";
	private String typeDet = "";
	private String funcArgType = "";
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	private String funcArgDataType;
	private String type = "";
	private String id = "";
	private String parentId = "";
	private boolean root =  false;
	private boolean isFirst =  false;
	private boolean isLast =  false;	
	private int index = 0;
	private int depth = 0;
	private List<RHExpression> children = new LinkedList<>();
	private boolean visible = true;
	private boolean leaf = false;
	
	
	private String _inputLinkName;
	private Map<String,String> _otherAttrMap = new LinkedHashMap<>();
	
	/**
	 * @return the funcArgDataType
	 */
	public String getFuncArgDataType() {
		return funcArgDataType;
	}
	/**
	 * @param funcArgDataType the funcArgDataType to set
	 */
	public void setFuncArgDataType(String funcArgDataType) {
		this.funcArgDataType = funcArgDataType;
	}
	public String get_inputLinkName() {
		return _inputLinkName;
	}
	public void set_inputLinkName(String _inputLinkName) {
		this._inputLinkName = _inputLinkName;
	}
	public Map<String,String> get_otherAttrMap() {
		return _otherAttrMap;
	}
	public void set_otherAttrMap(Map<String,String> _otherAttrMap) {
		this._otherAttrMap = _otherAttrMap;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	private RHEntityAttributeObject rec;
	
	
	

}
