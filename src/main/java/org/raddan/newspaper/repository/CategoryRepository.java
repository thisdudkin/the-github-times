package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {  }
