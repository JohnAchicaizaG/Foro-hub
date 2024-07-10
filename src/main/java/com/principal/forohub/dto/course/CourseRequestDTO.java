package com.principal.forohub.dto.course;

import com.principal.forohub.entities.CategoriaEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    private String nombre;
    private CategoriaEntitys categoria;

}
