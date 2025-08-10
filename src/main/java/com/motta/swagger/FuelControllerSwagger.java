package com.motta.swagger;

import com.motta.dto.ErrorResponse;
import com.motta.dto.fuel.FuelRequest;
import com.motta.dto.fuel.FuelResponse;
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

@Tag(name = "FuelController")
public interface FuelControllerSwagger {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Fuel created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuelResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., validation errors)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class, example = "{ \"error\": \"Invalid input data\" }")))
            }
    )
    @Operation(
            summary = "Create a new fuel",
            description = "Creates a new fuel with the provided details and returns the created fuel information."
    )
    ResponseEntity<FuelResponse> createFuel(@Validated @RequestBody FuelRequest fuelRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of fuels returned successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuelResponse.class, examples = "[{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}]")))
            }
    )
    @Operation(
            summary = "Get all fuels",
            description = "Returns a list of all registered fuels."
    )
    ResponseEntity<List<FuelResponse>> getAllFuels();

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Fuel updated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FuelResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Fuel not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class, example = "{ \"error\": \"Fuel not found with id: fuelId\" }"))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., validation errors)",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class, example = "{ \"error\": \"Invalid input data\" }")))
            }
    )
    @Operation(
            summary = "Update a fuel",
            description = "Updates the details of an existing fuel by its ID."
    )
    ResponseEntity<FuelResponse> updateFuel(@Validated @RequestBody FuelRequest fuelRequest, @PathVariable String fuelId);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Fuel deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Fuel not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class, example = "{ \"error\": \"Fuel not found with id: fuelId\" }")))
            }
    )
    @Operation(
            summary = "Delete a fuel",
            description = "Deletes an existing fuel by its ID."
    )
    ResponseEntity<Void> deleteFuel(@PathVariable String fuelId);

}