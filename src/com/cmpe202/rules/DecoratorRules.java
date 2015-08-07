package com.cmpe202.rules;

public abstract class DecoratorRules extends Rules{
	
	protected Rules rules;

	public boolean validate() {
		
		rules.validate();
		return false;
	}
	public DecoratorRules(Rules rules){
		this.rules = rules;
	}
	

}