import { Camera, CameraView } from "expo-camera";
import { useRouter } from "expo-router";
import React, { useState, useEffect, useRef } from "react";
import { SafeAreaView, StatusBar, StyleSheet, Alert, Platform, Text, View, Pressable } from "react-native";
import axios from "axios";
import { BASE_URL } from "../constants/util";

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
        console.log("dshkdhk")
        console.log(data);  
            try {
                const response = await axios.put(BASE_URL + "vehicle/" + data);
                console.log(response.data);
            
                if (response.status === 200) {
                    const vehicleDetails = response.data; 
            
                    const payload = {
                        qrString: data, 
                        ...vehicleDetails,
                    };
            
                    console.log("Scanned Data:", payload);
            
                   
                    router.push({
                        pathname: "./fillfuel",
                        params: payload,
                    });
                } else {
                    Alert.alert("Error", "Invalid QR code. Please try again.");
                }
            } catch (error) {
                console.log(error);
                Alert.alert("Error", "Failed to validate QR code. Please try again.");
            
        }
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
                    onBarcodeScanned={handleBarCodeScanned}
                />
            </View>

            <Pressable style={styles.button} onPress={() => router.push("./dashboard")}>
                <Text style={styles.buttonText}>Go to Dashboard</Text>
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
        width: 300, 
        height: 400, 
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
        marginTop: 20,
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