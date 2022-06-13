package com.protostellar.zugplaner.trackandpredict.infra.spi.featureflags;

import com.microsoft.azure.spring.cloud.feature.manager.FeatureManager;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class FeatureFlagsService {

    private FeatureManager featureManager;

    public FeatureFlagsService(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    public boolean isEnabled(String featureKey) {
        return requireNonNull(featureManager.isEnabledAsync(featureKey).block());
    }
}
