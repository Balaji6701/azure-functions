package com.cts.azure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cts.azure.exceptions.ProductNotFoundException;
import com.cts.azure.models.Product;
import com.cts.azure.services.ProductService;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

@Component
public class ProductFunction {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private ProductService productService = new ProductService();

	@FunctionName("products")
	public HttpResponseMessage execute(@HttpTrigger(name = "request", methods = { HttpMethod.GET,
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "products/{id=}") HttpRequestMessage<Product> request,
			@BindingName("id") String id, ExecutionContext context) {
		try {
			if (request.getHttpMethod() == HttpMethod.GET) {

				if (id.length() > 0) {
					return request.createResponseBuilder(HttpStatus.OK).body(productService.getProductById(id))
							.header("Content-Type", "application/json").build();
				} else {
					return request.createResponseBuilder(HttpStatus.OK).body(productService.getAllProducts())
							.header("Content-Type", "application/json").build();
				}
			} else {
				Product product = request.getBody();
				if (product == null || product.getName() == null || product.getName().length() <= 0) {
					throw new RuntimeException("Product should have valid body");
				}
				context.getLogger().info("Body is" + product);
				return request.createResponseBuilder(HttpStatus.CREATED).body(productService.saveProduct(product))
						.header("Content-Type", "application/json").build();
			}
		} catch (ProductNotFoundException productNotFoundException) {
			return request.createResponseBuilder(HttpStatus.NOT_FOUND).body(productNotFoundException.getMessage())
					.header("Content-Type", "text/plain").build();
		} catch (Exception e) {
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage())
					.header("Content-Type", "text/plain").build();
		}
	}

}
