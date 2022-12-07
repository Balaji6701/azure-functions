package com.example.handler;

import java.util.List;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.example.exceptions.ProductNotFoundException;
import com.example.models.Product;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class ProductListHandler extends FunctionInvoker<Object, List<Product>> {
	
	@FunctionName("productListGet")
	public HttpResponseMessage getProduct(@HttpTrigger(name = "productListGet", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "product") HttpRequestMessage request,
			ExecutionContext context) throws ProductNotFoundException {

		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest("", context))
				.header("Content-Type", "application/json").build();
	}
	
}
