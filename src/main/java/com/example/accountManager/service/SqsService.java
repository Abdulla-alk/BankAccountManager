package com.example.accountManager.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SqsService {

    private final AmazonSQS amazonSQS;
    private final String queueUrl;

    @Autowired
    public SqsService(AmazonSQS amazonSQS, @Value("${aws.sqs.queueUrl}") String queueUrl) {
        this.amazonSQS = amazonSQS;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String message) {
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        amazonSQS.sendMessage(sendMsgRequest);
    }
}