package com.binar.Batch7.Service;

import java.util.Map;

public interface AbstractFactoryService {
    Map save(Object request);

    Map update(Object request);

    Map deleted(Object request);

    Map getData();
}

