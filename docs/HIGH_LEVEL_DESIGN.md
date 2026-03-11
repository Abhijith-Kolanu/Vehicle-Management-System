# Vehicle Management System - High Level Design Document

## 1. Overview
The Vehicle Management System is a Java-based application designed to register, manage, and track different types of vehicles. It demonstrates core Object-Oriented Programming (OOP) principles.

---

## 2. OOP Concepts Used

| Concept | Implementation |
|---------|----------------|
| **Encapsulation** | Private fields with public getters/setters in all classes |
| **Inheritance** | Car, Motorcycle, Truck, Bus extend abstract Vehicle class |
| **Polymorphism** | Method overriding (displayDetails, performOperation) |
| **Abstraction** | Abstract Vehicle class with abstract methods |
| **Interface** | Serviceable interface for service operations |

---

## 3. System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Main Application                         │
│                  (VehicleManagementApp)                     │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    VehicleManager                           │
│  - registerVehicle()                                        │
│  - removeVehicle()                                          │
│  - findVehicle()                                            │
│  - getAllVehicles()                                         │
│  - getVehiclesByType()                                      │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                  Vehicle (Abstract Class)                   │
│  - vehicleId, brand, model, year, price                     │
│  - displayDetails() [abstract]                              │
│  - performOperation() [abstract]                            │
└─────────────────────────────────────────────────────────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        ▼                     ▼                     ▼
┌───────────────┐   ┌───────────────┐   ┌───────────────┐
│     Car       │   │  Motorcycle   │   │    Truck      │
│ - numDoors    │   │ - engineCC    │   │ - loadCapacity│
│ - fuelType    │   │ - hasCarrier  │   │ - numAxles    │
└───────────────┘   └───────────────┘   └───────────────┘
        │                     │                     │
        └─────────────────────┼─────────────────────┘
                              ▼
                    ┌───────────────────┐
                    │   Serviceable     │
                    │   (Interface)     │
                    │ - service()       │
                    │ - getServiceCost()|
                    └───────────────────┘
```

---

## 4. Class Diagram

```
┌──────────────────────────────────────────┐
│           <<interface>>                  │
│            Serviceable                   │
├──────────────────────────────────────────┤
│ + service(): void                        │
│ + getServiceCost(): double               │
└──────────────────────────────────────────┘
                    △
                    │ implements
                    │
┌──────────────────────────────────────────┐
│           <<abstract>>                   │
│             Vehicle                      │
├──────────────────────────────────────────┤
│ - vehicleId: String                      │
│ - brand: String                          │
│ - model: String                          │
│ - year: int                              │
│ - price: double                          │
│ - type: VehicleType                      │
├──────────────────────────────────────────┤
│ + getters/setters                        │
│ + displayDetails(): void {abstract}      │
│ + performOperation(): void {abstract}    │
│ + calculateDepreciation(): double        │
└──────────────────────────────────────────┘
         △           △           △
         │           │           │
    ┌────┘     ┌─────┘     ┌─────┘
    │          │           │
┌───────┐  ┌───────┐  ┌───────┐
│  Car  │  │ Motor │  │ Truck │
│       │  │ cycle │  │       │
└───────┘  └───────┘  └───────┘
```

---

## 5. Package Structure

```
com.vehicle.management/
├── model/
│   ├── Vehicle.java          (Abstract base class)
│   ├── Car.java              (Extends Vehicle)
│   ├── Motorcycle.java       (Extends Vehicle)
│   ├── Truck.java            (Extends Vehicle)
│   ├── Bus.java              (Extends Vehicle)
│   └── VehicleType.java      (Enum)
├── service/
│   ├── VehicleManager.java   (Main management class)
│   └── Serviceable.java      (Interface)
├── exception/
│   ├── VehicleException.java
│   ├── VehicleNotFoundException.java
│   ├── DuplicateVehicleException.java
│   └── InvalidVehicleDataException.java
├── util/
│   └── VehicleValidator.java (Validation utility)
└── app/
    └── VehicleManagementApp.java (Main class)
```

---

## 6. Key Features

1. **Vehicle Registration**: Add new vehicles with unique IDs
2. **Vehicle Types**: Support for Car, Motorcycle, Truck, Bus
3. **Vehicle Tracking**: Search, filter, and list vehicles
4. **Type-Specific Operations**: Each vehicle type has unique operations
5. **Service Management**: Track and manage vehicle services
6. **Exception Handling**: Custom exceptions for error scenarios

---

## 7. Exception Handling Strategy

| Exception | When Thrown |
|-----------|-------------|
| `VehicleNotFoundException` | When searching for a non-existent vehicle |
| `DuplicateVehicleException` | When registering with existing ID |
| `InvalidVehicleDataException` | When vehicle data validation fails |

---

## 8. Data Flow

1. User interacts with `VehicleManagementApp` (Menu-driven)
2. App calls appropriate methods on `VehicleManager`
3. `VehicleManager` validates data using `VehicleValidator`
4. Operations performed on `Vehicle` objects (polymorphism)
5. Results returned to user

---

## 9. Future Enhancements

- Database integration for persistence
- GUI using JavaFX or Swing
- REST API for web access
- Vehicle maintenance scheduling
- Fuel tracking and analytics

---

## 10. Technologies Used

- **Language**: Java 11+
- **Build Tool**: Can use Maven/Gradle
- **IDE**: VS Code 
- **Concepts**: OOP, Collections, Exception Handling
