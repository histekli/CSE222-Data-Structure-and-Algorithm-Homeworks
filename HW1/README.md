# HW1 - Hardware System Simulation

A comprehensive Java-based hardware system simulation that manages various electronic devices and communication protocols.

## 🎯 Project Overview

This project simulates a hardware system with multiple device types including sensors, motors, displays, and wireless communication modules. The system supports various communication protocols and provides error handling capabilities.

## 🏗️ Architecture

### Core Components

- **HWSystem**: Main system controller
- **ConfigReader**: Configuration file parser
- **ErrorHandler**: System error management
- **Port**: Hardware port abstraction

### Device Types

- **Sensors**: BME280, DHT11, GY951, IMU, MPU6050, Temperature sensors
- **Motors**: PCA9685, SparkFun Motor Driver
- **Displays**: LCD, OLED
- **Wireless**: Bluetooth, WiFi

### Communication Protocols

- **I2C**: Inter-Integrated Circuit
- **SPI**: Serial Peripheral Interface
- **UART**: Universal Asynchronous Receiver-Transmitter
- **OneWire**: Single-wire communication protocol

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Make (for building)

### Building the Project

```bash
make
```

### Running the Project

```bash
make run
```

### Cleaning Build Files

```bash
make clean
```

## 📁 Project Structure

```
HW1/
├── src/                    # Source code
│   ├── core/              # Core system components
│   ├── devices/           # Device implementations
│   ├── protocols/         # Communication protocols
│   └── Main/              # Main application
├── build/                 # Compiled classes
├── test_configs/          # Test configuration files
├── config.txt            # Main configuration
├── makefile              # Build configuration
└── README.md             # This file
```

## ⚙️ Configuration

The system uses configuration files to define device setups. See `test_configs/` for examples:

- `config1.txt`: Basic sensor configuration
- `config2.txt`: Complex multi-device setup
- `scenario1.txt`: Test scenario 1
- `scenario2.txt`: Test scenario 2

## 🔧 Usage Examples

### Basic Usage

```bash
java Main config.txt
```

### With Test Configurations

```bash
java Main test_configs/config1.txt
```

## 📝 Features

- ✅ Multiple device type support
- ✅ Protocol abstraction layer
- ✅ Configuration file parsing
- ✅ Error handling and logging
- ✅ Modular architecture
- ✅ Extensible design

## 🐛 Testing

Test configurations are available in the `test_configs/` directory. Each configuration tests different aspects of the system.

## ‍💻 Author

**Hasan Can İstekli**  
Student ID: 210104004058  
Course: CSE222 - Data Structures and Algorithms
