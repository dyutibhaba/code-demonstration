package com.protostellar.zugplaner.trackandpredict.infra.api.rest.featuremngt;

import com.protostellar.zugplaner.trackandpredict.infra.spi.featureflags.FeatureFlagsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/features")
public class FeatureFlagsController {

    private FeatureFlagsService featureFlagsService;

    public FeatureFlagsController(FeatureFlagsService featureFlagsService) {
        this.featureFlagsService = featureFlagsService;
    }

    @GetMapping("/{featureKey}/isEnabled")
    public ResponseEntity<Boolean> isEnabled(@PathVariable String featureKey) {
        return ResponseEntity.ok(featureFlagsService.isEnabled(featureKey));
    }
}
