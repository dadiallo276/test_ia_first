package com.diallo.ai.dto;

public class ChatRequest {
	
	//@NotBlank(message = "Le message ne doit pas être vide")
    private String message;
	
	private String messageProfile;
	
	//@NotBlank(message = "Le message ne doit pas être vide")
	private String userId;

    public ChatRequest(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessageProfile() {
		return messageProfile;
	}

	public void setMessageProfile(String messageProfile) {
		this.messageProfile = messageProfile;
	}
}