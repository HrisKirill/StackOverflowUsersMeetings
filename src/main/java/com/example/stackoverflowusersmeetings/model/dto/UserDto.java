package com.example.stackoverflowusersmeetings.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private int user_id;
    private String display_name;
    private int reputation;
    private String location;
    private String link;
    private String profile_image;

}
