package com.example.handler;

import java.util.logging.Level;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.CosmosDBTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class CosmosDBEventHandler {
	@FunctionName("CosmosEvent")
	public void cosmosEventProcessor(
			@CosmosDBTrigger(name = "items", databaseName = "stores", collectionName = "product", createLeaseCollectionIfNotExists = true, connectionStringSetting = "AzureCosmosDBConnection") String[] items,
			ExecutionContext executionContext) {
		executionContext.getLogger().log(Level.INFO, "The following items inserted into database");
		for (String item : items) {
			executionContext.getLogger().log(Level.INFO, item);
		}
		executionContext.getLogger().log(Level.INFO, "items length is " + items.length);
	}
}
