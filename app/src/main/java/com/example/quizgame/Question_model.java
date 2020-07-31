package com.example.quizgame;

public class Question_model {
    private String question;
    private String options_a;
    private String options_b;
    private String options_c;
    private String options_d;
    private String correctAnswer;

    public Question_model(String question, String options_a, String options_b, String options_c, String options_d, String correctAnswer) {
        this.question = question;
        this.options_a = options_a;
        this.options_b = options_b;
        this.options_c = options_c;
        this.options_d = options_d;
        this.correctAnswer = correctAnswer;
    }

    public Question_model(String[] question, String[] option_a, String[] option_b, String[] option_c, String[] option_d, String[] answer) {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions_a() {
        return options_a;
    }

    public void setOptions_a(String options_a) {
        this.options_a = options_a;
    }

    public String getOptions_b() {
        return options_b;
    }

    public void setOptions_b(String options_b) {
        this.options_b = options_b;
    }

    public String getOptions_c() {
        return options_c;
    }

    public void setOptions_c(String options_c) {
        this.options_c = options_c;
    }

    public String getOptions_d() {
        return options_d;
    }

    public void setOptions_d(String options_d) {
        this.options_d = options_d;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
