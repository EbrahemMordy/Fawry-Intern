package org.example.jakarta.hello;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sources.Product;

import java.util.ArrayList;
import java.util.List;

@Path("products")
public class ProductResource {
    private static List<Product> products = new ArrayList<>(List.of(new Product("Laptop", 1000), new Product("Mouse", 20), new Product("Keyboard", 50)));

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok(products).build();
    }

    @GET
    @Path("add/{name}/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@PathParam("name") String name, @PathParam("price") double price) {
        products.add(new Product(name, price));
        return Response.ok().entity("Product " + name + " added successfully!").build();
    }

    @GET
    @Path("delete/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("name") String name) {
        boolean removed = products.removeIf(product -> product.getName().equals(name));
        if (removed) {
            return Response.ok().entity("Product " + name + " deleted successfully!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
    }

    @GET
    @Path("update/{name}/{newname}/{newprice}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("name") String name, @PathParam("newname") String newName, @PathParam("newprice") double newPrice) {

        boolean updated = false;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setName(newName);
                product.setPrice(newPrice);
                updated = true;
                break;
            }
        }

        if (updated) {
            return Response.ok().entity("Product updated successfully!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
    }
}