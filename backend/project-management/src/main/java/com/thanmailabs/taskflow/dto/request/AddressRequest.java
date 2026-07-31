package com.thanmailabs.taskflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequest {
    @NotBlank
    @Size(max = 40)
    private String street;
    @NotNull
    private Integer laneNumber;
    @NotBlank
    @Size(max = 40)
    private String city;
    @NotBlank
    @Size(max = 40)
    private String state;
    @NotBlank
    @Size(max = 40)
    private String country;
}
