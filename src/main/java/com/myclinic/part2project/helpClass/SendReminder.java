package com.myclinic.part2project.helpClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.service.RecordService;
import com.myclinic.part2project.service.RecordServiceImpl;

public class SendReminder {
	@Autowired
	RecordService recordService;
	 // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACc3f52405dbc1fc693e99a6accf393f6e";
    public static final String AUTH_TOKEN = "fb533170325b5f23a5bb591e15990b8c";
    public static final String TWILIO_NUMBER = "+18503042900";
	//@Scheduled(fixedRate=5000)
    @Scheduled(cron = "0 0 6 * * ?")
	public void sendReminder(){
		System.out.println(">>>>>>>>>>>>>>>>>SEND REMINDER");
		this.reminder();
	}
	public void reminder(){
		for(Record record: recordService.viewAllPatientRecords()){
			for(Prescription pre: record.getPrescription()){
				LocalDate date= record.getVisitDate().plusDays(pre.getDrug().getDuration());
				if(date.isAfter(LocalDate.now())){
					sendSMS(record.getPatient().getMobile());
					return;
				}
			}
		}
	}
public void sendSMS(String phoneNumber) {
    try {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", "Dear our customer, We are reminding you to take your medicals. Thanks for supporting us."));
        params.add(new BasicNameValuePair("To", phoneNumber)); //Add real number here
        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    } 
    catch (TwilioRestException e) {
        System.out.println(e.getErrorMessage());
    }
}


}
