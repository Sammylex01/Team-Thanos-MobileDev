package com.example.aquafit.ui.settings;

public class RandomQuotes
{
	public static String[] quotes={
		"Did you know your body is made of 70% water?",
		"Scientists recommend you drink at least 2 litres of water a day"
	};
	public static String  a(){
		return quotes[(int)Math.floor(Math.random() * quotes.length)];
		}
}
