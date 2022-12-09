# Azure functions using spring boot.

### Versions:
* Spring boot - 2.77(Snapshot)
* Gradle - 7.5

## Prerequisite

* [Azure Functions core tools](https://go.microsoft.com/fwlink/?linkid=2174087) - _To test locally_
* Maven / Gradle
* Java
* Please check func is added to path variable before running the build commands
* Azure cosmos emulator to run cosmos locally.
* Add certificate to cacerts keystore using keytool command.
```ruby
keytool -cacerts -importcert -alias cosmos_emulator --file "File_Name"
```
### UserService
* userService branch contains rest service using cosmos as database.
* It runs on localhost and has get all users, get user by id and save user endpoints

### CosmosDBEventTrigger
* When any new insert happens in CosmosDB using User-Service, The CosmosDBEventHandler will run.
* It can be used to send notifications to admin or user

### Maven commands to start azure function

```ruby
mvn clean package
mvn azure-functions:run
mvn azure-functions:deploy
```
### Gradle commands to start azure function

```ruby
gradle clean build
gradle azureFunctionsRun
gradle azureFunctionsDeploy
```
* Azure functions core tool will create a function locally on mentioned port with url as http://localhost:{port}/api/{Function-Name}/{params}
* Route is used to configure path parameters and **@BindingName** is used to bind to path variables.
* Can trigger for many events like Http, Blob(Azure storage), Cosmos DB event, Kafka event, Queues.

### Additional Links
* **Spring boot auto wiring won't work on the azure function class but will work if we use FunctionInvoker.**[Refer database branch](https://github.com/Balaji6701/azure-functions/tree/database)
* [Reference for host.json](https://learn.microsoft.com/en-us/azure/azure-functions/functions-host-json) - Used to set azure configuration
* [Reference for local.settings.json](https://learn.microsoft.com/en-us/azure/azure-functions/functions-develop-local#local-settings-file) - Used to set azure configurations locally
* [Spring boot azure function example](https://learn.microsoft.com/en-us/samples/azure-samples/hello-spring-function-azure/hello-spring-function-azure/)
