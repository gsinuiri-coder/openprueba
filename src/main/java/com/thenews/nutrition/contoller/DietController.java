package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Diet;
import com.thenews.nutrition.domain.service.DietService;
import com.thenews.nutrition.resource.DietResource;
import com.thenews.nutrition.resource.SaveDietResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Tag(name="Diets", description = "Diets API")
@RestController
@RequestMapping("/api")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Nutricionists", description = "Get All available Nutricionists", responses = {
            @ApiResponse(
                    description = "All Nutricionists",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/nutricionists/{nutricionistId}/diets")
    public List<DietResource> getAllDietsByNutricionistId(@PathVariable (value = "nutricionistId") Long nutricionistId) {
        return  dietService.getAllDietsByNutricionistId(nutricionistId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/nutricionists/{nutricionistId}/diets/{dietId}")
    public DietResource getDietByIdAndNutricionistId(
            @PathVariable(name = "nutricionistId") Long nutricionistId,
            @PathVariable(name = "dietId") Long dietId) {
        return convertToResource(dietService.getDietByIdAndNutricionistId(nutricionistId, dietId));
    }

    @PostMapping("/nutricionists/{nutricionistId}/diets")
    public DietResource createDiet(
            @PathVariable(value = "nutricionistId") Long nutricionistId,
            @Valid @RequestBody SaveDietResource resource) {
        return convertToResource(dietService.createDiet(nutricionistId,
                convertToEntity(resource)));
    }

    @PutMapping("/nutricionists/{nutricionistId}/diets/{dietId}")
    public DietResource updateDiet(
            @PathVariable (value = "nutricionistId") Long nutricionistId,
            @PathVariable (value = "dietId") Long dietId,
            @Valid @RequestBody SaveDietResource resource) {
        return convertToResource(dietService.updateDiet(nutricionistId, dietId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/nutricionists/{nutricionistId}/diets/{dietId}")
    public ResponseEntity<?> deleteDiet(
            @PathVariable (value = "nutricionistId") Long nutricionistId,
            @PathVariable (value = "dietId") Long dietId) {
        return dietService.deleteDiet(nutricionistId, dietId);
    }

    private Diet convertToEntity(SaveDietResource resource) {
        return mapper.map(resource, Diet.class);
    }
    private DietResource convertToResource(Diet entity) {
        return mapper.map(entity, DietResource.class);
    }
}
