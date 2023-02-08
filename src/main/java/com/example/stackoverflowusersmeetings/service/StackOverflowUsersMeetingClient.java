package com.example.stackoverflowusersmeetings.service;

import com.example.stackoverflowusersmeetings.model.Tag;
import com.example.stackoverflowusersmeetings.model.dto.TagsDto;
import com.example.stackoverflowusersmeetings.model.dto.UserDto;
import com.example.stackoverflowusersmeetings.model.dto.UsersDto;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.example.stackoverflowusersmeetings.constants.ExchangeApiConstants.KEY;

@Component
public class StackOverflowUsersMeetingClient {
    HttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory clientHttpRequestFactories = new HttpComponentsClientHttpRequestFactory(httpClient);
    private final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactories);

    public List<UserDto> getUser() {
        try {
            UsersDto usersDto = restTemplate.getForObject(
                    new URI("https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow"
                            + KEY),
                    UsersDto.class);
            if (usersDto != null) {
                return usersDto.getItems();
            } else {
                throw new RuntimeException("UsersDto is null");
            }

        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int getAnswersByUserId(int id) {
        try {
            UsersDto usersDto = restTemplate.getForObject(
                    new URI("https://api.stackexchange.com/2.3/users/" + id + "/answers?order=desc&sort=activity&site=stackoverflow"
                            + KEY), UsersDto.class);
            if (usersDto != null) {
                return usersDto.getItems().size();
            } else {
                throw new RuntimeException("UsersDto is null");
            }
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int getQuestionsByUserId(int id) {
        try {
            UsersDto usersDto = restTemplate.getForObject(
                    new URI("https://api.stackexchange.com/2.3/users/" + id + "/questions?order=desc&sort=activity&site=stackoverflow"
                            + KEY), UsersDto.class);
            if (usersDto != null) {
                return usersDto.getItems().size();
            } else {
                throw new RuntimeException("UsersDto is null");
            }
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Tag> getTagsByUserId(int id) {
        try {
            TagsDto tagsDto = restTemplate.getForObject(
                    new URI("https://api.stackexchange.com/2.3/users/" + id + "/tags?order=desc&sort=popular&site=stackoverflow"
                            + KEY), TagsDto.class);
            if (tagsDto != null) {
                return tagsDto.getItems();
            } else {
                throw new RuntimeException();
            }
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
