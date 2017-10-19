package com.webservice.rest;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;
import com.webservice.dto.SentimentTO;

@Stateless
public class SentimentAnalysisImpl implements SentimentAnalysisIf {

	Response.ResponseBuilder builder = null;
	private static final Logger logger = LoggerFactory.getLogger(SentimentAnalysisImpl.class);
	
	public SentimentTO getSentimentAnalysisScore(String text) {
		try {
			return sentimentAnalysis(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SentimentTO sentimentAnalysis(String text) throws Exception {
		
		SentimentTO sentimentTO = new SentimentTO();
		
		// Instantiates a client
		LanguageServiceClient language = LanguageServiceClient.create();
		Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
		// Detects the sentiment of the text
		Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

		System.out.printf("Text: %s%n", text);
		System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());

		sentimentTO.setMagnitude(sentiment.getMagnitude());
		sentimentTO.setScore(sentiment.getScore());
		sentimentTO.setText(text);
		
		System.out.println(sentiment);
		
		StringBuilder str = new StringBuilder();

		/*
		 * Syntactic Analysis extracts linguistic information, breaking up the
		 * given text into a series of sentences and tokens (generally, word
		 * boundaries), providing further analysis on those tokens.
		 */
		AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
				.setEncodingType(EncodingType.UTF16).build();
		// analyze the syntax in the given text
		AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
		// print the response
		str.append("-------Adding AnalyzeSyntaxResponse Response--------");
		for (Token token : response.getTokensList()) {
			System.out.println(token.toString());
			str.append(token.toString());
//			System.out.printf("\tText: %s\n", token.getText().getContent());
//			System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
//			System.out.printf("Lemma: %s\n", token.getLemma());
//			System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
//			System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
//			System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
//			System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
//			System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
//			System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
//			System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
//			System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
//			System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
//			System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
//			System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
//			System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
//			System.out.println("DependencyEdge");
//			System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
//			System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
//
//			System.out.println("assss: " + token);
		}

		/*
		 * 
		 * Entity Sentiment Analysis combines both entity analysis and sentiment
		 * analysis and attempts to determine the sentiment (positive or
		 * negative) expressed about entities within the text.
		 * 
		 * 
		 */
		AnalyzeEntitySentimentRequest request1 = AnalyzeEntitySentimentRequest.newBuilder().setDocument(doc)
				.setEncodingType(EncodingType.UTF16).build();
		// detect entity sentiments in the given string
		AnalyzeEntitySentimentResponse response1 = language.analyzeEntitySentiment(request1);
		// Print the response
		System.out.printf("------AnalyzeEntitySentimentResponse-------");
		str.append("------AnalyzeEntitySentimentResponse-------");
		for (Entity entity : response1.getEntitiesList()) {
//			System.out.printf("Entity: %s\n", entity.getName());
//			System.out.printf("Salience: %.3f\n", entity.getSalience());
//			System.out.printf("Sentiment : %s\n", entity.getSentiment());
			System.out.println(entity.toString());
			str.append(entity.toString());
			for (EntityMention mention : entity.getMentionsList()) {
//				System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
//				System.out.printf("Content: %s\n", mention.getText().getContent());
//				System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
//				System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
//				System.out.printf("Type: %s\n\n", mention.getType());
//				System.out.println("assss: " + mention);
				System.out.println(mention.toString());
				str.append(mention.toString());
			}
		}
		
		sentimentTO.setOtherDetails(str.toString());
		
		return sentimentTO;

	}
	
}
