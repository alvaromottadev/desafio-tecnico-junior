package com.motta.swagger;

import com.motta.dto.ErrorResponse;
import com.motta.dto.fuelpump.FuelPumpRequest;
import com.motta.dto.fuelpump.FuelPumpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "FuelPumpController")
public interface FuelPumpSwagger {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Fuel pump created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = FuelPumpResponse.class,
                                    example = "{\"id\":\"b7e6a1c2-3d4f-4e5a-8b2c-1d2e3f4a5b6c\",\"name\":\"PUMP-01\",\"fuel\":{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}}"
                            ))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., validation errors)",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = ErrorResponse.class,
                                    example = "{ \"error\": \"Invalid input data\" }"
                            )))
            }
    )
    @Operation(
            summary = "Create a new fuel pump",
            description = "Creates a new fuel pump with the provided details and returns the created fuel pump information."
    )
    ResponseEntity<FuelPumpResponse> createFuelPump(@Validated @RequestBody FuelPumpRequest fuelPumpRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of fuel pumps returned successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = FuelPumpResponse.class,
                                    example = "[{\"id\":\"b7e6a1c2-3d4f-4e5a-8b2c-1d2e3f4a5b6c\",\"name\":\"PUMP-01\",\"fuel\":{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}}]"
                            )))
            }
    )
    @Operation(
            summary = "Get all fuel pumps",
            description = "Returns a list of all registered fuel pumps."
    )
    ResponseEntity<List<FuelPumpResponse>> getAllFuelPumps();

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fuel pump updated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = FuelPumpResponse.class,
                                    example = "{\"id\":\"b7e6a1c2-3d4f-4e5a-8b2c-1d2e3f4a5b6c\",\"name\":\"PUMP-01-UPDATED\",\"fuel\":{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}}"
                            ))),
                    @ApiResponse(responseCode = "404", description = "Fuel pump not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = ErrorResponse.class,
                                    example = "{ \"error\": \"Fuel pump not found with id: fuelPumpId\" }"
                            ))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., validation errors)",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = ErrorResponse.class,
                                    example = "{ \"error\": \"Invalid input data\" }"
                            )))
            }
    )
    @Operation(
            summary = "Update a fuel pump",
            description = "Updates the details of an existing fuel pump by its ID."
    )
    ResponseEntity<FuelPumpResponse> updateFuelPump(@Validated @RequestBody FuelPumpRequest fuelPumpRequest, @PathVariable String fuelPumpId);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Fuel pump deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Fuel pump not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = ErrorResponse.class,
                                    example = "{ \"error\": \"Fuel pump not found with id: fuelPumpId\" }"
                            )))
            }
    )
    @Operation(
            summary = "Delete a fuel pump",
            description = "Deletes an existing fuel pump by its ID."
    )
    ResponseEntity<Void> deleteFuelPump(@PathVariable String fuelPumpId);

}