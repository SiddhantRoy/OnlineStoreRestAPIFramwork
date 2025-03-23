**OnlineStoreRestAPI**
link : https://fakestoreapi.com/docs
**1.Overview**
The project involves building and testing APIs for 1 module: Products. The APIs
support CRUD operations, filtering, sorting, and specific use cases . The design covers both functional requirements and user story-based scenarios.

**2. Modules and Functionalities**
2.1 Products Module
• API Endpoints:
- GET /products: Retrieve all products.
- GET /products/{id}: Retrieve a product by ID.
- GET /products?limit=x: Retrieve a limited number of products.
- GET /products?sort=asc|desc: Retrieve products in a specific order.
- GET /products/categories: Retrieve all product categories.
- GET /products/category/{category}: Retrieve products by category.
- POST /products: Add a new product.
- PUT /products/{id}: Update an existing product.
- PATCH /products/{id}: Partially update a product.
- DELETE /products/{id}: Delete a product.

**3. Testing Design**
3.1 Test Cases
• Products Module:
- Verify retrieval of all products.
- Verify retrieval of a product by ID.
- Verify retrieval of products with a limit.
- Verify retrieval of products with sorting.
- Verify CRUD operations for products.
- Validate error handling for invalid product IDs.

3.2 Automation Framework
• Technology Stack:
- Programming Language: Java
- Testing Framework: TestNG or JUnit
- HTTP Client: REST Assured
- Reporting: Allure Reports, Extent Reports

4. Generate Allure Reports
• Install Maven & Allure Binaries and configure the system path:
o https://maven.apache.org/download.cgi
o https://allurereport.org/docs/install-for-windows/
• Verify installation:
  mvn --version
  allure --version
• Run tests using testng.xml.
• Generate Allure Reports:
allure serve <<Location of allure-results folder>>
5. Execute Failed Tests
• Re-run only failed test cases using testng-failed.xml.

