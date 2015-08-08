package com.cmpe202.rules;

public class MemberRules extends Rules{

	private String emailId = null;
	private String licenseNumber = null;
	private String member_role = null;
	
	public boolean validate() {
		boolean validationStatus = false;

		if(isnotNullcheck(emailId,licenseNumber, member_role)){
			
			if(member_role.equalsIgnoreCase("Driver")){
				if(isValidEmailAddress(emailId) && isValidLicenseNumber(licenseNumber)){
					validationStatus = true;
				}
			}else if(member_role.equalsIgnoreCase("Customer") || member_role.equalsIgnoreCase("executive") ){
				if(isValidEmailAddress(emailId)){
					validationStatus = true;
				}
			}
		}
		return validationStatus;
	}
	public MemberRules(String emailId, String licenseNumber, String member_role){
		this.emailId = emailId;
		this.licenseNumber = licenseNumber;
		this.member_role = member_role;
	}
	public boolean isnotNullcheck(String emailId, String licenseNumber, String member_role){
		boolean return_status = true; 
		if(member_role.equalsIgnoreCase("Driver")){
			if(emailId == null || licenseNumber == null)
				return_status = false;
		}else if(member_role.equalsIgnoreCase("Customer") || member_role.equalsIgnoreCase("executive")){
			if(emailId == null)
				return_status = false;
		}
		return return_status;
		
		
	}
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
        
	}
	public boolean isValidLicenseNumber(String licenseNumber){
		//String ePattern = "[A-Z]\\d(\\d\\d-\\d\\d){3}";
		String ePattern = "[A-Z](\\d){7}";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(licenseNumber);
        return m.matches();
	}

}