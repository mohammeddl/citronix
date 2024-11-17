🌟 Citronix Farm Management System 🌱
Citronix is a comprehensive farm management system designed to help farmers manage their farms, fields, trees, harvests, and sales. It streamlines processes and optimizes productivity tracking.

🚀 Features
🌾 Farm Management
🛠️ CRUD Operations: Create, update, and delete farms.
📊 Validation: Ensure the sum of field areas is less than the farm's total area.
🔍 Search: Multi-criteria search for farms.
🌱 Field Management
🛠️ Add fields to farms with area constraints.
🔢 Limit farms to a maximum of 10 fields.
🌳 Tree Management
🌿 Track trees by planting date, age, and assigned field.
📈 Calculate tree productivity:
Young Trees (< 3 years): 2.5 kg/season
Mature Trees (3-10 years): 12 kg/season
Old Trees (> 10 years): 20 kg/season
📅 Validate planting dates (only between March–May).
🌦️ Harvest Management
🛠️ Record harvest details, including quantity and season.
🕒 Ensure one harvest per season per field.
💰 Sales Management
🛒 Log sales with client details and unit price.
💵 Calculate total revenue: quantity × unitPrice.
🛠️ Technologies Used
🔧 Backend
Spring Boot: REST API and backend logic.
Spring Data JPA: Database management.
Lombok: Reduce boilerplate code.
MapStruct: Automate entity-DTO mappings.
JUnit and Mockito: Unit testing.
