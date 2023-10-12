package com.dev.moments.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> tags = new ArrayList<String>();


}
