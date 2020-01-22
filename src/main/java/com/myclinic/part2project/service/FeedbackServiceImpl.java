package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.FeedbackDAO;
import com.myclinic.part2project.model.Feedback;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackDAO feedbackDao;

	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackDao.saveFeedback(feedback);
	}

	@Override
	public void deleteFeedback(int feedbackId) {
		feedbackDao.deleteFeedback(feedbackId);
	}

	@Override
	public List<Feedback> getFeeddbacks() {
		return feedbackDao.getFeedbacks();
	}

}
