package com.protostellar.zugplaner.utils;

import com.protostellar.zugplaner.common.utils.PicoContainer;
import org.picocontainer.MutablePicoContainer;

import java.util.Objects;

public class PicoFactoryTestConfiguration {
  private final MutablePicoContainer pico;

  public PicoFactoryTestConfiguration() {
    pico = PicoContainer.get();
  }

  public <T> T getComponentInstance(Class<T> wantedClass) {
    T componentInstance = pico.getComponent(wantedClass);
    Objects.requireNonNull(componentInstance, wantedClass.getName() + " not found in Pico container");
    return componentInstance;
  }

}
