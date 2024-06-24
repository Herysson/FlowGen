package com.example.generetediagrams.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "tbl_text_image")
public class Diagram {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    private String text;
    @Lob
    private String code;
    @Lob
    @Column(name = "image_data", nullable = false, columnDefinition = "BLOB")
    private byte[] imageData;
}
