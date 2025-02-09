import React, { useState, useEffect } from "react";
import { View, Text, TextInput, Pressable, Alert, StyleSheet, ActivityIndicator } from "react-native";
import { useLocalSearchParams, useRouter } from "expo-router";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";
import { BASE_URL } from "../constants/util";

export default function FuelFilling() {
    const params = useLocalSearchParams();
    const { qrString, exsistingFuel, vehicleType } = params;
    const [fuelLimit, setFuelLimit] = useState<number>(0);
    const [pumpedFuel, setPumpedFuel] = useState<number>(0);
    const [enteredFuel, setEnteredFuel] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(true);
    
    const router = useRouter();

    useEffect(() => {
        const fetchFuelData = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/fuelLimit/limit/${vehicleType}`);
                if (response.status === 200) {
                    setFuelLimit(response.data.fuelLimit);
                }

                const pumpedResponse = await axios.get(`${BASE_URL}/vehicleFuelQuota/pumpedFuel/${qrString}`);
                if (pumpedResponse.status === 200) {
                    setPumpedFuel(pumpedResponse.data.pumpedFuel);
                }
            } catch (error) {
                Alert.alert("Error", "Failed to fetch fuel data.");
            } finally {
                setLoading(false);
            }
        };

        fetchFuelData();
    }, [vehicleType, qrString]);

    const handleFuelSubmit = async () => {
        const fuelAmount = parseFloat(enteredFuel);

        if (isNaN(fuelAmount) || fuelAmount <= 0) {
            Alert.alert("Error", "Please enter a valid fuel amount.");
            return;
        }

        if (fuelAmount + pumpedFuel > fuelLimit) {
            Alert.alert("Error", "You cannot get this amount of fuel.");
            return;
        }

        try {
            const token = await AsyncStorage.getItem("authToken");
            const payload = { qrString, vehicleType, fuelAmount };

            const response = await axios.post(
                `${BASE_URL}/fuel/update-transaction`,
                payload,
                { headers: { Authorization: `Bearer ${token}` } }
            );

            if (response.status === 200) {
                Alert.alert("Success", "Fuel transaction recorded successfully.");
                router.push("./fuelsummary");
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
            <Text>Vehicle: {vehicleType}</Text>
            <Text>Existing Fuel: {exsistingFuel} L</Text>
            <Text>Fuel Limit: {fuelLimit} L</Text>
            <Text>Pumped Fuel: {pumpedFuel} L</Text>

            <TextInput
                style={styles.input}
                placeholder="Enter Fuel amount"
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
