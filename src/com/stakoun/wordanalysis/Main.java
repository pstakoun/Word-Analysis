package com.stakoun.wordanalysis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		String[] wordGroups = new String(Files.readAllBytes(Paths.get("res/words.txt")), Charset.defaultCharset()).split("\n");
		String[] wordLabels = new String[wordGroups.length];
		String[][] words = new String[wordGroups.length][];
		for (int i = 0; i < wordGroups.length; i++) {
			String[] temp = wordGroups[i].split(":");
			wordLabels[i] = temp[0];
			words[i] = temp[1].split(",");
			for (int j = 0; j < words[i].length; j++)
				words[i][j] = words[i][j].trim();
		}
		Analysis richardiiiAnalysis = new Analysis("Richard III", getText("res/texts/richardiii.txt"), wordLabels, words, "ACT");
		Analysis fifthbusinessAnalysis = new Analysis("Fifth Business", getText("res/texts/fifthbusiness.txt"), wordLabels, words, "SEPERATOR");
		Files.write(Paths.get("res/results.txt"), Analysis.compare(richardiiiAnalysis, fifthbusinessAnalysis).getBytes());
	}
	
	private static String getText(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset()).replaceAll("[^a-zA-Z0-9\\s]", "");
	}

}
