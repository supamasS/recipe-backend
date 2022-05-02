package com.backend.recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JsonData {
    private String name;
    private List<Recipe> recipes;
}
