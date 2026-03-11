# Vehicle Management System

A Java console application demonstrating Object-Oriented Programming (OOP) concepts.

## 🎯 Features

- **Register Vehicles**: Add cars, motorcycles, trucks, and buses
- **Manage Vehicle Types**: Each vehicle type has unique properties and operations
- **Track Details**: View comprehensive vehicle information
- **Vehicle Operations**: Type-specific actions (honk, load cargo, board passengers, etc.)
- **Service Management**: Track when vehicles need service
- **Search & Filter**: Find vehicles by ID, brand, price, or year

## 📁 Project Structure

```
Vehicle_Management_System/
├── docs/
│   └── HIGH_LEVEL_DESIGN.md      # Design documentation
├── src/
│   └── com/vehicle/management/
│       ├── model/                 # Vehicle classes
│       │   ├── Vehicle.java       # Abstract base class
│       │   ├── VehicleType.java   # Enum for vehicle types
│       │   ├── Car.java           # Car implementation
│       │   ├── Motorcycle.java    # Motorcycle implementation
│       │   ├── Truck.java         # Truck implementation
│       │   └── Bus.java           # Bus implementation
│       ├── service/               # Business logic
│       │   ├── Serviceable.java   # Interface
│       │   └── VehicleManager.java# Main management class
│       ├── exception/             # Custom exceptions
│       │   ├── VehicleException.java
│       │   ├── VehicleNotFoundException.java
│       │   ├── DuplicateVehicleException.java
│       │   └── InvalidVehicleDataException.java
│       ├── util/                  # Utilities
│       │   └── VehicleValidator.java
│       └── app/                   # Application entry
│           └── VehicleManagementApp.java
└── README.md
```

## 🔧 OOP Concepts Demonstrated

| Concept | Where Used |
|---------|------------|
| **Encapsulation** | Private fields with getters/setters in all classes |
| **Inheritance** | Car, Motorcycle, Truck, Bus extend Vehicle |
| **Polymorphism** | `displayDetails()`, `performOperation()` behave differently |
| **Abstraction** | Vehicle is abstract; Serviceable is an interface |
| **Interface** | Serviceable interface for service operations |

## 🚀 How to Run

### Using Command Line

1. Navigate to the project directory:
   ```bash
   cd Vehicle_Management_System
   ```

2. Compile all Java files:
   ```bash
   javac -d out src/com/vehicle/management/**/*.java
   ```

3. Run the application:
   ```bash
   java -cp out com.vehicle.management.app.VehicleManagementApp
   ```

### Using VS Code
1. Open the folder in VS Code
2. Install "Extension Pack for Java"
3. Press F5 to run VehicleManagementApp.java

## 📋 Usage

The application presents a menu-driven interface:

```
╔════════════════ MAIN MENU ════════════════╗
║  1. Register New Vehicle                   ║
║  2. View All Vehicles                      ║
║  3. Search Vehicle                         ║
║  4. View Vehicle Details                   ║
║  5. Update Vehicle                         ║
║  6. Remove Vehicle                         ║
║  7. Perform Vehicle Operation              ║
║  8. Service Vehicle                        ║
║  9. View Statistics                        ║
║ 10. View Vehicles by Type                  ║
║  0. Exit                                   ║
╚════════════════════════════════════════════╝
```

## 🎓 Learning Points

1. **Abstract Classes**: Vehicle class defines common structure
2. **Interfaces**: Serviceable defines a contract for service operations
3. **Enums**: VehicleType provides type-safe constants
4. **Custom Exceptions**: Specific error handling for different scenarios
5. **Collections**: HashMap for efficient vehicle storage
6. **Streams**: Used for filtering and searching vehicles
7. **Validation**: Utility class for data validation

## 📝 Requirements Met

- ✅ Using OOP concepts
- ✅ Register vehicle
- ✅ Manage different vehicle types
- ✅ Track vehicle details
- ✅ Type of vehicle (Enum)
- ✅ Vehicle-specific operations
- ✅ Extend features (inheritance)
- ✅ Define exceptions

## 👨‍💻 Author

Created as a learning project for Java OOP concepts.
