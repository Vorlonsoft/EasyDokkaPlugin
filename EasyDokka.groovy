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

/**
 * <p>EasyDokka Class - class with gradle properties getters, thread-safe and a fast singleton
 * implementation.</p>
 *
 * @since 1.1.0 Yokohama
 * @version 1.1.0 Yokohama
 * @author Alexander Savin
 */

final class EasyDokka {

    private static volatile EasyDokka singleton = null

    private final def project

    private EasyDokka(project) {
        this.project = project
    }

    /**
     * Only method to get singleton object of EasyDokka Class
     *
     * @param project project
     * @return thread-safe singleton
     */
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

    /**
     * Returns Dokka fatjar version
     *
     * @return DOKKA_FATJAR_VERSION gradle property value or "0.9.17" if project hasn't
     * this property
     */
    String getDokkaFatJarVersion() {
        return project.hasProperty('DOKKA_FATJAR_VERSION') ? project.DOKKA_FATJAR_VERSION : '0.9.17'
    }

    /**
     * Returns Dokka output format
     *
     * @return DOKKA_OUTPUT_FORMAT gradle property value or "javadoc" if project hasn't
     * this property
     */
    String getDokkaOutputFormat() {
        return project.hasProperty('DOKKA_OUTPUT_FORMAT') ? project.DOKKA_OUTPUT_FORMAT : 'javadoc'
    }
}