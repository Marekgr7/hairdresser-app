package com.gryszq.microservices.workmodelservice.rest.controller;

import com.gryszq.microservices.workmodelservice.exceptions.ServiceNotFoundException;
import com.gryszq.microservices.workmodelservice.exceptions.ServiceUniqueConstraintException;
import com.gryszq.microservices.workmodelservice.model.Service;
import com.gryszq.microservices.workmodelservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceRepository.findAll();

        if(services.isEmpty()) throw new ServiceNotFoundException("Services: NOT FOUND");
        else return ResponseEntity.ok().body(services);
    }

    @GetMapping("/{id}")
    public @ResponseBody Resource<Service> getOneService(@PathVariable Long id) {
        Service service = serviceRepository.findById(id).orElse(null);

        if(service == null) throw new ServiceNotFoundException("Service with id: " + id + " NOT FOUND");

        Resource<Service> resource = new Resource<>(service);
        ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllServices());
        resource.add(link.withRel("all-users"));

        return resource;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> addNewService(@Valid @RequestBody Service service) {
        List<String> existingServices = serviceRepository.selectAllNames();

        for (String tmp : existingServices) {
            if(tmp.equals(service.getName())) throw new ServiceUniqueConstraintException("Service with this name already exists");
        }

        serviceRepository.save(service);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(service.getName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        Service service = serviceRepository.findById(id).orElse(null);
        if(service == null) throw new ServiceNotFoundException("Service with id: " + id + " NOT FOUND");
        else {
            serviceRepository.delete(service);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
