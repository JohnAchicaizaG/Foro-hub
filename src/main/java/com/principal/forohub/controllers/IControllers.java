package com.principal.forohub.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//Intercade controllers todo
public interface IControllers<P, R> {


    ResponseEntity<List<R>> mostrar();

    ResponseEntity<R> buscarporId(@PathVariable Long id);

    ResponseEntity<R> guardar(@RequestBody P nuevoRegistro);

    ResponseEntity<R> actualizarUsuario(@PathVariable Long id, @RequestBody P nuevosDatos);

    ResponseEntity<?> eliminar(@PathVariable Long id);

}
