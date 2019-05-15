package com.test.awsLambdaTest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

	
	@Bean
	public java.util.List<org.springframework.cloud.aws.cache.CacheFactory> getCacheFactory() {
		return new ArrayList<>();
	}

	@Bean(name = "amazonS3Client")
	public AmazonS3 amazonS3Client(@Value("${cloud.aws.region.static}") String awsRegion) {
		return AmazonS3ClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
				.withRegion(awsRegion).build();

	}
	
	/*@Bean(name = "amazonS3Client")
	public AmazonS3 amazonS3Client(@Value("${cloud.aws.region.static}") String awsRegion) {
		return AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

	}*/
	
	/*@Bean
	public AmazonSQSAsync amazonSQS(@Value("${aws.region}") String awsRegion) {
		AmazonSQSAsync theAsyncSqs = null;
		try {
			theAsyncSqs = AmazonSQSAsyncClientBuilder.standard().withRegion(awsRegion)
					.withCredentials(new DefaultAWSCredentialsProviderChain()).build();
		} catch (Exception ex) {
		}
		return theAsyncSqs;
	}

	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
		SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
		factory.setAmazonSqs(amazonSqs);
		factory.setMaxNumberOfMessages(5);
		return factory;
	}

	@Bean
	public QueueMessageHandler queueMessageHandler(AmazonSQSAsync amazonSQSAsync) {
		QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
		factory.setAmazonSqs(amazonSQSAsync);
		return factory.createQueueMessageHandler();
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer(
			SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory,
			QueueMessageHandler queueMessageHandler) {
		SimpleMessageListenerContainer container = simpleMessageListenerContainerFactory
				.createSimpleMessageListenerContainer();
		container.setMessageHandler(queueMessageHandler);
		container.setWaitTimeOut(20);
		return container;
	}*/

}
