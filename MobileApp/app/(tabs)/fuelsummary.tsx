import React, { useState, useEffect } from "react";
import { View, Text, StyleSheet, ActivityIndicator, Pressable, Alert } from "react-native";
import { useLocalSearchParams, useRouter } from "expo-router";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

export default function FuelSummary() {
    const router = useRouter();
    const params = useLocalSearchParams();
    const { vehicleRegistrationNumber } = params;

    const [loading, setLoading] = useState(true);
    const [summary, setSummary] = useState({
        vehicleOwner: "",
        vehicleType: "",
        fuelType: "",
        totalFuelLimit: 0,
        pumpedFuel: 0,
        lastFilledAmount: 0,
    });

    useEffect(() => {
        const fetchSummary = async () => {
            try {
                const token = await AsyncStorage.getItem("authToken");
                const response = await axios.get(
                    `http://172.19.89.229:8080/apifuel/get-summary/${vehicleRegistrationNumber}`,
                    { headers: { Authorization: `Bearer ${token}` } }
                );

                if (response.status === 200) {
                    setSummary(response.data);
                } else {
                    Alert.alert("Error", "Failed to fetch fuel summary.");
                }
            } catch (error) {
                Alert.alert("Error", "Error fetching fuel summary.");
            } finally {
                setLoading(false);
            }
        };

        fetchSummary();
    }, [vehicleRegistrationNumber]);

    const handleFinish = () => {
        router.push("./dashboard");
    };

    if (loading) return <ActivityIndicator size="large" color="#ff9800" />;

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Fuel Summary</Text>

            <View style={styles.summaryContainer}>
                <Text style={styles.label}>Vehicle Number:</Text>
                <Text style={styles.value}>{vehicleRegistrationNumber}</Text>

                <Text style={styles.label}>Owner:</Text>
                <Text style={styles.value}>{summary.vehicleOwner}</Text>

                <Text style={styles.label}>Vehicle Type:</Text>
                <Text style={styles.value}>{summary.vehicleType}</Text>

                <Text style={styles.label}>Fuel Type:</Text>
                <Text style={styles.value}>{summary.fuelType}</Text>

                <Text style={styles.label}>Total Fuel Limit:</Text>
                <Text style={styles.value}>{summary.totalFuelLimit} L</Text>

                <Text style={styles.label}>Already Pumped:</Text>
                <Text style={styles.value}>{summary.pumpedFuel} L</Text>

                <Text style={styles.label}>Last Filled Amount:</Text>
                <Text style={styles.value}>{summary.lastFilledAmount} L</Text>

                <Text style={styles.label}>Remaining Quota:</Text>
                <Text style={[styles.value, { color: summary.totalFuelLimit - summary.pumpedFuel < 5 ? "red" : "green" }]}>
                    {summary.totalFuelLimit - summary.pumpedFuel} L
                </Text>
            </View>

            <Pressable style={styles.button} onPress={handleFinish}>
                <Text style={styles.buttonText}>Finish</Text>
            </Pressable>
        </View>
    );
}

const styles = StyleSheet.create({
    container: { flex: 1, justifyContent: "center", alignItems: "center", padding: 20, backgroundColor: "#f8f8f8" },
    title: { fontSize: 24, fontWeight: "bold", marginBottom: 20 },
    summaryContainer: { width: "90%", padding: 20, borderRadius: 10, backgroundColor: "#fff", elevation: 3 },
    label: { fontSize: 16, fontWeight: "bold", marginTop: 10 },
    value: { fontSize: 16, color: "#555" },
    button: { backgroundColor: "#ff9800", padding: 15, borderRadius: 8, marginTop: 20, width: "80%", alignItems: "center" },
    buttonText: { color: "#fff", fontWeight: "bold", fontSize: 18 },
});
