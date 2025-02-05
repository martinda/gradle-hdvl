/*
 * Copyright 2022 the original author or authors.
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

package com.verificationgentleman.gradle.hdvl.internal;

import com.verificationgentleman.gradle.hdvl.SourceSet;
import org.gradle.api.model.ObjectFactory;
import org.gradle.util.GUtil;

import javax.inject.Inject;

import static com.verificationgentleman.gradle.hdvl.internal.Names.getMainGenArgsFileTaskName;

public abstract class DefaultSourceSet implements SourceSet {
    private final String name;

    @Inject
    public DefaultSourceSet(String name, ObjectFactory objectFactory) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGenArgsFileTaskName(String toolName) {
        return name.equals("main")
                ? getMainGenArgsFileTaskName(toolName)
                : GUtil.toLowerCamelCase("gen" + " " + name + "" + toolName + "ArgsFile");
    }

    @Override
    public String getArgsFileName(String toolName) {
        String toolNameLower = toolName.toLowerCase();
        return name.equals("main")
            ? toolNameLower + "_" + "args.f"
            : name + "_" + toolNameLower + "_" + "args.f";
    }
}
