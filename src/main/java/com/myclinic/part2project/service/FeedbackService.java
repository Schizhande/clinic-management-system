package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Feedback;

public interface FeedbackService {

	void saveFeedback(Feedback feedback);

	void deleteFeedback(int feedbackId);

	List<Feedback> getFeeddbacks();

}
