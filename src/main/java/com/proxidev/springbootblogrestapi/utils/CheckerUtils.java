package com.proxidev.springbootblogrestapi.utils;

import static com.proxidev.springbootblogrestapi.utils.AppConstant.MAX_PAGE_SIZE;

public class CheckerUtils {
    public static void validatedPaginationData(int page, int size) {
        if (page < 0)
            throw new RuntimeException("Page number cannot be less than 0");

        if (size < 0)
            throw new RuntimeException("Size number cannot be less than 0");

        if (size > MAX_PAGE_SIZE)
            throw new RuntimeException("Page size must not be gretter than " + MAX_PAGE_SIZE);
    }
}
