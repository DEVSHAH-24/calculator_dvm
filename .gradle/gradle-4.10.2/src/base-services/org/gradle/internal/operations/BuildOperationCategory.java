/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.internal.operations;

/**
 * Classifies a build operation such that executors and event listeners can
 * react differently depending on this type.
 *
 * @since 4.0
 */
public enum BuildOperationCategory {
    CONFIGURE_PROJECT(true, false),
    TASK(true, true),
    CONFIGURE_ROOT_BUILD(false, false),
    CONFIGURE_BUILD(false, false),
    RUN_TASKS_ROOT_BUILD(false, false),
    RUN_TASKS(false, false),
    UNCATEGORIZED(false, false),
    TRANSFORM(true, true);

    private final boolean grouped;
    private final boolean topLevelWorkItem;

    BuildOperationCategory(boolean grouped, boolean topLevelWorkItem) {
        this.grouped = grouped;
        this.topLevelWorkItem = topLevelWorkItem;
    }

    public boolean isGrouped() {
        return grouped;
    }

    public boolean isTopLevelWorkItem() {
        return topLevelWorkItem;
    }
}
