package com.thenews.nutrition.contoller;


import com.thenews.nutrition.domain.model.Advice;
import com.thenews.nutrition.domain.model.Diet;
import com.thenews.nutrition.domain.service.AdviceService;
import com.thenews.nutrition.domain.service.DietService;
import com.thenews.nutrition.resource.AdviceResource;
import com.thenews.nutrition.resource.DietResource;
import com.thenews.nutrition.resource.SaveAdviceResource;
import com.thenews.nutrition.resource.SaveDietResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Tag(name = "AdviceDiets", description = "AdviceDiets API")
@RestController
@RequestMapping("/api")
public class AdviceDietsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private DietService dietService;

    @PostMapping("/advices/{adviceId}/diets/{dietId}")
    public AdviceResource assignAdviceDiet(
            @PathVariable(name = "adviceId") Long adviceId,
            @PathVariable(name = "dietId") Long dietId) {
        return convertToResource(adviceService.assignAdviceDiet(adviceId, dietId));
    }

    @DeleteMapping("/advices/{adviceId}/diets/{dietId}")
    public AdviceResource unassignAdviceDiet(
            @PathVariable(name = "adviceId") Long adviceId,
            @PathVariable(name = "dietId") Long dietId) {
        return convertToResource(adviceService.unassignAdviceDiet(adviceId, dietId));
    }

    @GetMapping("/advices/{adviceId}/diets")
    public List<DietResource> getAllDietsByAdviceId(@PathVariable(name = "adviceId") Long adviceId) {
        return dietService.getAllDietsByAdviceId(adviceId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    private Diet convertToEntity(SaveDietResource resource) {
        return mapper.map(resource, Diet.class);
    }
    private DietResource convertToResource(Diet entity) {
        return mapper.map(entity, DietResource.class);
    }

    private Advice convertToEntity(SaveAdviceResource resource) {
        return mapper.map(resource, Advice.class);
    }
    private AdviceResource convertToResource(Advice entity) {
        return mapper.map(entity, AdviceResource.class);
    }
}
