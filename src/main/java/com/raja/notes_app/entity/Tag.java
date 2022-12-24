package com.raja.notes_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;

    public Tag(String name) {
        this.tagName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tag) {
            return this.tagName.equals(((Tag) obj).getTagName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}
