package io.mmondino.infrastructure.data.loader.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public abstract class CsvToMapDataLoader<S, K, V, T extends Map<K, V>> {

    public final T read(URL url) throws IOException {
        return this.read(url.getPath());
    }

    public final T read(String path) throws IOException {
        return this.internalRead(path);
    }

    public final T internalRead(String path) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            return bufferedReader.lines()
                    .map(l -> this.mapFieldsToItem(Arrays.asList(l.split(","))))
                    .collect(this.getCollector());
        }
    }

    protected abstract S mapFieldsToItem(List<String> fields);
    protected abstract Collector<S, ?,T> getCollector();
}
