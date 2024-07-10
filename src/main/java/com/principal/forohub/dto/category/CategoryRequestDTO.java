package com.principal.forohub.dto.category;

import com.principal.forohub.entities.CategoriaEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String nombre;
    private CategoriaEntitys categoria;
    
}
