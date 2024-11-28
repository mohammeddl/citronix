# Project Name: **Citronix**

## Description

🌟 Citronix Farm Management System 🌱
Citronix is a comprehensive farm management system designed to help farmers manage their farms, fields, trees, harvests, and sales. It streamlines processes and optimizes productivity tracking.

## 🛠️ Technologies Used

- **Spring Boot**: REST API and backend logic.
- **Spring Data JPA**: Database management.
- **Docker**: Containerization of the application.
- **MapStruct**: Used for mapping between entities and DTOs.
- **Lombok**: Used to reduce boilerplate code (getters, setters, etc.).
- **Mockito**: For unit testing with mock data.
- **PostgreSQL**: The database used for persistence.
- **DigitalOcean**: Cloud service for hosting the application.

## 🚀 Features

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

## 📝 API Documentation

The API documentation is available at [Swagger UI](https://citronix.systems/swagger-ui/index.html).

## 📦 Installation

1. Clone the repository.
2. Run the application using the following command:

```bash
docker-compose up
```

3. Access the application at [http://localhost:8084](http://localhost:8084).
4. Access the API documentation at [Swagger UI](http://localhost:8084/swagger-ui/index.html).
5. To stop the application, run the following command:

```bash
docker-compose down
```

## API Endpoints

- **Farm**: `/api/farms`
- **Field**: `/api/fields`
- **Tree**: `/api/trees`
- **Harvest**: `/api/harvests`
- **Sales**: `/api/sales`

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

📧 For inquiries, reach out to the team at [daali.22.ss@gmail.com](mailto:daali.22.ss@gmail.com).

👥 Connect with us on LinkedIn: [LinkedIn](https://www.linkedin.com/in/daali-mohammed-85736b271/).

🌐 Visit our website: [Citronix Systems](https://citronix.systems/swagger-ui/index.html).
