package com.diallo.ai.extractor;

import com.diallo.ai.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserProfileExtractor {

    private static final Pattern NAME_PATTERN =
            Pattern.compile("(?i)je m'appelle\\s+(\\w+)");

    public void extract(UserProfile profile, String message) {

        Matcher matcher = NAME_PATTERN.matcher(message);

        if (matcher.find()) {

            profile.setFirstName(matcher.group(1));

        }

    }

}