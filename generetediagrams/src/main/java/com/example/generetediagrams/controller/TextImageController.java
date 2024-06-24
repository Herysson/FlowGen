package com.example.generetediagrams.controller;

import com.example.generetediagrams.model.Text_Image;
import com.example.generetediagrams.repository.TextImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/text")
public class TextImageController {
    @Autowired
    private TextImageRepository textImageRepository;

    @GetMapping("/form")
    public String showForm(Model model){
        //mandar um objeto do tipo textImage para a pagina de castro
        model.addAttribute("textImage",new Text_Image());
        //redireciona para pagina form.html
        return "formulario";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTextAndImage(@ModelAttribute Text_Image textImage) throws IOException {


        //textImageRepository.save(textImage);
        System.out.println(textImage.toString());
        return ResponseEntity.ok("imagem sera gerada");
    }

    @GetMapping("/list")
    public String listAllTextImages(Model model) {
        // obtem todos os registros do repositório
        Iterable<Text_Image> textImages = textImageRepository.findAll();
        // adiciona os registros ao modelo
        model.addAttribute("textImages", textImages);
        // redireciona para a página list.html
        return "list";
    }

    //exibir detalhes de um registro pelo id
    @GetMapping("/view/{id}")
    public String viewTextImage(@PathVariable("id") Integer id, Model model) {
        Text_Image textImage = textImageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("textImage", textImage);
        // Adiciona a URL da imagem ao modelo
        String imageUrl = "/text/image/" + id;
        model.addAttribute("imageUrl", imageUrl);
        return "view";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Busca o registro pelo ID
        Text_Image textImage = textImageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        // Adiciona o registro ao modelo
        model.addAttribute("textImage", textImage);
        // Redireciona para a página edit.html
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTextImage(@PathVariable("id") Integer id, @ModelAttribute Text_Image textImage, @RequestParam("file") MultipartFile file) throws IOException {
        // Verifica se o arquivo não está vazio
        if (!file.isEmpty()) {
            // Converte em array de bytes
            textImage.setImageData(file.getBytes());
        }
        // Atualiza o registro no repositório
        textImageRepository.save(textImage);
        // Redireciona para a lista de registros
        return "redirect:/text/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTextImage(@PathVariable("id") Integer id) {
        // Busca o registro pelo ID
        Text_Image textImage = textImageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        // Deleta o registro do repositório
        textImageRepository.delete(textImage);
        // Redireciona para a lista de registros
        return "redirect:/text/list";
    }

    @GetMapping("/")
    public String home() {
        return "index"; // Redireciona para a página index.html
    }


}
