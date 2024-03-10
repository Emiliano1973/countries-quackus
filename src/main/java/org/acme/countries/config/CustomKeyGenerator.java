package org.acme.countries.config;


import io.quarkus.cache.CacheKeyGenerator;
import jakarta.enterprise.context.ApplicationScoped;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class CustomKeyGenerator implements CacheKeyGenerator {
    private static final String DELIMITER = "_";

    @Override
    public Object generate(Method method, Object... params) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getDeclaringClass().getName()).append(DELIMITER)
                .append(method.getName());
        if (Objects.isNull(params) || params.length == 0) {
            return stringBuilder.toString();
        }
        stringBuilder.append(DELIMITER)
                .append(Stream.of(params).filter(Objects::nonNull)
                        .map(Object::toString).collect(Collectors.joining(DELIMITER)));
        return stringBuilder.toString();
    }

}
