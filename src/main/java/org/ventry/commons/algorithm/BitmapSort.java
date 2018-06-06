package org.ventry.commons.algorithm;

/**
 * Created by ventry on 16/7/31.
 */
public class BitmapSort {

    private final int repeat;// Upper limit of the repeat times of a number = power(2, repeat) - 1
    private final int shift;
    private final int mod;
    private final int mask;

    public BitmapSort(int repeat) {
        // Assert.isTrue(repeat in [0, 32]);
        this.repeat = repeat;
        this.shift = 5;
        this.mod = 0x1f;
        this.mask = (int)(Math.pow(2, repeat) - 1);
    }

    /**
     * advanced counting sort when leaks of memory
     * @param source
     * @param bound
     * @return
     */
    public int[] order(int[] source, int bound) {
        int bitmapSize = (bound * repeat) >> shift;
        int[] bitmap = new int[bitmapSize + 1];

        int index, offset, temp;
        for(int i = 0; i < source.length; i++) {
            index = (source[i] * repeat) >> shift;// source[i] / 32
            offset = (source[i] * repeat) & mod;// source[i] mod 32
            temp = (bitmap[index] >> offset) & mask;

            bitmap[index] &= ~(mask << offset);// clear source[i] in the bitmap
            bitmap[index] += ((temp + 1) << offset);
        }

        for(int i = 0, j = 0; i < bound && j < source.length; i++) {
            index = (i * repeat) >> shift;
            offset = (i * repeat) & mod;
            temp = (bitmap[index] >> offset) & mask;

            while(temp-- > 0 && j < source.length)
                source[j++] = i;
        }

        return source;
    }
}
