package com.kafka.demo.client;

import com.kafka.demo.model.QuizQuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.kafka.demo.util.Constants.PATH_QUESTIONS;

@Service
public class ApiClient {
    @Value("${quiz.app.base.url}")
    public String BASE_URL;

    @Autowired
    private RestTemplate mRestTemplate;

    public ResponseEntity<QuizQuestionWrapper[]> getQuestions() {
        String url = BASE_URL + PATH_QUESTIONS;

        System.out.println(url);

        return mRestTemplate.getForEntity(url, QuizQuestionWrapper[].class);
    }
}
