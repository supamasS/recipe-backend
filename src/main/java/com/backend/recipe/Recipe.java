package com.backend.recipe;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
}
