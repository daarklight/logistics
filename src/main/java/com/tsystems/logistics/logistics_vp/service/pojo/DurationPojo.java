package com.tsystems.logistics.logistics_vp.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seconds"
})
@Generated("jsonschema2pojo")
public class DurationPojo {

    @JsonProperty("seconds")
    private String seconds;

    @JsonProperty("seconds")
    public String getSeconds() {
        return seconds;
    }

    @JsonProperty("seconds")
    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }
}