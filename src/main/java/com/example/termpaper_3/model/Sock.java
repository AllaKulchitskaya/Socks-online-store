package com.example.termpaper_3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {
    private Color color;
    private Size size;
    private int cottonPart;
}
