package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Feedback;

public interface FeedbackDAO {

	void saveFeedback(Feedback feedback);

	void deleteFeedback(int feedbackId);

	List<Feedback> getFeedbacks();

}
