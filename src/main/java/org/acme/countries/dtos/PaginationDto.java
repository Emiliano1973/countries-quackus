package org.acme.countries.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public record PaginationDto(int currentPage, int pageTotalElements, int totalPages, int pageSize, int totalElements,
                            Collection<? extends Serializable> elements) implements Serializable {
    public PaginationDto(int currentPage, int pageTotalElements, int totalPages, int pageSize, int totalElements, Collection<? extends Serializable> elements) {
        this.currentPage = currentPage;
        this.pageTotalElements = pageTotalElements;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.elements = (elements == null) ? Collections.emptyList() : new ArrayList<>(elements);
    }

    public Collection<? extends Serializable> elements() {
        return new ArrayList<>(elements);
    }

    @Override
    public String toString() {
        return "PaginationDto{" + "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", elements=[" +
                elements.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                "]" +
                '}';
    }
}
