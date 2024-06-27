package com.example.generetediagrams.controller;

import com.example.generetediagrams.model.Diagram;
import com.example.generetediagrams.repository.DiagramRepository;
import com.example.generetediagrams.services.DiagramService;
import com.example.generetediagrams.services.PlantUML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Base64;

@Controller
public class TextImageController {
    @Autowired
    private DiagramRepository textImageRepository;


    @GetMapping("/form")
    public String showForm(Model model){
        //mandar um objeto do tipo textImage para a pagina de castro
        model.addAttribute("textImage",new Diagram());
        //redireciona para pagina form.html
        return "formulario";
    }

    @PostMapping("/save")
    public String saveTextAndImage(@ModelAttribute Diagram textImage, Model model) throws IOException {
        DiagramService service = new DiagramService();
        textImage.setCode(service.getCode(textImage.getText()));
        textImage.setImageData(service.getImageByte(textImage.getCode()));
        textImageRepository.save(textImage);
        BufferedImage image = service.gerarDiagramaImagem(textImage.getImageData());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        model.addAttribute("image", "data:image/png;base64," + base64Image);

        return "ExemploDiagrama";
    }

}
