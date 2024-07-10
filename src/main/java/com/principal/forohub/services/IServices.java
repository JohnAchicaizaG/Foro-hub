package com.principal.forohub.services;

import java.util.List;
//request - response
public interface IServices<P, R> {

    

    List<R> listarTopic();

    R encontrarCategoriaTopic(Long id);

    R guardarCategoriaTopic(P nuevoRegistro);

    R actualizarTopico(Long id, P nuevosDatos);

    void eliminarCategoriaTopic(Long id);

}
