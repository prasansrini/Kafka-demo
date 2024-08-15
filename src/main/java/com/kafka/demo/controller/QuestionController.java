package com.kafka.demo.controller;

import com.kafka.demo.model.QuizQuestionWrapper;
import com.kafka.demo.service.KafkaProducerService;
import com.kafka.demo.service.QuestionFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionFetcherService service;

    @Autowired
    private KafkaProducerService publisherService;

    @GetMapping("/send-message")
    public void sendMessage() {
        System.out.println("Send message request received!");
        publisherService.sendMessage("Hello from Kafka!!");
    }

    @GetMapping("/my-question")
    public ResponseEntity<List<QuizQuestionWrapper>> getQuestions() {
        List<QuizQuestionWrapper> questions = service.getQuestions();

        if (questions != null && !questions.isEmpty()) {
            System.out.println(questions);

            questions.forEach(quiz -> {
                if (quiz.getQuestion().length() > 65) {
                    System.out.println(quiz.getQuestion());
                }
            });

            questions.stream()
                    .map(QuizQuestionWrapper::getQuestion)
                    .filter(quiz -> quiz.length() > 65)
                    .forEach(System.out::println);


            System.out.println(questions.stream()
                    .filter(quiz -> quiz.getQuestion().length() > 40)
                    .count()
            );

            return ResponseEntity.ok(questions);
        } else {
            System.out.println("Questions empty!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
