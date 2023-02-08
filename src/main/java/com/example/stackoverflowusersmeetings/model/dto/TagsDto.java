package com.example.stackoverflowusersmeetings.model.dto;

import com.example.stackoverflowusersmeetings.model.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TagsDto {
    private List<Tag> items;
}
