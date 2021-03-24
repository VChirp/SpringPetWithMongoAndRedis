package com.pet_pr.SpringPet.utils;

public class BinUtils {
    public static final String BIV_REGEX = "\\d{16}";

    public static Boolean checkBinFormat(String bin) {
        return bin.matches(BIV_REGEX);
    }
}
