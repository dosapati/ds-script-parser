package com.citi.gcg.rh.beans;

public class RHEntityAttributeObject {
	
	/**
	 * @return the ord
	 */
	public final int getOrd() {
		return ord;
	}
	/**
	 * @param ord the ord to set
	 */
	public final void setOrd(int ord) {
		this.ord = ord;
	}
	/**
	 * @return the filterData
	 */
	public final String getFilterData() {
		return filterData;
	}
	/**
	 * @param filterData the filterData to set
	 */
	public final void setFilterData(String filterData) {
		this.filterData = filterData;
	}
	
	//String inUseRules="";
	String inputEntityId="";
	String inputEntityType="";
	String inputEntityName="";
	String entityName="";
	String customAttr="Y";
	String extId;
	
	int ord;
	
	/**
	 * @return the inUseRules
	 *//*
	public final String getInUseRules() {
		return inUseRules;
	}
	*//**
	 * @param inUseRules the inUseRules to set
	 *//*
	public final void setInUseRules(String inUseRules) {
		this.inUseRules = inUseRules;
	}*/
	/**
	 * @return the inputEntityId
	 */
	public final String getInputEntityId() {
		return inputEntityId;
	}
	/**
	 * @param inputEntityId the inputEntityId to set
	 */
	public final void setInputEntityId(String inputEntityId) {
		this.inputEntityId = inputEntityId;
	}
	/**
	 * @return the inputEntityType
	 */
	public final String getInputEntityType() {
		return inputEntityType;
	}
	/**
	 * @param inputEntityType the inputEntityType to set
	 */
	public final void setInputEntityType(String inputEntityType) {
		this.inputEntityType = inputEntityType;
	}
	/**
	 * @return the inputEntityName
	 */
	public final String getInputEntityName() {
		return inputEntityName;
	}
	/**
	 * @param inputEntityName the inputEntityName to set
	 */
	public final void setInputEntityName(String inputEntityName) {
		this.inputEntityName = inputEntityName;
	}
	/**
	 * @return the entityName
	 */
	public final String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public final void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the customAttr
	 */
	public final String getCustomAttr() {
		return customAttr;
	}
	/**
	 * @param customAttr the customAttr to set
	 */
	public final void setCustomAttr(String customAttr) {
		this.customAttr = customAttr;
	}
	String dataDictionaryId = "";
	String physicalName = "";
	String attributeFormat;
	String lookupDisplay;
	String attributeId;
	String attributeName;
	String attributeType;
	String attributeOrder;
	String attributeDesc;
	/**
	 * @return the dataDictionaryId
	 */
	public final String getDataDictionaryId() {
		return dataDictionaryId;
	}
	/**
	 * @param dataDictionaryId the dataDictionaryId to set
	 */
	public final void setDataDictionaryId(String dataDictionaryId) {
		this.dataDictionaryId = dataDictionaryId;
	}
	/**
	 * @return the physicalName
	 */
	public final String getPhysicalName() {
		return physicalName;
	}
	/**
	 * @param physicalName the physicalName to set
	 */
	public final void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	/**
	 * @return the attributeFormat
	 */
	public final String getAttributeFormat() {
		return attributeFormat;
	}
	/**
	 * @param attributeFormat the attributeFormat to set
	 */
	public final void setAttributeFormat(String attributeFormat) {
		this.attributeFormat = attributeFormat;
	}
	/**
	 * @return the lookupDisplay
	 */
	public final String getLookupDisplay() {
		return lookupDisplay;
	}
	/**
	 * @param lookupDisplay the lookupDisplay to set
	 */
	public final void setLookupDisplay(String lookupDisplay) {
		this.lookupDisplay = lookupDisplay;
	}
	/**
	 * @return the attributeId
	 */
	public final String getAttributeId() {
		return attributeId;
	}
	/**
	 * @param attributeId the attributeId to set
	 */
	public final void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	/**
	 * @return the attributeName
	 */
	public final String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName the attributeName to set
	 */
	public final void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return the attributeType
	 */
	public final String getAttributeType() {
		return attributeType;
	}
	/**
	 * @param attributeType the attributeType to set
	 */
	public final void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * @return the attributeOrder
	 */
	public final String getAttributeOrder() {
		return attributeOrder;
	}
	/**
	 * @param attributeOrder the attributeOrder to set
	 */
	public final void setAttributeOrder(String attributeOrder) {
		this.attributeOrder = attributeOrder;
	}
	/**
	 * @return the attributeDesc
	 */
	public final String getAttributeDesc() {
		return attributeDesc;
	}
	/**
	 * @param attributeDesc the attributeDesc to set
	 */
	public final void setAttributeDesc(String attributeDesc) {
		this.attributeDesc = attributeDesc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RHEntityAttributeObject [inputEntityId=" + inputEntityId + ", inputEntityType=" + inputEntityType
				+ ", inputEntityName=" + inputEntityName + ", entityName=" + entityName + ", customAttr=" + customAttr
				+ ", dataDictionaryId=" + dataDictionaryId + ", physicalName=" + physicalName + ", attributeFormat="
				+ attributeFormat + ", lookupDisplay=" + lookupDisplay + ", attributeId=" + attributeId
				+ ", attributeName=" + attributeName + ", attributeType=" + attributeType + ", attributeOrder="
				+ attributeOrder + ", attributeDesc=" + attributeDesc + "]";
	}
	
	private String filterData;
	
	
	

}
