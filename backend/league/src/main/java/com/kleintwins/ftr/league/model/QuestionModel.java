package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lge_question")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionModel {

    @EmbeddedId
    private QuestionId questionId;

    @Column(nullable = false, length = 1000)
    @NotNull(message = "Question text cannot be null")
    @Size(max = 1000, message = "Question text must be at most 1000 characters")
    private String text;

    @ManyToOne
    @MapsId("surveyId")
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyModel survey;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private int points; // Points assigned if correct

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AnswerOptionModel> answerOptions = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ParticipantAnswerModel> participantAnswers = new ArrayList<>();

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private CorrectAnswerModel correctAnswer;

    public void addAnswerOption(AnswerOptionModel answerOptionModel) {
        answerOptions.add(answerOptionModel);
        answerOptionModel.setQuestion(this);
    }

    public void removeAnswerOption(AnswerOptionModel answerOptionModel) {
        answerOptions.remove(answerOptionModel);
        answerOptionModel.setQuestion(null);
    }

    public void addParticipantAnswer(ParticipantAnswerModel participantAnswerModel) {
        participantAnswers.add(participantAnswerModel);
        participantAnswerModel.setQuestion(this);
    }

    public void removeParticipantAnswer(ParticipantAnswerModel participantAnswerModel) {
        participantAnswers.remove(participantAnswerModel);
        participantAnswerModel.setQuestion(null);
    }

    public void setCorrectAnswer(CorrectAnswerModel correctAnswerModel) {
        correctAnswer = correctAnswerModel;
        correctAnswerModel.setQuestion(this);
    }

    public void removeCorrectAnswer() {
        correctAnswer = null;
    }

}
