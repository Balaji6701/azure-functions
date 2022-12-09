package com.example.handler;

import java.util.Arrays;
import java.util.logging.Level;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.CosmosDBTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class CosmosDBEventHandler {
	@FunctionName("CosmosEvent")
	public void cosmosEventProcessor(
			@CosmosDBTrigger(name = "items", databaseName = "6701", collectionName = "users", createLeaseCollectionIfNotExists = true, connectionStringSetting = "AzureCosmosDBConnection") String[] items,
			ExecutionContext executionContext) {
		System.out.println(Arrays.asList(items));
		executionContext.getLogger().log(Level.INFO, "items length is " + items.length);
	}
}
