package com.stakoun.wordanalysis;

import java.text.NumberFormat;
import java.util.Locale;

public class Analysis
{
	String title;
	String[] wordLabels;
	String[][] words;
	String[] buckets;
	int[][] wordCounts;
	float[][] wordPercentages;
	int[] totalWordCounts;
	float[] totalWordPercentages;
	int totalWords;
	
	public Analysis(String title, String text, String[] wordLabels, String[][] words)
	{
		this(title, text, wordLabels, words, null);
	}
	
	public Analysis(String title, String text, String[] wordLabels, String[][] words, String sectionSeperator)
	{
		this.title = title;
		this.wordLabels = wordLabels;
		this.words = words;
		totalWords = text.split("\\s+").length;
		buckets = sectionSeperator == null ? new String[] { text } : text.split(sectionSeperator);
		wordCounts = new int[buckets.length][words.length];
		wordPercentages = new float[buckets.length][words.length];
		totalWordCounts = new int[words.length];
		totalWordPercentages = new float[words.length];
		for (int i = 0; i < buckets.length; i++)
			for (String word : buckets[i].split("\\s+"))
				for (int j = 0; j < words.length; j++)
					for (int k = 0; k < words[j].length; k++)
						if (word.equalsIgnoreCase(words[j][k])) {
							wordCounts[i][j]++;
							totalWordCounts[j]++;
						}
		for (int i = 0; i < buckets.length; i++)
			for (int j = 0; j < words.length; j++)
				wordPercentages[i][j] = 100.0f * wordCounts[i][j] / buckets[i].split("\\s+").length;
		for (int i = 0; i < words.length; i++)
			totalWordPercentages[i] = 100.0f * totalWordCounts[i] / totalWords;
	}

	public static String compare(Analysis... analyses)
	{
		String result = "";
		for (int i = 0; i < analyses.length; i++) {
			result += analyses[i].getTitle() + " (" + NumberFormat.getNumberInstance(Locale.US).format(analyses[i].getTotalWords()) + " words total)\n\n";
			result += "Word counts by bucket:\n";
			int[][] wordCounts = analyses[i].getWordCounts();
			String[] wordLabels = analyses[i].getWordLabels();
			float[][] wordPercentages = analyses[i].getWordPercentages();
			for (int j = 0; j < wordCounts.length; j++) {
				for (int k = 0; k < wordCounts[j].length; k++)
					result += wordCounts[j][k] + " " + wordLabels[k]  + " (" + wordPercentages[j][k] + "%)" +  ((k == wordCounts[j].length - 1) ? "" : ", ");
				result += "\n";
			}
			result += "\n";
			result += "Total word counts: ";
			int[] totalWordCounts = analyses[i].getTotalWordCounts();
			float[] totalWordPercentages = analyses[i].getTotalWordPercentages();
			for (int j = 0; j < totalWordCounts.length; j++)
				result += totalWordCounts[j] + " " + wordLabels[j] + " (" + totalWordPercentages[j] + "%)" + ((j == totalWordCounts.length - 1) ? "" : ", ");
			
			result += ((i == analyses.length - 1) ? "" : "\n\n\n");
		}
		return result;
	}

	private String getTitle() {
		return title;
	}

	private String[] getWordLabels() {
		return wordLabels;
	}
	
	private int[][] getWordCounts() {
		return wordCounts;
	}

	private float[][] getWordPercentages() {
		return wordPercentages;
	}

	private int[] getTotalWordCounts() {
		return totalWordCounts;
	}

	private float[] getTotalWordPercentages() {
		return totalWordPercentages;
	}

	private int getTotalWords() {
		return totalWords;
	}

}
