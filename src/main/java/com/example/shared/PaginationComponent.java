package com.example.shared;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.nio.ByteBuffer;
import java.util.Base64;

public class PaginationComponent {

    public static Pageable buildPageable(int size, String pagingState) {
        Pageable pageable = PageRequest.of(0, size);
        if (pagingState != null && !pagingState.isBlank()) {
            ByteBuffer buffer = decodePagingState(pagingState);
            pageable = CassandraPageRequest.of(pageable, buffer);
        }
        return pageable;
    }

    public static ByteBuffer decodePagingState(String pagingState) {
        byte[] bytes = Base64.getDecoder().decode(pagingState);
        return ByteBuffer.wrap(bytes);
    }

    public static  String extractPagingState(Slice<?> customers) {
        if (customers.hasNext()) {
            Pageable next = customers.nextPageable();
            if (next instanceof CassandraPageRequest cassandraPageRequest) {
                ByteBuffer buffer = cassandraPageRequest.getPagingState();
                if (buffer != null) {
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.duplicate().get(bytes);
                    return Base64.getEncoder().encodeToString(bytes);
                }
            }
        }
        return null;
    }
}
