package com.example.stackoverflowusersmeetings.service;

import com.example.stackoverflowusersmeetings.model.Tag;
import com.example.stackoverflowusersmeetings.model.User;
import com.example.stackoverflowusersmeetings.model.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.stackoverflowusersmeetings.constants.ApplicationRequirementsConstants.*;

@Service
public class UserService {

    private final StackOverflowUsersMeetingClient stackOverflowUsersMeetingClient;

    @Autowired
    public UserService(StackOverflowUsersMeetingClient stackOverflowUsersMeetingClient) {
        this.stackOverflowUsersMeetingClient = stackOverflowUsersMeetingClient;
    }

    public List<User> findAll() {
        return stackOverflowUsersMeetingClient.getUser().stream()
                .map(this::toUser)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private User toUser(@NotNull UserDto userDto) {
        List<Tag> tags = stackOverflowUsersMeetingClient.getTagsByUserId(userDto.getUser_id());
        boolean isContain = false;
        for (Tag tag :
                tags) {
            for (String name :
                    TAGS_LIST) {
                if (name.equalsIgnoreCase(tag.getName())) {
                    isContain = true;
                    break;
                }
            }
        }

        if (userDto.getLocation() != null && (userDto.getLocation().contains("Romania")
                || userDto.getLocation().contains("Moldova"))
                && userDto.getReputation() > REPUTATION_MIN
                && stackOverflowUsersMeetingClient.getAnswersByUserId(userDto.getUser_id()) > ANSWER_MIN
            && isContain) {
            return User.builder()
                    .name(userDto.getDisplay_name())
                    .linkToProfile(userDto.getLink())
                    .linkToAvatar(userDto.getProfile_image())
                    .location(userDto.getLocation())
                    .tags(stackOverflowUsersMeetingClient.getTagsByUserId(userDto.getUser_id()).stream()
                            .map(Tag::getName)
                            .collect(Collectors.joining(",")))
                    .answerCount(stackOverflowUsersMeetingClient.getAnswersByUserId(userDto.getUser_id()))
                    .questionCount(stackOverflowUsersMeetingClient.getQuestionsByUserId(userDto.getUser_id()))
                    .build();
        }

        return null;
    }
}
