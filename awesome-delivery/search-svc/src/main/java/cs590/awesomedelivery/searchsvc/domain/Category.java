package cs590.awesomedelivery.searchsvc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {
    @JsonProperty("breakfast")
    BREAKFAST,
    @JsonProperty("lunch")
    LUNCH,
    @JsonProperty("dinner")
    DINNER
}
