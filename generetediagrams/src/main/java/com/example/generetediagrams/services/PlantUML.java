package com.example.generetediagrams.services;

import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PlantUML {
    public byte[] gerarDiagrama(String codigoGerado) { // Método para salvar no banco os bytes
        byte[] imagemBytes = null;
        try {
            SourceStringReader reader = new SourceStringReader(codigoGerado);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String desc = reader.generateImage(outputStream);
            imagemBytes = outputStream.toByteArray();
            System.out.println("Diagrama gerado com sucesso");
        } catch (IOException e) {
            System.err.println("Erro ao gerar diagrama: " + e.getMessage());
        }
        return imagemBytes;
    }
}


