package edu.ycp.cs320.entrelink.controller;

import java.util.List;

import edu.ycp.cs320.entrelink.model.NewUser;
import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.entrelink.userdb.persist.DatabaseProvider;
import edu.ycp.cs320.entrelink.userdb.persist.DerbyDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.FakeDatabase;
import edu.ycp.cs320.entrelink.userdb.persist.IDatabase;

public class SignupController {
	
	private NewUser model;
	
	public void setModel(NewUser model) {
		this.model = model;
	}
	
	
	IDatabase db = new DerbyDatabase();
	User getUser;
	
	public boolean verifyIsNewUser() {
		if(model != null) {
			if(db.findUserByEmailOrUsername(model.getNewUsername()).equals(null)) {
				if(db.findUserByEmailOrUsername(model.getNewEmail()).equals(null)) {
					return true;
				}
			}
		}
	return false;
	}
	
	public boolean verifyEmailsAreSame() {
		if(model.getNewEmail().equals(model.getConfirmEmail())) {
			return true;
		}
		return false;
	}
	
	public boolean verifyPasswordsAreSame() {
		if(model.getNewPassword().equals(model.getConfirmPassword())) {
			return true;
		}
		return false;
	}
	
	public boolean verifyEmailIsValid() {
		String testEmail = model.getNewEmail();
		Integer emailLength = testEmail.length();
		String getExtension = testEmail.substring(emailLength-8, emailLength);
		if(getExtension.equals("@ycp.edu")) {
			return true;
		}
		return false;
	}
	
	public NewUser getModel() {
		return model;
	}
	
}