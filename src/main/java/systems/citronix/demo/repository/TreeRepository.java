package systems.citronix.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import systems.citronix.demo.model.Tree;

public interface TreeRepository extends JpaRepository<Tree, Long> {
    long countByFieldId(Long fieldId);
    List<Tree> findByFieldId(Long fieldId);
}