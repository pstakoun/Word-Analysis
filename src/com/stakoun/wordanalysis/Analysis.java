package com.stakoun.wordanalysis;

public class Analysis
{
	public Analysis(String text, String[] words)
	{
		this(text, words, null);
	}
	
	public Analysis(String text, String[] words, String sectionSeperator)
	{
		String[] buckets = sectionSeperator == null ? new String[] { text } : text.split(sectionSeperator);
		int[][] wordCounts = new int[buckets.length][words.length];
		for (int i = 0; i < buckets.length; i++)
			for (String word : buckets[i].split("\\s+"))
				for (int j = 0; j < words.length; j++)
					if (word.equalsIgnoreCase(words[j]))
						wordCounts[i][j]++;
	}

}
