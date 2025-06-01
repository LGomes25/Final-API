package org.serratec.backend.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //ignora outras partes do json
public class OpenLibraryResponseDTO {

    private String title;

    @JsonProperty("number_of_pages")
    private String numberOfPages;

    @JsonProperty("publish_date")
    private String publishDate;

    private List<String> publishers;

    private List<Map<String, String>> authors;

    private Object description;
}
