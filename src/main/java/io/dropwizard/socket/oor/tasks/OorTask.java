/*
 * Copyright (c) 2016 Phaneesh Nagaraja <phaneesh.n@gmail.com>.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.dropwizard.socket.oor.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.socket.oor.server.OorSocketServer;

import java.io.PrintWriter;

/**
 * @author phaneesh
 */
public class OorTask extends Task {

    private OorSocketServer oorSocketServer;

    public OorTask(OorSocketServer oorSocketServer) {
        super("oor");
        this.oorSocketServer = oorSocketServer;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) throws Exception {
        oorSocketServer.stop();
        printWriter.println("Service out of rotation");
    }
}
