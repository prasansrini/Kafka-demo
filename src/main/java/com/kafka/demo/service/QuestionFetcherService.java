package com.kafka.demo.service;

import com.kafka.demo.client.ApiClient;
import com.kafka.demo.model.QuizQuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class QuestionFetcherService {
    @Autowired
    private ApiClient apiClient;

    public List<QuizQuestionWrapper> getQuestions() {
        ResponseEntity<QuizQuestionWrapper[]> responseEntity = apiClient.getQuestions();

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            if (responseEntity.getBody() != null) {

                return Arrays.asList(responseEntity.getBody());
            }
        }

        return null;
    }

}
