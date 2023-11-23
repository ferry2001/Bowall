package com.ferry.bowall.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Component;

@Component
public class PinYin {
    public String getPinyin(String word) {
        StringBuilder pinyin = new StringBuilder();
        for (char ch : word.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch);
            if (pinyinArray != null && pinyinArray.length > 0) {
                pinyin.append(pinyinArray[0]);
            }
        }
        return pinyin.toString().toLowerCase();
    }
}
