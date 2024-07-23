package com.kafka.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuizQuestionWrapper {
    private int id;

    private String question;

    private String[] options;
}
