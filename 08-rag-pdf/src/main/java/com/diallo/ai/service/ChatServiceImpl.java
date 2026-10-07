package com.diallo.ai.service;


import org.springframework.stereotype.Service;

import com.diallo.ai.assistant.Assistant;
import com.diallo.ai.assistant.ProfileExtractor;
import com.diallo.ai.dto.ChatRequest;
import com.diallo.ai.dto.ChatResponse;
import com.diallo.ai.model.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ChatServiceImpl implements ChatService {

	private final Assistant assistant;

	private final ProfileExtractor extractor;

	private final UserProfileService profileService;
	
	private final ObjectMapper mapper;

	public ChatServiceImpl(Assistant assistant, ProfileExtractor extractor, UserProfileService profileService, ObjectMapper mapper) {

		this.assistant = assistant;
		this.extractor = extractor;
		this.profileService = profileService;
		this.mapper = mapper;
	}

	@Override
	public ChatResponse chat(ChatRequest request) {

//
//        String response =
//                assistant.chat(request.getMessage());

//    	String response = assistant.chat(
//    	        request.getUserId(),
//    	        request.getMessage()
//    	);

//		UserProfile profile = profileService.getProfile(request.getUserId());
//		
//		System.out.println("profile : "+ profile);
//
//		UserProfile extracted = new UserProfile();
//
//		try {
//
//		    String json =
//		            extractor.extract(request.getMessageProfile());
//
//		    extracted =
//		            mapper.readValue(
//		                    json,
//		                    UserProfile.class);
//
//		}
//		catch (Exception e) {
//
//		    System.out.println("Impossible de parser le JSON");
//
//		}
//
//		merge(profile, extracted);
//
//		profileService.save(profile);

//		String prompt = buildPrompt(profile, request.getMessage());
//		
//		System.out.println("prompt : "+ prompt);

		String response = assistant.chat(request.getUserId(), request.getMessage());

		return new ChatResponse(response);
	}
	
	private void merge(
	        UserProfile profile,
	        UserProfile extracted) {

	    if (extracted == null) {
	        return;
	    }

	    if (extracted.getFirstName() != null)
	        profile.setFirstName(extracted.getFirstName());

	    if (extracted.getLastName() != null)
	        profile.setLastName(extracted.getLastName());

	    if (extracted.getJob() != null)
	        profile.setJob(extracted.getJob());

	    if (extracted.getExperience() != null)
	        profile.setExperience(extracted.getExperience());

	    if (extracted.getCity() != null)
	        profile.setCity(extracted.getCity());

	}
	
	private String buildPrompt(
	        UserProfile profile,
	        String question) {

	    StringBuilder sb = new StringBuilder();

	    sb.append("""
	Informations connues :

	""");

	    if (profile.getFirstName() != null)
	        sb.append("Prénom : ")
	          .append(profile.getFirstName())
	          .append("\n");

	    if (profile.getLastName() != null)
	        sb.append("Nom : ")
	          .append(profile.getLastName())
	          .append("\n");

	    if (profile.getJob() != null)
	        sb.append("Métier : ")
	          .append(profile.getJob())
	          .append("\n");

	    if (profile.getExperience() != null)
	        sb.append("Expérience : ")
	          .append(profile.getExperience())
	          .append(" ans\n");

	    if (profile.getCity() != null)
	        sb.append("Ville : ")
	          .append(profile.getCity())
	          .append("\n");

	    sb.append("""

	Question :

	""");

	    sb.append(question);

	    return sb.toString();

	}

}