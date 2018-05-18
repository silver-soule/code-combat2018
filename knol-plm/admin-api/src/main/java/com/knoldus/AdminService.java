package com.knoldus;

import akka.Done;
import akka.NotUsed;
import com.knoldus.models.ProjectResource;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

/**
 * The Admin service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Admin.
 */
public interface AdminService extends Service {
    
    ServiceCall<NotUsed, String> helloAdmin(String id);
    
    ServiceCall<ProjectResource, Done> addResource();
    
    ServiceCall<NotUsed, Done> deleteResource(Integer id);
    
    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("admin-service").withCalls(
                pathCall("/api/admin/:id", this::helloAdmin),
                restCall(Method.POST, "/api/admin/addResource", this::addResource),
                restCall(Method.DELETE, "/api/admin/deleteResource/:id", this::deleteResource)
        ).withAutoAcl(true);
        // @formatter:on
    }
}
