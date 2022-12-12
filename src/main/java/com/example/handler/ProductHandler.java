package com.example.handler;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.example.exceptions.ProductNotFoundException;
import com.example.models.Product;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class ProductHandler extends FunctionInvoker<Object, Object> {

	@FunctionName("productGet")
	public HttpResponseMessage getProduct(@HttpTrigger(name = "productGet", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "product/{id}") HttpRequestMessage<Product> request,
			ExecutionContext context, @BindingName("id") String id) throws ProductNotFoundException {

		context.getLogger().info("Getting product with id: " + id);
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(id, context))
				.header("Content-Type", "application/json").build();
	}

	@FunctionName("productSave")
	public HttpResponseMessage saveProduct(@HttpTrigger(name = "productSave", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "product") HttpRequestMessage<Product> request,
			ExecutionContext context) throws ProductNotFoundException {

		Product product = request.getBody();
		context.getLogger().info("Saving product: " + product);
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(product, context))
				.header("Content-Type", "application/json").build();
	}

	@FunctionName("productDelete")
	public HttpResponseMessage deleteProduct(@HttpTrigger(name = "productDelete", methods = {
			HttpMethod.DELETE }, authLevel = AuthorizationLevel.ANONYMOUS, route = "product/{id}") HttpRequestMessage<Product> request,
			ExecutionContext context, @BindingName("id") String id) throws ProductNotFoundException {

		context.getLogger().info("Deleting product with id: " + id);
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(id, context))
				.header("Content-Type", "application/json").build();
	}

	@FunctionName("productPut")
	public HttpResponseMessage updateProduct(@HttpTrigger(name = "productPut", methods = {
			HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "product") HttpRequestMessage<Product> request,
			ExecutionContext context) throws ProductNotFoundException {
		Product product = request.getBody();
		context.getLogger().info("Getting product with id: " + product);
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(product, context))
				.header("Content-Type", "application/json").build();
	}
}
