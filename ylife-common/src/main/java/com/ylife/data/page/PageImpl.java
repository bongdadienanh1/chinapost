package com.ylife.data.page;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by InThEnd on 2016/3/24.
 * <p/>
 * 分页实现。
 */
public class PageImpl<T> implements Page<T> {

    @Expose
    private int number;

    @Expose
    private int size;

    @Expose
    private int totalElements;

    @Expose
    private int totalPages;

    @Expose
    private List<T> content;

    private transient Pageable pageable;

    public PageImpl(Pageable pageable, int totalElements, List<T> content) {
        number = pageable == null ? -1 : pageable.getPageNumber();
        size = pageable == null ? -1 : pageable.getPageSize();
        this.totalElements = totalElements;
        this.totalPages = pageable == null ? 1 : (totalElements + size - 1) / size;
        this.content = content;
        this.pageable = pageable;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int getTotalElements() {
        return totalElements;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return content != null && content.size() > 0;
    }

    @Override
    public Pageable nextPageable() {
        return pageable.next();
    }

    @Override
    public Pageable previousPageable() {
        return pageable.previousOrFirst();
    }

    @Override
    public boolean isLastPageNumber() {
        return pageable.getPageNumber()==totalElements/pageable.getPageSize()+1;
    }
}
