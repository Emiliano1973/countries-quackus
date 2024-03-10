package org.acme.countries.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PaginatorDtoBuilder {
    private int currentPage;
    private int pageTotalElements;
    private int totalPages;
    private int pageSize;
    private int totalElements;
    private Collection<? extends Serializable> elements;

    public PaginatorDtoBuilder setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public PaginatorDtoBuilder setPageTotalElements(int pageTotalElements) {
        this.pageTotalElements = pageTotalElements;
        return this;
    }

    public PaginatorDtoBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PaginatorDtoBuilder setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PaginatorDtoBuilder setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PaginatorDtoBuilder setElements(Collection<? extends Serializable> elements) {
        this.elements = new ArrayList<>(elements);
        return this;
    }

    public PaginationDto createPaginatorDto() {
        return new PaginationDto(currentPage, pageTotalElements, totalPages, pageSize, totalElements, elements);
    }
}