package com.diallo.ai.service;

import com.diallo.ai.model.UserProfile;

public interface UserProfileService {

    UserProfile getProfile(String userId);

    void save(UserProfile profile);

}