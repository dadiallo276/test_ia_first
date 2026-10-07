package com.diallo.ai.repository;

import com.diallo.ai.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserProfileRepository implements UserProfileRepository {

    private final Map<String, UserProfile> database =
            new ConcurrentHashMap<>();

    @Override
    public UserProfile findByUserId(String userId) {

        return database.get(userId);

    }

    @Override
    public void save(UserProfile profile) {

        database.put(profile.getUserId(), profile);

    }

}