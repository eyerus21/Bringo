package cs590.awesomedelivery.searchsvc.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {
    private String id;
    private String city;
    private String state;
    private String zip;
    private String street;
}
