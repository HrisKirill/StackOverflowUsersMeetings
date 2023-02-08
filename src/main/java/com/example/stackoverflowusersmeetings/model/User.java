package com.example.stackoverflowusersmeetings.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class User {
    private String name;
    private String location;
    private int answerCount;
    private int questionCount;
    private String tags;
    private String linkToProfile;
    private String linkToAvatar;
}
