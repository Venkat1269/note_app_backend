package com.raja.notes_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String noteText;
    private String imageURL;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ListItems> listItems;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
}
