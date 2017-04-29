package com.mm.web.bbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("navigationBean")
@Scope("session")
public class NavigationBean implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String navigateTo ="index.jsf";

		public NavigationBean(){
			
		}
		
		public String goToPage(){
			String page = "index.jsf";
			navigateTo = page;
			return page;
			
		}

		public String getNavigateTo() {
			return navigateTo;
		}

		public void setNavigateTo(String navigateTo) {
			this.navigateTo = navigateTo;
		}
}
