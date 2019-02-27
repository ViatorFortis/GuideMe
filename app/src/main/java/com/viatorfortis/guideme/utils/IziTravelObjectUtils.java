package com.viatorfortis.guideme.utils;

import com.viatorfortis.guideme.model.Child;

import java.util.Iterator;
import java.util.List;

public class IziTravelObjectUtils {

    public static final String MTGO_CHILD_STORY_NAVIGATION_TYPE = "story_navigation";

    public static void excludeMtgObjectChildByType(List<Child> childList, String type) {
        Iterator<Child> childIterator = childList.iterator();
        while (childIterator.hasNext()) {
            Child child = childIterator.next();

            if (child.getType().equals(type) ) {
                childIterator.remove();
            }
        }
    }
}
