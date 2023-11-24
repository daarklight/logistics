package com.tsystems.logistics.logistics_vp.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "origin_index",
    "destination_index",
    "distance_meters",
    "duration"
})
@Generated("jsonschema2pojo")
public class DistanceMatrixPojo {

    @JsonProperty("origin_index")
    private String originIndex;
    @JsonProperty("destination_index")
    private String destinationIndex;
    @JsonProperty("distance_meters")
    private String distanceMeters;
    @JsonProperty("duration")
    private DurationPojo duration;

    @JsonProperty("origin_index")
    public String getOriginIndex() {
        return originIndex;
    }

    @JsonProperty("origin_index")
    public void setOriginIndex(String originIndex) {
        this.originIndex = originIndex;
    }

    @JsonProperty("destination_index")
    public String getDestinationIndex() {
        return destinationIndex;
    }

    @JsonProperty("destination_index")
    public void setDestinationIndex(String destinationIndex) {
        this.destinationIndex = destinationIndex;
    }

    @JsonProperty("distance_meters")
    public String getDistanceMeters() {
        return distanceMeters;
    }

    @JsonProperty("distance_meters")
    public void setDistanceMeters(String distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    @JsonProperty("duration")
    public DurationPojo getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(DurationPojo duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DistanceMatrixPojo{" +
            "originIndex='" + originIndex + '\'' +
            ", destinationIndex='" + destinationIndex + '\'' +
            ", distanceMeters='" + distanceMeters + '\'' +
            ", duration=" + duration.getSeconds() +
            '}';
    }
}