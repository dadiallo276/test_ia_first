package com.diallo.ai.service;

import com.diallo.ai.model.UserProfile;
import com.diallo.ai.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {

        this.repository = repository;

    }

    @Override
    public UserProfile getProfile(String userId) {

        UserProfile profile = repository.findByUserId(userId);

        if (profile == null) {

            profile = new UserProfile(userId);

            repository.save(profile);

        }

        return profile;

    }

    @Override
    public void save(UserProfile profile) {

        repository.save(profile);

    }

}