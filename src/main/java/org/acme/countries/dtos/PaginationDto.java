package org.acme.countries.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public record PaginationDto(int currentPage,int pageTotalElements, int totalPages, int pageSize, int totalElements, Collection<? extends Serializable> elements)  implements  Serializable{

    public PaginationDto(int currentPage, int pageTotalElements, int totalPages, int pageSize, int totalElements, Collection<? extends Serializable> elements) {
        this.currentPage = currentPage;
        this.pageTotalElements = pageTotalElements;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.elements = new ArrayList<>(elements);
    }

    public Collection<? extends Serializable> elements() {
        return new ArrayList<>(elements);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("PaginationDto{").append("currentPage=").append(currentPage)
                .append(", pageTotalElements=").append(pageTotalElements)
                .append(", totalPages=").append(totalPages)
                .append(", pageSize=").append(pageSize)
                .append(", totalElements=").append(totalElements)
                .append(", elements=[")
                .append(elements.stream().map(Object::toString).collect(Collectors.joining(", ")))
                .append("]")
                .append('}').toString();
    }
}
