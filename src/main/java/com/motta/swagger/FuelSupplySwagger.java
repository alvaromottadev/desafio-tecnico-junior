package com.motta.swagger;

import com.motta.dto.ErrorResponse;
import com.motta.dto.fuelsupply.FuelSupplyRequest;
import com.motta.dto.fuelsupply.FuelSupplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "FuelSupplyController")
public interface FuelSupplySwagger {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Fuel supply created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = FuelSupplyResponse.class,
                                    example = "{\"id\":\"b7e6a1c2-3d4f-4e5a-8b2c-1d2e3f4a5b6c\",\"date\":\"2024-06-01T10:00:00\",\"liters\":10,\"totalPrice\":54.90,\"fuelPump\":{\"id\":\"c1d2e3f4-5a6b-7c8d-9e0f-1a2b3c4d5e6f\",\"name\":\"PUMP-01\",\"fuel\":{\"id\":\"a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}}}"
                            ))),
                    @ApiResponse(responseCode = "400", description = "Bad request (e.g., validation errors)",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = ErrorResponse.class,
                                    example = "{ \"error\": \"Invalid input data\" }"
                            )))
            }
    )
    @Operation(
            summary = "Create a new fuel supply",
            description = "Creates a new fuel supply with the provided details and returns the created fuel supply information."
    )
    ResponseEntity<FuelSupplyResponse> createFuelSupply(@Validated @RequestBody FuelSupplyRequest fuelSupplyRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of fuel supplies returned successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(
                                    implementation = FuelSupplyResponse.class,
                                    example = "[{\"id\":\"b7e6a1c2-3d4f-4e5a-8b2c-1d2e3f4a5b6c\",\"date\":\"2024-06-01T10:00:00\",\"liters\":10,\"totalPrice\":54.90,\"fuelPump\":{\"id\":\"c1d2e3f4-5a6b-7c8d-9e0f-1a2b3c4d5e6f\",\"name\":\"PUMP-01\",\"fuel\":{\"id\":\"a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d\",\"name\":\"Gasoline\",\"pricePerLiter\":5.49}}}]"
                            )))
            }
    )
    @Operation(
            summary = "Get all fuel supplies",
            description = "Returns a list of all registered fuel supplies."
    )
    ResponseEntity<List<FuelSupplyResponse>> getAllFuelSupply();

}