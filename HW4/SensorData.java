/**
 * Represents sensor data for a node in the planetary system.
 * Includes temperature, pressure, humidity, and radiation levels.
 */
public class SensorData {
    private double temperature; // Kelvin
    private double pressure; // Pascals
    private double humidity; // Percentage (0-100)
    private double radiation; // Sieverts

    /**
     * Constructs a SensorData object with the specified values.
     * 
     * @param temperature The temperature in Kelvin.
     * @param pressure    The pressure in Pascals.
     * @param humidity    The humidity as a percentage (0-100).
     * @param radiation   The radiation level in Sieverts.
     */
    public SensorData(double temperature, double pressure, double humidity, double radiation) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }

    /**
     * Gets the temperature.
     * 
     * @return The temperature in Kelvin.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the pressure.
     * 
     * @return The pressure in Pascals.
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Gets the humidity.
     * 
     * @return The humidity as a percentage (0-100).
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Gets the radiation level.
     * 
     * @return The radiation level in Sieverts.
     */
    public double getRadiation() {
        return radiation;
    }

    /**
     * Returns a string representation of the sensor data.
     * 
     * @return A formatted string with temperature, pressure, humidity, and
     *         radiation.
     */
    @Override
    public String toString() {
        return String.format("%.2f Kelvin, %.2f Pascals, %.2f%% Humidity, %.2f Sieverts",
                temperature, pressure, humidity, radiation);
    }
}