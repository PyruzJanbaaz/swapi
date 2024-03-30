package com.pyruz.dzm.swapi.model.dto.people;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTOs {
    private List<PeopleDTO> results;
    private int count;
    private String next;
    private String previous;
}
