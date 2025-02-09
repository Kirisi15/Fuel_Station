import React, { useState } from "react";
import { View, Text, TextInput, Pressable, Alert, StyleSheet, ImageBackground } from "react-native";
import { useRouter } from "expo-router";
import AsyncStorage from "@react-native-async-storage/async-storage"; 
import { BASE_URL } from "../constants/util";

export default function DashboardScreen() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleLogin = async () => {
    if (!username || !password) {
      setErrorMessage("Please enter both username and password");
      return;
    }

    try {
      const response = await fetch(BASE_URL + "employee/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();
      console.log(data);

      if (response.ok) {
       
        const storedUsername = await AsyncStorage.getItem("username");
        const storedPassword = await AsyncStorage.getItem("password");

        if (storedUsername !== username || storedPassword !== password) {
         
          await AsyncStorage.setItem("username", username);
          await AsyncStorage.setItem("password", password);
        }

       
        router.push("./scanqr"); 
      } else {
        setErrorMessage(data.message || "Incorrect username or password");
      }
    } catch (error) {
      console.error(error);
      Alert.alert("Error", "Failed to connect to the server.");
    }
  };

  return (
    <ImageBackground
      source={require("../../assets/images/signin-backimage.jpg")}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.overlay}>
        <Text style={styles.title}>Employee Login</Text>

        <TextInput
          style={styles.input}
          placeholder="Enter Username"
          placeholderTextColor="#666"
          value={username}
          onChangeText={setUsername}
        />

        <TextInput
          style={styles.input}
          placeholder="Enter Password"
          placeholderTextColor="#666"
          value={password}
          onChangeText={setPassword}
          secureTextEntry
        />

        {errorMessage ? <Text style={styles.error}>{errorMessage}</Text> : null}

        <Pressable style={styles.button} onPress={handleLogin}>
          <Text style={styles.buttonText}>Login</Text>
        </Pressable>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    width: "100%",
    height: "100%",
    justifyContent: "center",
    alignItems: "center",
  },
  overlay: {
    width: "85%",
    backgroundColor: "rgba(0, 0, 0, 0.6)", 
    padding: 30,
    borderRadius: 15,
    alignItems: "center",
    elevation: 5, 
  },
  title: {
    fontSize: 26,
    fontWeight: "bold",
    color: "#fff",
    marginBottom: 25,
  },
  input: {
    width: "90%",
    padding: 12,
    borderWidth: 1,
    borderColor: "#ff9800",
    borderRadius: 8,
    marginBottom: 15,
    backgroundColor: "#fff",
    fontSize: 16,
    elevation: 3, 
  },
  button: {
    backgroundColor: "#ff9800",
    paddingVertical: 12,
    paddingHorizontal: 30,
    borderRadius: 8,
    marginTop: 15,
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
  error: {
    color: "#ff4d4d",
    fontSize: 14,
    backgroundColor: "rgba(255, 77, 77, 0.1)",
    padding: 8,
    borderRadius: 5,
    marginBottom: 10,
  },
});
