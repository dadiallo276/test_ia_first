package com.diallo.ai.repository;

import com.diallo.ai.model.UserProfile;

public interface UserProfileRepository {

    UserProfile findByUserId(String userId);

    void save(UserProfile profile);

}