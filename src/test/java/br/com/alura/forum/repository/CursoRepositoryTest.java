package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void buscandoCursoExistente() {
        String nomeCurso = "HTML 5";
        Curso novo = new Curso();
        novo.setNome(nomeCurso);
        novo.setCategoria("Programacao");
        entityManager.persist(novo);

        Curso curso = cursoRepository.findByNome(nomeCurso);
        assertNotNull(curso);
        assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void buscandoCursoInexistente() {
        String nomeCurso = "JPA";
        Curso curso = cursoRepository.findByNome(nomeCurso);
        assertNull(curso);
    }
}
