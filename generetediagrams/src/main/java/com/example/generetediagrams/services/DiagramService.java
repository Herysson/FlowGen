package com.example.generetediagrams.services;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiagramService {
    private GeminiAI geminiAI = new GeminiAI();

    public String getCode(String textPrompt) throws IOException {
        textPrompt = textPrompt + "\n Gere um diagrama de atividade para a api PlantUML";
        String output = this.geminiAI.inputPrompt(textPrompt);

        String patternString = "```plantuml(.*?)```";
        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(output);
        String plantUmlBlock = new String();
        while (matcher.find()) {
            plantUmlBlock = matcher.group(1).trim();
        }
        return plantUmlBlock;
    }
}
