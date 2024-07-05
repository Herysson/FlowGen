package com.example.generetediagrams.services;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;

public class GeminiAI {
    private String projectId = "steel-watch-426711-q1";
    private String location = "southamerica-east1";
    private String modelName = "gemini-1.5-pro-001";


    public String inputPrompt(String textPrompt) throws IOException {
        try (VertexAI vertexAI = new VertexAI(this.projectId, this.location)) {
            GenerativeModel model = new GenerativeModel(this.modelName, vertexAI);

            GenerateContentResponse response = model.generateContent(textPrompt);
            return ResponseHandler.getText(response);
        }
    }
}
