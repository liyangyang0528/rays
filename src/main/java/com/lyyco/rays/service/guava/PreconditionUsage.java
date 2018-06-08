package com.lyyco.rays.service.guava;

import static com.google.common.base.Preconditions.*;

/**
 * Author liyangyang
 * 2018/6/8
 */
public class PreconditionUsage {


    public static void main(String... args) {
        boolean isChecked = false;
        /*
        Checks that the boolean is true.
        Use for validating arguments to methods
         */
        checkArgument(isChecked);
        /*
        Checks that the value is not null.
        Returns the value directly, so you can use checkNotNull(value) inline
         */
        boolean notNull = checkNotNull(isChecked);



        /*
        Checks some state of the object,
        not dependent on the method arguments.
        For example, an Iterator might use this to
        check that next has been called before any call to remove.
         */
        checkState(true);
        /*
        Checks that index is a valid element index into a list, string, or array with the specified size.
         */
        checkElementIndex(1, 20);
        /*
        Checks that index is a valid position index into a list, string, or array with the specified size.
         */
        checkPositionIndex(1, 20);
        /*
        Checks that [start, end) is a valid sub range of
        a list, string, or array with the specified size.
        Comes with its own error message.
         */
        checkPositionIndexes(1, 2, 20);
    }
}
