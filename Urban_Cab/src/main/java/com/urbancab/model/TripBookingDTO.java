package com.urbancab.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class TripBookingDTO {

    private Integer tripBookingId;
    @NotNull(message = "From location should not be null.")
    private String fromLocation;

    @NotNull(message = "To location should not be null.")
    private String toLocation;

    @NotNull(message = "From date&time should not be null.")
    private LocalDateTime fromDateTime;

    @NotNull(message = "To date&time should not be null.")
    private LocalDateTime toDateTime;

    @NotNull(message = "Distance should not be null.")
    @Min(value = 50, message = "Min distance should be 50")
    private Float distanceInKm;
}
