/**
 * 
 */
package com.blogspot.mallampatirahul.beans;

/**
 * @author rahulmallampati
 *
 */
public class LoginResult {
	private String  loginResult;
	private boolean isAdmin;
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(int admin) {
		if(admin>0) {
			isAdmin=true;	
		} else {
			isAdmin=false;
		} 
	}
	
}
