package com.example.generetediagrams.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiagramService {
    private GeminiAI geminiAI = new GeminiAI();
    private PlantUML plantUML = new PlantUML();

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

    public byte[] getImageByte(String cod){
        return plantUML.gerarDiagrama(cod);
    }

    public BufferedImage gerarDiagramaImagem(byte[] imagemBytes) {
        try {
            // Converter os bytes da imagem para BufferedImage
            ByteArrayInputStream bis = new ByteArrayInputStream(imagemBytes);
            BufferedImage imagem = ImageIO.read(bis);
            bis.close();

            System.out.println("Diagrama gerado com sucesso");

            return imagem;
        } catch (IOException e) {
            System.err.println("Erro ao gerar diagrama: " + e.getMessage());
            return null;
        }
    }
}
