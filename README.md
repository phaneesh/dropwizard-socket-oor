# Dropwizard Oor Bundle [![Travis build status](https://travis-ci.org/phaneesh/dropwizard-socket-oor.svg?branch=master)](https://travis-ci.org/phaneesh/dropwizard-socket-oor)

This bundle adds a healthcheck which can used to take the application out of rotation from
a loadbalancer which uses a tcp server socket endpoint for healthchecks
This bundle compiles only on Java 8.
 
## Usage
This makes it easier perform rolling deployments & maintenance of dropwizard applications fronted by nginx/haproxy like front end proxies
 
### Build instructions
  - Clone the source:

        git clone github.com/phaneesh/dropwizard-socket-oor

  - Build

        mvn install

### Maven Dependency
* Use the following maven dependency:
```
<dependency>
    <groupId>io.dropwizard.socket.oor</groupId>
    <artifactId>dropwizard-socket-oor</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Using Socket Oor bundle

#### Bootstrap
```java
    @Override
    public void initialize(final Bootstrap...) {
        bootstrap.addBundle(new OorSocketBundle() {
            
            public int oorPort() {
                return 8082;
            }
            
        });
    }
```

#### Tasks
* ```curl -XPOST http://host:adminport/tasks/oor``` for taking node out of rotation

## Note
Please allow the node to run for the grace period and a bit more that
you have configured in your load balancer healthchecks so that the
load balancer can drain all the requests gracefully.

The app needs to be restarted once taken out of rotation to bring it back to rotation