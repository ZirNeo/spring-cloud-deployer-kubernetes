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

package org.springframework.cloud.deployer.resource.docker;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

//TODO: move to shared location for CF and Mesos use

/**
 * A {@link Resource} implementation for resolving a Docker image.
 *
 * Note: {@link #getInputStream()} throws {@code UnsupportedOperationException}.
 *
 * @author Thomas Risberg
 */
public class DockerResource extends AbstractResource {

	private static String DOCKER_URI_SCHEME = "docker";

	private String scheme = "docker";

	private URI uri;

	/**
	 * Create a new {@code DockerResource} from an image name.
	 * @param imageName the name of the image in a docker registry.
	 */
	public DockerResource(String imageName) {
		this.uri = URI.create(DOCKER_URI_SCHEME + ":" + imageName);
	}

	/**
	 * Create a new {@code DockerResource} from a URI
	 * @param uri a URI
	 */
	public DockerResource(URI uri) {
		Assert.isTrue("docker".equals(uri.getScheme()), "A 'docker' scheme is required");
		this.uri = uri;
	}


	@Override
	public String getDescription() {
		return "Docker Resource [" + uri + "]";
	}

	/**
	 * This implementation currently throws {@code UnsupportedOperationException}
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		throw new UnsupportedOperationException("getInputStream not supported");
	}

	@Override
	public URI getURI() throws IOException {
		return uri;
	}

}
