package com.arash.edu.bujournal.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.AutoCloseInputStream;

import java.util.Arrays;
import java.util.concurrent.Callable;

import static java.nio.charset.Charset.defaultCharset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final ClassLoader CLASS_LOADER = JsonUtil.class.getClassLoader();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        return call(() -> MAPPER.writeValueAsString(object));
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return call(() -> MAPPER.readValue(json, clazz));
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        return call(() -> MAPPER.readValue(json, typeReference));
    }

    public static String loadFromFile(String resource) {
        return call(() -> IOUtils.toString(new AutoCloseInputStream(CLASS_LOADER.getResourceAsStream(resource)), defaultCharset()));
    }

    public static String loadFromFile(String resource, Object... args) {
        return String.format(loadFromFile(resource), Arrays.stream(args).map(JsonUtil::toJson).toArray());
    }

    public static <T> T loadFromJsonFile(String resource, Class<T> clazz, Object... args) {
        return fromJson(loadFromFile(resource, args), clazz);
    }

    @SneakyThrows
    private static <T> T call(Callable<T> callable) {
        return callable.call();
    }

}
