package com.example.generetediagrams.controller;

import com.example.generetediagrams.model.Diagram;
import com.example.generetediagrams.repository.DiagramRepository;
import com.example.generetediagrams.services.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseEntity<String> saveTextAndImage(@ModelAttribute Diagram textImage) throws IOException {
        var service = new DiagramService();

        textImage.setCode(service.getCode(textImage.getText()));
        // textImageRepository.save(textImage);
//        System.out.println(textImage.toString());
        return ResponseEntity.ok("imagem sera gerada");
    }

}
