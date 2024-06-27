package com.example.generetediagrams.repository;

import com.example.generetediagrams.model.Diagram;
import org.springframework.data.repository.CrudRepository;

public interface DiagramRepository extends CrudRepository<Diagram,Integer> {
}
