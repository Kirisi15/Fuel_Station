import React, { useState, useEffect } from "react";
import { View, Text, TextInput, Pressable, Alert, StyleSheet, ActivityIndicator } from "react-native";
import { useLocalSearchParams, useRouter } from "expo-router";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

export default function FuelFilling() {
    const params = useLocalSearchParams();
    const { qrString, exsistingFuel, vehicleRegistrationNumber } = params;
    const [fuelLimit, setFuelLimit] = useState(0);
    const [pumpedFuel, setPumpedFuel] = useState(0);
    const [enteredFuel, setEnteredFuel] = useState("");
    const [loading, setLoading] = useState(true);
    const router = useRouter();

    useEffect(() => {
        const fetchFuelLimit = async () => {
            try {
                const token = await AsyncStorage.getItem("authToken");
                const response = await axios.get(`http://172.19.67.1:8080/api/fuel/get-limit/${vehicleRegistrationNumber}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });

                if (response.status === 200) {
                    setFuelLimit(response.data.fuelLimit);
                    setPumpedFuel(response.data.pumpedFuel);
                }
            } catch (error) {
                Alert.alert("Error", "Failed to fetch fuel limit.");
            } finally {
                setLoading(false);
            }
        };

        fetchFuelLimit();
    }, [vehicleRegistrationNumber]);

    const handleFuelSubmit = async () => {
        const fuelAmount = parseFloat(enteredFuel);

        if (fuelAmount + pumpedFuel > fuelLimit) {
            Alert.alert("Error", "You cannot get this amount of fuel.");
            return;
        }

        try {
            const token = await AsyncStorage.getItem("authToken");
            const payload = { qrString, vehicleRegistrationNumber, fuelAmount };

            const response = await axios.post(
                "http://172.19.67.1:8080/api/fuel/update-transaction",
                payload,
                { headers: { Authorization: `Bearer ${token}` } }
            );

            if (response.status === 200) {
                Alert.alert("Success", "Fuel transaction recorded successfully.");
                router.push(
                "/fuelsummary");
            } else {
                Alert.alert("Error", "Transaction failed.");
            }
        } catch (error) {
            Alert.alert("Error", "Failed to record transaction.");
        }
    };

    if (loading) return <ActivityIndicator size="large" color="#ff9800" />;

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Fuel Filling</Text>
            <Text>Vehicle: {vehicleRegistrationNumber}</Text>
            <Text>Existing Fuel: {exsistingFuel} L</Text>
            <Text>Fuel Limit: {fuelLimit} L</Text>
            <Text>Pumped Fuel: {pumpedFuel} L</Text>

            <TextInput
                style={styles.input}
                placeholder="Enter Fuel (L)"
                keyboardType="numeric"
                value={enteredFuel}
                onChangeText={setEnteredFuel}
            />

            <Pressable style={styles.button} onPress={handleFuelSubmit}>
                <Text style={styles.buttonText}>Submit</Text>
            </Pressable>
        </View>
    );
}

const styles = StyleSheet.create({
    container: { flex: 1, justifyContent: "center", alignItems: "center", padding: 20, backgroundColor: "#f8f8f8" },
    title: { fontSize: 22, fontWeight: "bold", marginBottom: 20 },
    input: { width: "80%", height: 40, borderColor: "#ccc", borderWidth: 1, paddingHorizontal: 10, marginTop: 20 },
    button: { backgroundColor: "#ff9800", padding: 15, borderRadius: 8, marginTop: 20 },
    buttonText: { color: "#fff", fontWeight: "bold" },
});
