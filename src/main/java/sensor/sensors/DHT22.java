package sensor.sensors;

import com.pi4j.wiringpi.Gpio;
import org.jetbrains.annotations.Contract;
import sensor.SensorInterface;

import java.util.HashMap;
import java.util.Map;

public class DHT22 implements SensorInterface
{
    private static final int READ_ATTEMPTS = 15;
    private static final int MAX_TIMINGS = 85;

    private int pin;

    private float temperature;
    private float humidity;

    public DHT22(int pin) throws Exception {
// @TODO: to enable on raspberry pi migration
//        if (Gpio.wiringPiSetup() == -1) {
//            throw new Exception("Can't setup wiring pi module");
//        }

        this.pin = pin;
    }

    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();

        for (int i = 0; i < READ_ATTEMPTS; i++) {
            if (this.collectData() == 1) break;
        }

        data.put("temperature", String.valueOf(this.temperature));
        data.put("humidity", String.valueOf(this.humidity));

        return data;
    }

    public Map<String, String> getCurrent()
    {
        Map<String, String> data = new HashMap<String, String>();

        data.put("temperature", String.valueOf(this.temperature));
        data.put("humidity", String.valueOf(this.humidity));

        return data;
    }

    private int collectData() {
        this.temperature = (float) Math.random();
        this.humidity = (float) Math.random();
        return 1;
// TODO@: to enable on raspberry pi migration
//        int laststate = Gpio.HIGH;
//        int counter = 0;
//        int j = 0, i;
//        int data[] = new int[5];
//
//        Gpio.pinMode(this.pin, Gpio.OUTPUT);
//        Gpio.digitalWrite(this.pin, Gpio.HIGH); Gpio.delay(10);
//        Gpio.digitalWrite(this.pin, Gpio.LOW);  Gpio.delay(18);
//        Gpio.digitalWrite(this.pin, Gpio.HIGH); Gpio.delayMicroseconds(40);
//        Gpio.pinMode(this.pin, Gpio.INPUT);
//
//        for (i = 0; i < MAX_TIMINGS; i++) {
//            counter = 0;
//            while (Gpio.digitalRead(this.pin) == laststate) {
//                counter++;
//                Gpio.delayMicroseconds(1);
//                if (counter == 255) {
//                    break;
//                }
//            }
//
//            laststate = Gpio.digitalRead(this.pin);
//
//            if (counter == 255) break;
//
//            // ignore first 3 transitions
//            if ((i >= 4) && (i%2 == 0)) {
//                // shove each bit into the storage bytes
//                data[j/8] <<= 1;
//
//                if (counter > 16) {
//                    data[j/8] |= 1;
//                }
//
//                j++;
//            }
//        }
//
//        // check we read 40 bits (8bit x 5 ) + verify checksum in the last byte
//        if ((j >= 40) && (data[4] == ((data[0] + data[1] + data[2] + data[3]) & 0xFF)) ) {
//            float t, h;
//
//            h = (float)data[0] * 256 + (float)data[1];
//            h /= 10;
//
//            t = (float)(data[2] & 0x7F)* 256 + (float)data[3];
//            t /= 10.0;
//
//            if ((data[2] & 0x80) != 0)  t *= -1;
//
//            this.temperature = t;
//            this.humidity = h;
//
//            return 1;
//        }
//
//        return 0;
    }
}
