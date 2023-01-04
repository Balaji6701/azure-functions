# Audit Log
* Use seperate class for audit and extend the entity class. Do not add fields to entities. Promotes code re-usability.
* @CreatedBy - Used to map who created the entity
* @LastModifiedBy - Used to map who modified the entity last
* @CreatedDate - maps to the date when the entity is created.
* @LastModifiedDate - Maps to the date when the entity is last updated.
* **Use @EnableCosmosAuditing**
* The created and modified usernames will will automatically populated if spring security is added to the project.
* Implement the **AuditorAware** interface to automatically populate the usernames in CreatedBy and LastModifiedBy
