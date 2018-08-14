/*
 * Copyright 2018 Vorlonsoft LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

final class EasyDokka {

    private static volatile EasyDokka singleton = null

    private final def project

    private EasyDokka(project) {
        this.project = project
    }

    static EasyDokka with(project) {
        if (singleton == null) {
            synchronized (EasyDokka.class) {
                if (singleton == null) {
                    singleton = new EasyDokka(project)
                }
            }
        }
        return singleton
    }

    String getDokkaFatJarVersion() {
        return project.hasProperty('DOKKA_FATJAR_VERSION') ? project.DOKKA_FATJAR_VERSION : '0.9.17'
    }
}