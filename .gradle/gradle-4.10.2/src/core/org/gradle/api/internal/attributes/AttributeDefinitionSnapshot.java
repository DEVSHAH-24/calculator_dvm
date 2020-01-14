/*
 * Copyright 2018 the original author or authors.
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

package org.gradle.api.internal.attributes;

import org.gradle.api.attributes.Attribute;
import org.gradle.api.internal.changedetection.state.AbstractIsolatableScalarValue;
import org.gradle.api.internal.changedetection.state.ImplementationSnapshot;
import org.gradle.caching.internal.BuildCacheHasher;
import org.gradle.internal.classloader.ClassLoaderHierarchyHasher;

public class AttributeDefinitionSnapshot extends AbstractIsolatableScalarValue<Attribute<?>> {

    private final ClassLoaderHierarchyHasher classLoaderHasher;

    public AttributeDefinitionSnapshot(Attribute<?> value, ClassLoaderHierarchyHasher classLoaderHasher) {
        super(value);
        this.classLoaderHasher = classLoaderHasher;
    }

    @Override
    public void appendToHasher(BuildCacheHasher hasher) {
        hasher.putString(getValue().getName());
        Class<?> type = getValue().getType();
        new ImplementationSnapshot(type.getName(), classLoaderHasher.getClassLoaderHash(type.getClassLoader())).appendToHasher(hasher);
    }
}
