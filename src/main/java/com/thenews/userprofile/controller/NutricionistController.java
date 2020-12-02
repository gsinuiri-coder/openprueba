package com.thenews.userprofile.controller;


import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.service.NutricionistService;
import com.thenews.userprofile.resource.NutricionistResource;
import com.thenews.userprofile.resource.SaveNutricionistResource;
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
@Tag(name="Nutricionists", description = "Nutricionist API")
@RestController
@RequestMapping("/api")
public class NutricionistController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NutricionistService nutricionistService;

    @Operation(summary = "Get All Nutricionists", description = "Get All available Nutricionists", responses = {
            @ApiResponse(
                    description = "All Nutricionists",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/nutricionists")
    public List<NutricionistResource> getAllNutricionists() {
        return nutricionistService.getAllNutricionists()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/nutricionists/{nutricionistId}")
    public NutricionistResource getNutricionistById(@PathVariable(value = "nutricionistId") Long nutricionistId) {
        return convertToResource(nutricionistService.getNutricionistById(nutricionistId));
    }

    @PostMapping("/nutricionists")
    public NutricionistResource createNutricionist( @Valid @RequestBody SaveNutricionistResource resource) {

        Nutricionist nutricionist = convertToEntity(resource);
        return convertToResource(nutricionistService.createNutricionist(nutricionist));

    }

    @PutMapping("/nutricionists/{nutricionistId}")
    public NutricionistResource updateNutricionist(@PathVariable Long nutricionistId,
                                   @Valid @RequestBody SaveNutricionistResource resource) {
        Nutricionist nutricionist = convertToEntity(resource);
        return convertToResource(
                nutricionistService.updateNutricionist(nutricionistId, nutricionist));
    }

    @DeleteMapping("/nutricionists/{nutricionistId}")
    public ResponseEntity<?> deleteNutricionist(@PathVariable Long nutricionistId) {
        return nutricionistService.deleteNutricionist(nutricionistId);
    }


    private Nutricionist convertToEntity(SaveNutricionistResource resource) {
        return mapper.map(resource, Nutricionist.class);
    }
    private NutricionistResource convertToResource(Nutricionist entity) {
        return mapper.map(entity, NutricionistResource.class);
    }
}
