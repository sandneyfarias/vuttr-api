package br.com.startideia.vuttr.repository;

import br.com.startideia.vuttr.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findByTitleOrLinkAndIdNot(String title, String link, Long id);

    List<Tool> findAllByOrderByTitle();

    @Query("SELECT t FROM Tool t JOIN t.tags ta WHERE ta.name = :tag ORDER BY t.title")
    List<Tool> finAlldByTag(@Param("tag") String tag);

}
