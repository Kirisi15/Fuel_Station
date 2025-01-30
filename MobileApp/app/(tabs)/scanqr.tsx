import { Camera, CameraView } from "expo-camera";
import { useRouter } from "expo-router";
import React, { useState, useEffect, useRef } from "react";
import { SafeAreaView, StatusBar, StyleSheet, Alert, Platform, Text, View, Pressable } from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function ScanScreen() {
    const [hasPermission, setHasPermission] = useState<boolean | null>(null);
    const [scanning, setScanning] = useState(false);
    const router = useRouter();
    const qrLock = useRef(false);

    useEffect(() => {
        const requestPermission = async () => {
            const { status } = await Camera.requestCameraPermissionsAsync();
            setHasPermission(status === "granted");
        };
        requestPermission();
    }, []);

    const handleBarCodeScanned = async ({ data }: { data: string }) => {
        if (data && !qrLock.current) {
            qrLock.current = true;
            setScanning(true);

            const token = await AsyncStorage.getItem("authToken");

            if (!token) {
                Alert.alert("Error", "No authentication token found. Please log in.");
                setScanning(false);
                qrLock.current = false;
                return;
            }

            try {
                const response = await axios.post(
                    "http://172.19.67.1:8080/api/qr/check-qr-string",
                    { qrString: data },
                    {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    }
                );

                if (response.status === 200) {
                    const { exsistingFuel, vehicleRegistrationNumber } = response.data;
                    const payload = {
                        qrString: data,
                        exsistingFuel,
                        vehicleRegistrationNumber,
                    };

                    console.log("Storing payload:", payload);

                    // Store in AsyncStorage
                    await AsyncStorage.setItem("scannedData", JSON.stringify(payload));

                    // Navigate to FuelFilling Screen
                    router.push("/Fillfuel");  // Update this to go to FuelFilling screen
                } else {
                    Alert.alert("Error", "Invalid QR code. Please try again.");
                }
            } catch (error) {
                Alert.alert("Error", "Failed to validate QR code. Please try again.");
            } finally {
                setScanning(false);
                qrLock.current = false;
            }
        }
    };

    const handleLogout = async () => {
        // Clear AsyncStorage or any necessary data
        await AsyncStorage.removeItem("authToken");
        await AsyncStorage.removeItem("scannedData");

        // Navigate to sign-in screen
        router.push("/signin");
    };

    if (hasPermission === null) {
        return <Text>Requesting camera permission...</Text>;
    }
    if (hasPermission === false) {
        return <Text>No access to camera</Text>;
    }

    return (
        <SafeAreaView style={styles.container}>
            {Platform.OS === "android" ? <StatusBar hidden /> : null}

            <Text style={styles.title}>Scan QR Code</Text>

            {/* Camera View Container */}
            <View style={styles.cameraContainer}>
                <CameraView
                    style={styles.camera}
                    onBarcodeScanned={scanning ? undefined : handleBarCodeScanned}
                />
            </View>

            {/* Logout Button */}
            <Pressable style={styles.button} onPress={() => router.push("/dashboard")}>
                <Text style={styles.buttonText}>Logout</Text>
            </Pressable>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#f8f8f8",
    },
    title: {
        fontSize: 22,
        fontWeight: "bold",
        marginBottom: 20,
        color: "#333",
    },
    cameraContainer: {
        width: 300, // Adjust width as needed
        height: 400, // Adjust height as needed
        overflow: "hidden",
        borderRadius: 10,
        borderWidth: 2,
        borderColor: "#ff9800",
    },
    camera: {
        flex: 1,
    },
    button: {
        backgroundColor: "#ff9800",
        paddingVertical: 12,
        paddingHorizontal: 30,
        borderRadius: 8,
        marginTop
        : 20,
        shadowColor: "#000",
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.3,
        shadowRadius: 3,
        elevation: 5,
    },
    buttonText: {
        fontSize: 18,
        color: "#fff",
        fontWeight: "bold",
    },
});
