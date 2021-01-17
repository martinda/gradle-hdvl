/*
 * Copyright 2021 the original author or authors.
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

package com.verificationgentleman.gradle.hdvl.dvt;

import com.verificationgentleman.gradle.hdvl.GenFullArgsFile;
import com.verificationgentleman.gradle.hdvl.HDVLBasePlugin;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DVTPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getPluginManager().apply(HDVLBasePlugin.class);

        project.getTasks().register("dvt", DVTTask.class, new Action<DVTTask>() {
            @Override
            public void execute(DVTTask dvt) {
                dvt.setDescription("Generates a DVT project.");
                setArgsFile(dvt);
            }

            private void setArgsFile(DVTTask dvt) {
                GenFullArgsFile genFullArgsFile
                        = dvt.getProject().getTasks().withType(GenFullArgsFile.class).getByName("genFullArgsFile");
                dvt.getArgsFile().set(genFullArgsFile.getDestination());
            }
        });
    }
}
