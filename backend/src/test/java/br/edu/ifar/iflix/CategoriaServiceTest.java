package br.edu.ifar.iflix;
import br.edu.ifar.iflix.model.Categoria;
import br.edu.ifar.iflix.repository.CategoriaRepository;
import br.edu.ifar.iflix.service.CategoriaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock CategoriaRepository repo;
    @InjectMocks CategoriaService service;

    @Test
    void buscaPorIdExistenteRetornaCategoria() {
        Categoria c = new Categoria();
        c.setIdCategoria(1);
        c.setNome("filme");
        when(repo.findById(1)).thenReturn(Optional.of(c));

        assertEquals("filme", service.buscaPorId(1).getNome());
    }

    @Test
    void buscaPorIdInexistenteRetorna404() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.buscaPorId(99));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}