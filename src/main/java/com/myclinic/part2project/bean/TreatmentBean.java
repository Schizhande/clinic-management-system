package com.myclinic.part2project.bean;

import java.io.Serializable;

public class TreatmentBean implements Serializable {
	
	private String question;
	private String answer;
	
	private boolean found;
	
	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public TreatmentBean(String qn){
		this.question=qn;
	}
	
	public String getResponse(String qn){
		if(qn.equalsIgnoreCase(this.getQuestion())){
			return this.getAnswer();
		}
		return null;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
