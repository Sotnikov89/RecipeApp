package com.example.recipeapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @EqualsAndHashCode.Exclude
    private Recipe recipe;
    @Lob
    private String recipeNotes;
}
