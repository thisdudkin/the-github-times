package org.earlspilner.newspaper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Table(name = "Tags")
@AllArgsConstructor
public class Tag extends NamedEntity {

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

}
