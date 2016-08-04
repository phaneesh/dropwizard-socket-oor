/*
 * Copyright 2016 Phaneesh Nagaraja <phaneesh.n@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.dropwizard.socket.oor;

import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.socket.oor.server.OorSocketServer;
import io.dropwizard.socket.oor.tasks.OorTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author phaneesh
 */
@Slf4j
public abstract class OorSocketBundle<T extends Configuration> implements ConfiguredBundle<T> {

    public abstract int oorPort();

    private ExecutorService executorService;

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        OorSocketServer oorSocketServer = new OorSocketServer(oorPort());
        executorService = Executors.newSingleThreadExecutor();
        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() throws Exception {
                executorService.submit(oorSocketServer);
            }

            @Override
            public void stop() throws Exception {
                oorSocketServer.stop();
                executorService.shutdownNow();
            }
        });

        environment.admin().addTask(new OorTask(oorSocketServer));
    }
}
