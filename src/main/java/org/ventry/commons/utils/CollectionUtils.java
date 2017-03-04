package org.ventry.commons.utils;

import java.util.Collection;

/**
 * file: org.ventry.commons.utils.CollectionUtils
 * author: jojo
 * create: 2017/3/4 16:46
 * description:
 */

public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }
}
